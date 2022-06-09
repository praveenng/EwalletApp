package com.unibrain.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unibrain.entity.EwalletLog;

@Repository
public class EwalletLogDaoImpl implements EwalletLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean saveEwalletLog(EwalletLog ewalletLog) {
		Boolean isSaved = false;
		try (Session session = sessionFactory.openSession()) {
			Transaction tn = session.beginTransaction();
			session.save(ewalletLog);
			tn.commit();
			isSaved = true;
		}
		return isSaved;
	}

}
