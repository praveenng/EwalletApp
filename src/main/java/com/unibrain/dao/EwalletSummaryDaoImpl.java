package com.unibrain.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unibrain.entity.EwalletTransaction;

@Repository
public class EwalletSummaryDaoImpl implements EwalletSummaryDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<EwalletTransaction> getEwalletSummaryByUserId(Integer userId,Integer page,Integer limit) {
		
		try (Session session = sessionFactory.openSession()) {
			List<EwalletTransaction> list = null;
			Query<EwalletTransaction> query = session.createQuery("from EwalletTransaction et where et.ewalletUserId =:userId "
					+ "order by et.paymentInitiatedDate desc",EwalletTransaction.class);
			query.setFirstResult((page-1) * limit);
			query.setMaxResults(limit);
			query.setParameter("userId", userId);
			list = query.getResultList();
			return list;
		}
		
	}

	@Override
	public Long getTransactionCount(Integer userId) {
		try (Session session = sessionFactory.openSession()) {
			Query<Long> query = session.createQuery("select count(et) from EwalletTransaction et where et.ewalletUserId =:userId "
					,Long.class);
			query.setParameter("userId", userId);
			Long count = query.getSingleResult();
			return count;
		}
	}
	
}
