package com.unibrain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibrain.dao.UserDao;
import com.unibrain.entity.BankMaster;
import com.unibrain.entity.EwalletLog;
import com.unibrain.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public Boolean register(User user, EwalletLog ewalletLog) {
		return userDao.register(user, ewalletLog);
	}

	@Override
	public User getUserByEWalletId(String ewalletId) {
		return userDao.getUserByEWalletId(ewalletId);
	}

	@Override
	public List<BankMaster> getBankMaster() {
		return userDao.getBankMaster();
	}

	@Override
	public User getUserByLoginId(String loginId) {
		return userDao.getUserByLoginId(loginId);
	}

	@Override
	public Boolean searchPanNumber(String panNumber) {
		return userDao.searchPanNumber(panNumber);
	}

	@Override
	public Boolean searchAdharNumber(String adharNumber) {
		return userDao.searchAdharNumber(adharNumber);
	}

	@Override
	public Boolean searchMobileNumber(String mobileNumber) {
		return userDao.searchMobileNumber(mobileNumber);
	}

	@Override
	public Boolean searchEmailID(String emailID) {
		return userDao.searchEmailID(emailID);
	}

	@Override
	public Boolean updateUserBankFileName(Integer id, String bankFileName, EwalletLog ewalletLog) {
		return userDao.updateUserBankFileName(id, bankFileName, ewalletLog);
	}

}
