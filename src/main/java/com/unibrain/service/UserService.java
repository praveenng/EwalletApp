package com.unibrain.service;

import java.util.List;

import com.unibrain.entity.BankMaster;
import com.unibrain.entity.EwalletLog;
import com.unibrain.entity.User;

public interface UserService {
	public User getUserById(int id);

	public Boolean register(User user, EwalletLog ewalletLog);

	public User getUserByEWalletId(String ewalletId);

	public List<BankMaster> getBankMaster();

	public User getUserByLoginId(String loginId);

	public Boolean searchPanNumber(String panNumber);

	public Boolean searchAdharNumber(String adharNumber);

	public Boolean searchMobileNumber(String mobileNumber);

	public Boolean searchEmailID(String emailID);

	public Boolean updateUserBankFileName(Integer id, String bankFileName, EwalletLog ewalletLog);
}
