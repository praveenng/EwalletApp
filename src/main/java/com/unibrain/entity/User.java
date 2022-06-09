package com.unibrain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "serial")
	private int id;

	@Column(name = "login_id")
	private String loginId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "ewallet_id")
	private String ewalletId;

	@Column(name = "user_type")
	private Integer userType;

	@Column(name = "individual_or_company_name")
	private String individualOrCompanyName;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "pan_number")
	private String panNumber;

	@Column(name = "adhar_number")
	private String adharNumber;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "account_holder_name")
	private String accountHolderName;

	@Column(name = "ifsc")
	private String ifsc;

	@Column(name = "bank_id")
	private Integer bankId;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "status")
	private Character status;
	
	@Column(name = "bank_file_name")
	private String bankFileName;
	
	@Column(name = "wallet_balance")
	private BigDecimal walletBalance;
	
	@Transient
	private String confirmPassword;

	public User() {

	}

	public User(int id, String loginId, String password, String ewalletId, Integer userType,
			String individualOrCompanyName, String emailId, String mobileNumber, String panNumber, String adharNumber,
			String accountNumber, String accountHolderName, String ifsc, Integer bankId, String branchName,
			LocalDateTime createdDate, Character status, String bankFileName, String confirmPassword, 
			BigDecimal walletBalance) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
		this.ewalletId = ewalletId;
		this.userType = userType;
		this.individualOrCompanyName = individualOrCompanyName;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.panNumber = panNumber;
		this.adharNumber = adharNumber;
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.ifsc = ifsc;
		this.bankId = bankId;
		this.branchName = branchName;
		this.createdDate = createdDate;
		this.status = status;
		this.bankFileName = bankFileName;
		this.confirmPassword = confirmPassword;
		this.walletBalance = walletBalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEwalletId() {
		return ewalletId;
	}

	public void setEwalletId(String ewalletId) {
		this.ewalletId = ewalletId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getIndividualOrCompanyName() {
		return individualOrCompanyName;
	}

	public void setIndividualOrCompanyName(String individualOrCompanyName) {
		this.individualOrCompanyName = individualOrCompanyName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAdharNumber() {
		return adharNumber;
	}

	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getBankFileName() {
		return bankFileName;
	}

	public void setBankFileName(String bankFileName) {
		this.bankFileName = bankFileName;
	}
	
	public BigDecimal getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(BigDecimal walletBalance) {
		this.walletBalance = walletBalance;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", loginId=" + loginId + ", password=" + password + ", ewalletId=" + ewalletId
				+ ", userType=" + userType + ", individualOrCompanyName=" + individualOrCompanyName + ", emailId="
				+ emailId + ", mobileNumber=" + mobileNumber + ", panNumber=" + panNumber + ", adharNumber="
				+ adharNumber + ", accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName
				+ ", ifsc=" + ifsc + ", bankId=" + bankId + ", branchName=" + branchName + ", createdDate="
				+ createdDate + ", status=" + status + ", bankFileName=" + bankFileName + ", walletBalance="
				+ walletBalance + ", confirmPassword=" + confirmPassword + "]";
	}

}