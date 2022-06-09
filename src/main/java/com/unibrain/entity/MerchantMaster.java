package com.unibrain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MERCHANT_MASTER")
public class MerchantMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="serial")  
	private Integer id;
	
	@Column(name="Bank_Id")
	private int bankId; 

	@Column(name="Payment_Mode_Id")
	private int paymentModeId; 

	@Column(name="Merchant_Nodal")
	private String merchantNodal; 

	@Column(name="Merchant_Type")
	private String merchantType; 

	@Column(name="Merchant_Nodal_Name")
	private String merchantNodalName; 
	
	@Column(name="Merchant_ICC_Id")
	private String merchantICCId; 
	
	@Column(name="Merchant_User_Name")
	private String merchantUserName; 
	
	@Column(name="Merchant_Password")
	private String merchantPassword; 
	
	@Column(name="Merchant_Return_URL")
	private String merchantReturnURL; 
	
	@Column(name="Merchant_Code")
	private String merchantCode; 
	
	@Column(name="Merchant_Beneficiary_Name")
	private String merchantBeneficiaryName; 
	
	@Column(name="Merchant_IFSC")
	private String merchantIFSC; 
	
	@Column(name="Merchant_Bank")
	private String merchantBank; 
	
	
	@Column(name="Merchant_Enablement_Flag")
	private String merchantEnablementFlag; 
	
	
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


	public MerchantMaster() {
		super();
	}


	public MerchantMaster(Integer id, int bankId, int paymentModeId, String merchantNodal, String merchantType,
			String merchantNodalName, String merchantICCId, String merchantUserName, String merchantPassword,
			String merchantReturnURL, String merchantCode, String merchantBeneficiaryName, String merchantIFSC,
			String merchantBank, String merchantEnablementFlag, String salt, String prefixCode, String merchantBranch,
			String merchantNeftBank, String neftAccountType, String neftAccountName, String accountId) {
		super();
		this.id = id;
		this.bankId = bankId;
		this.paymentModeId = paymentModeId;
		this.merchantNodal = merchantNodal;
		this.merchantType = merchantType;
		this.merchantNodalName = merchantNodalName;
		this.merchantICCId = merchantICCId;
		this.merchantUserName = merchantUserName;
		this.merchantPassword = merchantPassword;
		this.merchantReturnURL = merchantReturnURL;
		this.merchantCode = merchantCode;
		this.merchantBeneficiaryName = merchantBeneficiaryName;
		this.merchantIFSC = merchantIFSC;
		this.merchantBank = merchantBank;
		this.merchantEnablementFlag = merchantEnablementFlag;
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


	public int getBankId() {
		return bankId;
	}


	public void setBankId(int bankId) {
		this.bankId = bankId;
	}


	public int getPaymentModeId() {
		return paymentModeId;
	}


	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}


	public String getMerchantNodal() {
		return merchantNodal;
	}


	public void setMerchantNodal(String merchantNodal) {
		this.merchantNodal = merchantNodal;
	}


	public String getMerchantType() {
		return merchantType;
	}


	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}


	public String getMerchantNodalName() {
		return merchantNodalName;
	}


	public void setMerchantNodalName(String merchantNodalName) {
		this.merchantNodalName = merchantNodalName;
	}


	public String getMerchantICCId() {
		return merchantICCId;
	}


	public void setMerchantICCId(String merchantICCId) {
		this.merchantICCId = merchantICCId;
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


	public String getMerchantCode() {
		return merchantCode;
	}


	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
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


	public String getMerchantEnablementFlag() {
		return merchantEnablementFlag;
	}


	public void setMerchantEnablementFlag(String merchantEnablementFlag) {
		this.merchantEnablementFlag = merchantEnablementFlag;
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
		result = prime * result + bankId;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((merchantBank == null) ? 0 : merchantBank.hashCode());
		result = prime * result + ((merchantBeneficiaryName == null) ? 0 : merchantBeneficiaryName.hashCode());
		result = prime * result + ((merchantBranch == null) ? 0 : merchantBranch.hashCode());
		result = prime * result + ((merchantCode == null) ? 0 : merchantCode.hashCode());
		result = prime * result + ((merchantEnablementFlag == null) ? 0 : merchantEnablementFlag.hashCode());
		result = prime * result + ((merchantICCId == null) ? 0 : merchantICCId.hashCode());
		result = prime * result + ((merchantIFSC == null) ? 0 : merchantIFSC.hashCode());
		result = prime * result + ((merchantNeftBank == null) ? 0 : merchantNeftBank.hashCode());
		result = prime * result + ((merchantNodal == null) ? 0 : merchantNodal.hashCode());
		result = prime * result + ((merchantNodalName == null) ? 0 : merchantNodalName.hashCode());
		result = prime * result + ((merchantPassword == null) ? 0 : merchantPassword.hashCode());
		result = prime * result + ((merchantReturnURL == null) ? 0 : merchantReturnURL.hashCode());
		result = prime * result + ((merchantType == null) ? 0 : merchantType.hashCode());
		result = prime * result + ((merchantUserName == null) ? 0 : merchantUserName.hashCode());
		result = prime * result + ((neftAccountName == null) ? 0 : neftAccountName.hashCode());
		result = prime * result + ((neftAccountType == null) ? 0 : neftAccountType.hashCode());
		result = prime * result + paymentModeId;
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
		MerchantMaster other = (MerchantMaster) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (bankId != other.bankId)
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
		if (merchantCode == null) {
			if (other.merchantCode != null)
				return false;
		} else if (!merchantCode.equals(other.merchantCode))
			return false;
		if (merchantEnablementFlag == null) {
			if (other.merchantEnablementFlag != null)
				return false;
		} else if (!merchantEnablementFlag.equals(other.merchantEnablementFlag))
			return false;
		if (merchantICCId == null) {
			if (other.merchantICCId != null)
				return false;
		} else if (!merchantICCId.equals(other.merchantICCId))
			return false;
		if (merchantIFSC == null) {
			if (other.merchantIFSC != null)
				return false;
		} else if (!merchantIFSC.equals(other.merchantIFSC))
			return false;
		if (merchantNeftBank == null) {
			if (other.merchantNeftBank != null)
				return false;
		} else if (!merchantNeftBank.equals(other.merchantNeftBank))
			return false;
		if (merchantNodal == null) {
			if (other.merchantNodal != null)
				return false;
		} else if (!merchantNodal.equals(other.merchantNodal))
			return false;
		if (merchantNodalName == null) {
			if (other.merchantNodalName != null)
				return false;
		} else if (!merchantNodalName.equals(other.merchantNodalName))
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
		if (merchantType == null) {
			if (other.merchantType != null)
				return false;
		} else if (!merchantType.equals(other.merchantType))
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
		if (paymentModeId != other.paymentModeId)
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
		return "MerchantMaster [id=" + id + ", bankId=" + bankId + ", paymentModeId=" + paymentModeId
				+ ", merchantNodal=" + merchantNodal + ", merchantType=" + merchantType + ", merchantNodalName="
				+ merchantNodalName + ", merchantICCId=" + merchantICCId + ", merchantUserName=" + merchantUserName
				+ ", merchantPassword=" + merchantPassword + ", merchantReturnURL=" + merchantReturnURL
				+ ", merchantCode=" + merchantCode + ", merchantBeneficiaryName=" + merchantBeneficiaryName
				+ ", merchantIFSC=" + merchantIFSC + ", merchantBank=" + merchantBank + ", merchantEnablementFlag="
				+ merchantEnablementFlag + ", salt=" + salt + ", prefixCode=" + prefixCode + ", merchantBranch="
				+ merchantBranch + ", merchantNeftBank=" + merchantNeftBank + ", neftAccountType=" + neftAccountType
				+ ", neftAccountName=" + neftAccountName + ", accountId=" + accountId + "]";
	}

	
}
