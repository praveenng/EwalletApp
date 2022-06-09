package com.unibrain.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unibrain.entity.EwalletTransaction;
import com.unibrain.entity.User;

@Repository
public class PaymentDaoImpl  implements PaymentDao{


	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void saveInitiatedEPaymentDetails(EwalletTransaction payments) {
		try(Session session = sessionFactory.openSession()){
			Transaction txn = session.beginTransaction();
			session.save(payments);
			txn.commit();
		}
		
	}

	@Override
	public EwalletTransaction getPaymentsBasedOnEbidReference(String ebidReference) {
		try(Session session = sessionFactory.openSession()){
			EwalletTransaction payment = new EwalletTransaction();
			@SuppressWarnings("unchecked")
			Query<EwalletTransaction> query = session.createQuery("from EwalletTransaction where paymentEwalletReference=:ebidRef");
			query.setParameter("ebidRef", ebidReference);
			payment = query.getSingleResult();
			return payment;			
		}
	}

	@Override
	public Boolean updatePaymentDataWithEWallet(EwalletTransaction payments, User user) {
		try(Session session = sessionFactory.openSession()){
			Transaction txn = session.beginTransaction();
			session.update(payments);
			session.update(user);
			txn.commit();
			return true;
		}
	}
	
	@Override
	public EwalletTransaction getPaymentByEbidReference(String ebidReference) {
		
		EwalletTransaction payment = null;
		try (Session session = sessionFactory.openSession()) {
			Query<EwalletTransaction> query = session.createQuery("from EwalletTransaction p where p.paymentEwalletReference =:ref",EwalletTransaction.class);
			query.setParameter("ref", ebidReference);
			payment = query.getSingleResult();
		}

		return payment;
		
	}

	@Override
	public Boolean checkAccountNumberExist(String accountNumber) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Query<EwalletTransaction> checkAccountNumberExist = session
				.createQuery("from Payments where paymentEwalletReference=:payment_ebid_reference");
		checkAccountNumberExist.setParameter("payment_ebid_reference", accountNumber);
		EwalletTransaction result = null;
		try{
		 result = checkAccountNumberExist.getSingleResult();
		}catch (Exception e) {
			return false;
		}
		if (result != null) {
			return true;
		}
		session.close();
		return false;
	}

	@Override
	public Boolean saveInitiatedPayment(EwalletTransaction payment) {
		Boolean isSaved = false;
		try (Session session = sessionFactory.openSession()) {
			Transaction tn = session.beginTransaction();
			session.save(payment);
			tn.commit();
			isSaved = true;
		}

		return isSaved;
	}

	@Override
	public Boolean updatePayment(EwalletTransaction payment,User user) {
		Boolean isUpdated = false;
		try (Session session = sessionFactory.openSession()) {
			Transaction tn = session.beginTransaction();
			
			session.update(payment);
			session.update(user);
			tn.commit();
			isUpdated = true;
		}

		return isUpdated;
	}
}
