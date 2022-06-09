package com.unibrain.dao;

import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unibrain.entity.BankMaster;
import com.unibrain.entity.EwalletLog;
import com.unibrain.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean register(User user, EwalletLog ewalletLog) {
		Boolean isSaved = false;
		try (Session session = sessionFactory.openSession()) {
			Transaction tn = session.beginTransaction();
			session.save(user);

			int id = user.getId();

			String formatId = String.format("%06d", id);

			Query<?> query = session.createQuery("update User set ewalletId =:ewalletId where id=:id");

			String ewalletId = "EW" + formatId;

			query.setParameter("ewalletId", ewalletId);
			query.setParameter("id", id);
			query.executeUpdate();

			ewalletLog.setEwalletId(ewalletId);
			session.save(ewalletLog);

			tn.commit();
			isSaved = true;
		}

		return isSaved;
	}

	@Override
	public User getUserById(int id) {

		User user = null;
		try (Session session = sessionFactory.openSession()) {
			user = session.get(User.class, id);
		}

		return user;
	}

	@Override
	public User getUserByEWalletId(String ewalletId) {
		User user = null;
		try (Session session = sessionFactory.openSession()) {
			Query<User> query = session.createQuery("from User where ewalletId=:eWalletId", User.class);
			query.setParameter("eWalletId", ewalletId);
			user = query.getResultList().stream().findFirst().orElse(null);
		}

		return user;
	}

	@Override
	public List<BankMaster> getBankMaster() {
		List<BankMaster> lBankMasters = null;
		try (Session session = sessionFactory.openSession()) {
			Query<BankMaster> query = session.createQuery("from BankMaster order by id", BankMaster.class);
			lBankMasters = query.getResultList();
		}

		return lBankMasters;
	}

	@Override
	public User getUserByLoginId(String loginId) {
		User user = null;
		try (Session session = sessionFactory.openSession()) {
			Query<User> query = session.createQuery("from User where loginId=:loginId", User.class);
			query.setParameter("loginId", loginId);
			user = query.getResultList().stream().findFirst().orElse(null);
		}

		return user;
	}

	@Override
	public Boolean searchPanNumber(String panNumber) {
		try (Session session = sessionFactory.openSession()) {
			Query<String> query = session.createQuery("select panNumber from User where lower(panNumber)=:pan",
					String.class);
			query.setParameter("pan", panNumber.toLowerCase());
			return !query.getResultList().isEmpty();
		}
	}

	@Override
	public Boolean searchAdharNumber(String adharNumber) {
		try (Session session = sessionFactory.openSession()) {
			Query<String> query = session.createQuery("select adharNumber from User where lower(adharNumber)=:adhar",
					String.class);
			query.setParameter("adhar", adharNumber.toLowerCase());
			return !query.getResultList().isEmpty();
		}
	}

	@Override
	public Boolean searchMobileNumber(String mobileNumber) {
		try (Session session = sessionFactory.openSession()) {
			Query<String> query = session.createQuery("select mobileNumber from User where lower(mobileNumber)=:mob",
					String.class);
			query.setParameter("mob", mobileNumber.toLowerCase());
			return !query.getResultList().isEmpty();
		}
	}

	@Override
	public Boolean searchEmailID(String emailID) {
		try (Session session = sessionFactory.openSession()) {
			Query<String> query = session.createQuery("select emailId from User where lower(emailId)=:emailId",
					String.class);
			query.setParameter("emailId", emailID.toLowerCase());
			return !query.getResultList().isEmpty();
		}
	}

	@Override
	public Boolean updateUserBankFileName(Integer id, String bankFileName, EwalletLog ewalletLog) {
		Boolean isSaved = false;
		try (Session session = sessionFactory.openSession()) {
			Transaction tn = session.beginTransaction();

			Query<?> query = session.createQuery("update User set bankFileName =:bankFileName where id=:id");

			query.setParameter("bankFileName", bankFileName);
			query.setParameter("id", id);
			query.executeUpdate();

			session.save(ewalletLog);

			tn.commit();
			isSaved = true;
		}

		return isSaved;
	}

}
