package com.unibrain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MERCHANT_DETAILS")
public class MerchantDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="serial")  
	private Integer id;
	
	@Column(name="Merchant_Name")
	private String merchantName; 

	@Column(name="Merchant_ICC")
	private String merchantICC; 
	
	@Column(name="Merchant_UserName")
	private String merchantUserName; 
	
	@Column(name="Merchant_Password")
	private String merchantPassword; 

	@Column(name="Merchant_Return_URL")
	private String merchantReturnURL; 
	
	@Column(name="Merchant_Key")
	private String merchantKey; 
	
	@Column(name="Merchant_Beneficiary_Name")
	private String merchantBeneficiaryName; 
	
	@Column(name="Merchant_IFSC")
	private String merchantIFSC; 
	
	@Column(name="Merchant_Bank")
	private String merchantBank; 
	

	@Column(name="Salt")
	private String salt;
	
	@Column(name="Prefix_Code")
	private String prefixCode;
	
	@Column(name="Merchant_Branch")
	private String merchantBranch;

	@Column(name="merchant_neft_bank")
	private String merchantNeftBank;
	
	@Column(name="neft_account_type")
	private String neftAccountType;
	
	@Column(name="neft_account_name")
	private String neftAccountName;
	
	@Column(name = "account_id")
	private String accountId;
	
	public MerchantDetail() {
		super();
	}

	public MerchantDetail(Integer id, String merchantName, String merchantICC, String merchantUserName,
			String merchantPassword, String merchantReturnURL, String merchantKey, String merchantBeneficiaryName,
			String merchantIFSC, String merchantBank, String salt, String prefixCode, String merchantBranch,
			String merchantNeftBank, String neftAccountType, String neftAccountName, String accountId) {
		super();
		this.id = id;
		this.merchantName = merchantName;
		this.merchantICC = merchantICC;
		this.merchantUserName = merchantUserName;
		this.merchantPassword = merchantPassword;
		this.merchantReturnURL = merchantReturnURL;
		this.merchantKey = merchantKey;
		this.merchantBeneficiaryName = merchantBeneficiaryName;
		this.merchantIFSC = merchantIFSC;
		this.merchantBank = merchantBank;
		this.salt = salt;
		this.prefixCode = prefixCode;
		this.merchantBranch = merchantBranch;
		this.merchantNeftBank = merchantNeftBank;
		this.neftAccountType = neftAccountType;
		this.neftAccountName = neftAccountName;
		this.accountId = accountId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantICC() {
		return merchantICC;
	}

	public void setMerchantICC(String merchantICC) {
		this.merchantICC = merchantICC;
	}

	public String getMerchantUserName() {
		return merchantUserName;
	}

	public void setMerchantUserName(String merchantUserName) {
		this.merchantUserName = merchantUserName;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getMerchantReturnURL() {
		return merchantReturnURL;
	}

	public void setMerchantReturnURL(String merchantReturnURL) {
		this.merchantReturnURL = merchantReturnURL;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getMerchantBeneficiaryName() {
		return merchantBeneficiaryName;
	}

	public void setMerchantBeneficiaryName(String merchantBeneficiaryName) {
		this.merchantBeneficiaryName = merchantBeneficiaryName;
	}

	public String getMerchantIFSC() {
		return merchantIFSC;
	}

	public void setMerchantIFSC(String merchantIFSC) {
		this.merchantIFSC = merchantIFSC;
	}

	public String getMerchantBank() {
		return merchantBank;
	}

	public void setMerchantBank(String merchantBank) {
		this.merchantBank = merchantBank;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPrefixCode() {
		return prefixCode;
	}

	public void setPrefixCode(String prefixCode) {
		this.prefixCode = prefixCode;
	}

	public String getMerchantBranch() {
		return merchantBranch;
	}

	public void setMerchantBranch(String merchantBranch) {
		this.merchantBranch = merchantBranch;
	}

	public String getMerchantNeftBank() {
		return merchantNeftBank;
	}

	public void setMerchantNeftBank(String merchantNeftBank) {
		this.merchantNeftBank = merchantNeftBank;
	}

	public String getNeftAccountType() {
		return neftAccountType;
	}

	public void setNeftAccountType(String neftAccountType) {
		this.neftAccountType = neftAccountType;
	}

	public String getNeftAccountName() {
		return neftAccountName;
	}

	public void setNeftAccountName(String neftAccountName) {
		this.neftAccountName = neftAccountName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((merchantBank == null) ? 0 : merchantBank.hashCode());
		result = prime * result + ((merchantBeneficiaryName == null) ? 0 : merchantBeneficiaryName.hashCode());
		result = prime * result + ((merchantBranch == null) ? 0 : merchantBranch.hashCode());
		result = prime * result + ((merchantICC == null) ? 0 : merchantICC.hashCode());
		result = prime * result + ((merchantIFSC == null) ? 0 : merchantIFSC.hashCode());
		result = prime * result + ((merchantKey == null) ? 0 : merchantKey.hashCode());
		result = prime * result + ((merchantName == null) ? 0 : merchantName.hashCode());
		result = prime * result + ((merchantNeftBank == null) ? 0 : merchantNeftBank.hashCode());
		result = prime * result + ((merchantPassword == null) ? 0 : merchantPassword.hashCode());
		result = prime * result + ((merchantReturnURL == null) ? 0 : merchantReturnURL.hashCode());
		result = prime * result + ((merchantUserName == null) ? 0 : merchantUserName.hashCode());
		result = prime * result + ((neftAccountName == null) ? 0 : neftAccountName.hashCode());
		result = prime * result + ((neftAccountType == null) ? 0 : neftAccountType.hashCode());
		result = prime * result + ((prefixCode == null) ? 0 : prefixCode.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MerchantDetail other = (MerchantDetail) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (merchantBank == null) {
			if (other.merchantBank != null)
				return false;
		} else if (!merchantBank.equals(other.merchantBank))
			return false;
		if (merchantBeneficiaryName == null) {
			if (other.merchantBeneficiaryName != null)
				return false;
		} else if (!merchantBeneficiaryName.equals(other.merchantBeneficiaryName))
			return false;
		if (merchantBranch == null) {
			if (other.merchantBranch != null)
				return false;
		} else if (!merchantBranch.equals(other.merchantBranch))
			return false;
		if (merchantICC == null) {
			if (other.merchantICC != null)
				return false;
		} else if (!merchantICC.equals(other.merchantICC))
			return false;
		if (merchantIFSC == null) {
			if (other.merchantIFSC != null)
				return false;
		} else if (!merchantIFSC.equals(other.merchantIFSC))
			return false;
		if (merchantKey == null) {
			if (other.merchantKey != null)
				return false;
		} else if (!merchantKey.equals(other.merchantKey))
			return false;
		if (merchantName == null) {
			if (other.merchantName != null)
				return false;
		} else if (!merchantName.equals(other.merchantName))
			return false;
		if (merchantNeftBank == null) {
			if (other.merchantNeftBank != null)
				return false;
		} else if (!merchantNeftBank.equals(other.merchantNeftBank))
			return false;
		if (merchantPassword == null) {
			if (other.merchantPassword != null)
				return false;
		} else if (!merchantPassword.equals(other.merchantPassword))
			return false;
		if (merchantReturnURL == null) {
			if (other.merchantReturnURL != null)
				return false;
		} else if (!merchantReturnURL.equals(other.merchantReturnURL))
			return false;
		if (merchantUserName == null) {
			if (other.merchantUserName != null)
				return false;
		} else if (!merchantUserName.equals(other.merchantUserName))
			return false;
		if (neftAccountName == null) {
			if (other.neftAccountName != null)
				return false;
		} else if (!neftAccountName.equals(other.neftAccountName))
			return false;
		if (neftAccountType == null) {
			if (other.neftAccountType != null)
				return false;
		} else if (!neftAccountType.equals(other.neftAccountType))
			return false;
		if (prefixCode == null) {
			if (other.prefixCode != null)
				return false;
		} else if (!prefixCode.equals(other.prefixCode))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MerchantDetail [id=" + id + ", merchantName=" + merchantName + ", merchantICC=" + merchantICC
				+ ", merchantUserName=" + merchantUserName + ", merchantPassword=" + merchantPassword
				+ ", merchantReturnURL=" + merchantReturnURL + ", merchantKey=" + merchantKey
				+ ", merchantBeneficiaryName=" + merchantBeneficiaryName + ", merchantIFSC=" + merchantIFSC
				+ ", merchantBank=" + merchantBank + ", salt=" + salt + ", prefixCode=" + prefixCode
				+ ", merchantBranch=" + merchantBranch + ", merchantNeftBank=" + merchantNeftBank + ", neftAccountType="
				+ neftAccountType + ", neftAccountName=" + neftAccountName + ", accountId=" + accountId + "]";
	}	

}
