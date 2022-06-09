package com.unibrain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDetails {
	
	//Request Parameters for Business from any site when bidder tends to withdrwan from ewallet account
	private String siteName;
	private Integer departmentId;
	private String departmentName;
	private Integer tenderOrAuctionId;
	private String tenderOrAuctionNumber;
	private Integer tenderOrAuctionItemId;
	private String tenderOrAuctionItemName;
	private Integer bidderId;
	private String bidderName;
	
	//Parameters regarding the ewallet account in ewallet repository and deposit request parameters
	private Integer userId;
	private String loginId;
	private String ewalletNumber;
	private String orderId;
	private String ebidReference;
	private BigDecimal initiatedAmount;
	private LocalDateTime paymentInitiatedDate;
	
	//Response parameter from the gateway
	private String paymentBankReference;
	private BigDecimal reconciledAmount;
	private BigDecimal paymentCommission;
	private BigDecimal paymentAmountWithCommsion;
	private String paymentStatus;
	private LocalDateTime paymentReconciledDate;
	private Character transactionFlag;
	private BigDecimal ewalletBalance;
	
	//merchant details parameters
	private String key;
	private String salt;
	
	
	public PaymentDetails(){
		super();
	}


	public PaymentDetails(String siteName, Integer departmentId, String departmentName, Integer tenderOrAuctionId,
			String tenderOrAuctionNumber, Integer tenderOrAuctionItemId, String tenderOrAuctionItemName,
			Integer bidderId, String bidderName, Integer userId, String loginId, String ewalletNumber, String orderId,
			String ebidReference, BigDecimal initiatedAmount, LocalDateTime paymentInitiatedDate,
			String paymentBankReference, BigDecimal reconciledAmount, BigDecimal paymentCommission,
			BigDecimal paymentAmountWithCommsion, String paymentStatus, LocalDateTime paymentReconciledDate,
			Character transactionFlag, BigDecimal ewalletBalance, String key, String salt) {
		super();
		this.siteName = siteName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.tenderOrAuctionId = tenderOrAuctionId;
		this.tenderOrAuctionNumber = tenderOrAuctionNumber;
		this.tenderOrAuctionItemId = tenderOrAuctionItemId;
		this.tenderOrAuctionItemName = tenderOrAuctionItemName;
		this.bidderId = bidderId;
		this.bidderName = bidderName;
		this.userId = userId;
		this.loginId = loginId;
		this.ewalletNumber = ewalletNumber;
		this.orderId = orderId;
		this.ebidReference = ebidReference;
		this.initiatedAmount = initiatedAmount;
		this.paymentInitiatedDate = paymentInitiatedDate;
		this.paymentBankReference = paymentBankReference;
		this.reconciledAmount = reconciledAmount;
		this.paymentCommission = paymentCommission;
		this.paymentAmountWithCommsion = paymentAmountWithCommsion;
		this.paymentStatus = paymentStatus;
		this.paymentReconciledDate = paymentReconciledDate;
		this.transactionFlag = transactionFlag;
		this.ewalletBalance = ewalletBalance;
		this.key = key;
		this.salt = salt;
	}


	public String getSiteName() {
		return siteName;
	}


	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


	public Integer getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public Integer getTenderOrAuctionId() {
		return tenderOrAuctionId;
	}


	public void setTenderOrAuctionId(Integer tenderOrAuctionId) {
		this.tenderOrAuctionId = tenderOrAuctionId;
	}


	public String getTenderOrAuctionNumber() {
		return tenderOrAuctionNumber;
	}


	public void setTenderOrAuctionNumber(String tenderOrAuctionNumber) {
		this.tenderOrAuctionNumber = tenderOrAuctionNumber;
	}


	public Integer getTenderOrAuctionItemId() {
		return tenderOrAuctionItemId;
	}


	public void setTenderOrAuctionItemId(Integer tenderOrAuctionItemId) {
		this.tenderOrAuctionItemId = tenderOrAuctionItemId;
	}


	public String getTenderOrAuctionItemName() {
		return tenderOrAuctionItemName;
	}


	public void setTenderOrAuctionItemName(String tenderOrAuctionItemName) {
		this.tenderOrAuctionItemName = tenderOrAuctionItemName;
	}


	public Integer getBidderId() {
		return bidderId;
	}


	public void setBidderId(Integer bidderId) {
		this.bidderId = bidderId;
	}


	public String getBidderName() {
		return bidderName;
	}


	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getEwalletNumber() {
		return ewalletNumber;
	}


	public void setEwalletNumber(String ewalletNumber) {
		this.ewalletNumber = ewalletNumber;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getEbidReference() {
		return ebidReference;
	}


	public void setEbidReference(String ebidReference) {
		this.ebidReference = ebidReference;
	}


	public BigDecimal getInitiatedAmount() {
		return initiatedAmount;
	}


	public void setInitiatedAmount(BigDecimal initiatedAmount) {
		this.initiatedAmount = initiatedAmount;
	}


	public LocalDateTime getPaymentInitiatedDate() {
		return paymentInitiatedDate;
	}


	public void setPaymentInitiatedDate(LocalDateTime paymentInitiatedDate) {
		this.paymentInitiatedDate = paymentInitiatedDate;
	}


	public String getPaymentBankReference() {
		return paymentBankReference;
	}


	public void setPaymentBankReference(String paymentBankReference) {
		this.paymentBankReference = paymentBankReference;
	}


	public BigDecimal getReconciledAmount() {
		return reconciledAmount;
	}


	public void setReconciledAmount(BigDecimal reconciledAmount) {
		this.reconciledAmount = reconciledAmount;
	}


	public BigDecimal getPaymentCommission() {
		return paymentCommission;
	}


	public void setPaymentCommission(BigDecimal paymentCommission) {
		this.paymentCommission = paymentCommission;
	}


	public BigDecimal getPaymentAmountWithCommsion() {
		return paymentAmountWithCommsion;
	}


	public void setPaymentAmountWithCommsion(BigDecimal paymentAmountWithCommsion) {
		this.paymentAmountWithCommsion = paymentAmountWithCommsion;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public LocalDateTime getPaymentReconciledDate() {
		return paymentReconciledDate;
	}


	public void setPaymentReconciledDate(LocalDateTime paymentReconciledDate) {
		this.paymentReconciledDate = paymentReconciledDate;
	}


	public Character getTransactionFlag() {
		return transactionFlag;
	}


	public void setTransactionFlag(Character transactionFlag) {
		this.transactionFlag = transactionFlag;
	}


	public BigDecimal getEwalletBalance() {
		return ewalletBalance;
	}


	public void setEwalletBalance(BigDecimal ewalletBalance) {
		this.ewalletBalance = ewalletBalance;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt(String salt) {
		this.salt = salt;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bidderId == null) ? 0 : bidderId.hashCode());
		result = prime * result + ((bidderName == null) ? 0 : bidderName.hashCode());
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + ((ebidReference == null) ? 0 : ebidReference.hashCode());
		result = prime * result + ((ewalletBalance == null) ? 0 : ewalletBalance.hashCode());
		result = prime * result + ((ewalletNumber == null) ? 0 : ewalletNumber.hashCode());
		result = prime * result + ((initiatedAmount == null) ? 0 : initiatedAmount.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((loginId == null) ? 0 : loginId.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((paymentAmountWithCommsion == null) ? 0 : paymentAmountWithCommsion.hashCode());
		result = prime * result + ((paymentBankReference == null) ? 0 : paymentBankReference.hashCode());
		result = prime * result + ((paymentCommission == null) ? 0 : paymentCommission.hashCode());
		result = prime * result + ((paymentInitiatedDate == null) ? 0 : paymentInitiatedDate.hashCode());
		result = prime * result + ((paymentReconciledDate == null) ? 0 : paymentReconciledDate.hashCode());
		result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + ((reconciledAmount == null) ? 0 : reconciledAmount.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result + ((siteName == null) ? 0 : siteName.hashCode());
		result = prime * result + ((tenderOrAuctionId == null) ? 0 : tenderOrAuctionId.hashCode());
		result = prime * result + ((tenderOrAuctionItemId == null) ? 0 : tenderOrAuctionItemId.hashCode());
		result = prime * result + ((tenderOrAuctionItemName == null) ? 0 : tenderOrAuctionItemName.hashCode());
		result = prime * result + ((tenderOrAuctionNumber == null) ? 0 : tenderOrAuctionNumber.hashCode());
		result = prime * result + ((transactionFlag == null) ? 0 : transactionFlag.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		PaymentDetails other = (PaymentDetails) obj;
		if (bidderId == null) {
			if (other.bidderId != null)
				return false;
		} else if (!bidderId.equals(other.bidderId))
			return false;
		if (bidderName == null) {
			if (other.bidderName != null)
				return false;
		} else if (!bidderName.equals(other.bidderName))
			return false;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		if (ebidReference == null) {
			if (other.ebidReference != null)
				return false;
		} else if (!ebidReference.equals(other.ebidReference))
			return false;
		if (ewalletBalance == null) {
			if (other.ewalletBalance != null)
				return false;
		} else if (!ewalletBalance.equals(other.ewalletBalance))
			return false;
		if (ewalletNumber == null) {
			if (other.ewalletNumber != null)
				return false;
		} else if (!ewalletNumber.equals(other.ewalletNumber))
			return false;
		if (initiatedAmount == null) {
			if (other.initiatedAmount != null)
				return false;
		} else if (!initiatedAmount.equals(other.initiatedAmount))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (loginId == null) {
			if (other.loginId != null)
				return false;
		} else if (!loginId.equals(other.loginId))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (paymentAmountWithCommsion == null) {
			if (other.paymentAmountWithCommsion != null)
				return false;
		} else if (!paymentAmountWithCommsion.equals(other.paymentAmountWithCommsion))
			return false;
		if (paymentBankReference == null) {
			if (other.paymentBankReference != null)
				return false;
		} else if (!paymentBankReference.equals(other.paymentBankReference))
			return false;
		if (paymentCommission == null) {
			if (other.paymentCommission != null)
				return false;
		} else if (!paymentCommission.equals(other.paymentCommission))
			return false;
		if (paymentInitiatedDate == null) {
			if (other.paymentInitiatedDate != null)
				return false;
		} else if (!paymentInitiatedDate.equals(other.paymentInitiatedDate))
			return false;
		if (paymentReconciledDate == null) {
			if (other.paymentReconciledDate != null)
				return false;
		} else if (!paymentReconciledDate.equals(other.paymentReconciledDate))
			return false;
		if (paymentStatus == null) {
			if (other.paymentStatus != null)
				return false;
		} else if (!paymentStatus.equals(other.paymentStatus))
			return false;
		if (reconciledAmount == null) {
			if (other.reconciledAmount != null)
				return false;
		} else if (!reconciledAmount.equals(other.reconciledAmount))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (siteName == null) {
			if (other.siteName != null)
				return false;
		} else if (!siteName.equals(other.siteName))
			return false;
		if (tenderOrAuctionId == null) {
			if (other.tenderOrAuctionId != null)
				return false;
		} else if (!tenderOrAuctionId.equals(other.tenderOrAuctionId))
			return false;
		if (tenderOrAuctionItemId == null) {
			if (other.tenderOrAuctionItemId != null)
				return false;
		} else if (!tenderOrAuctionItemId.equals(other.tenderOrAuctionItemId))
			return false;
		if (tenderOrAuctionItemName == null) {
			if (other.tenderOrAuctionItemName != null)
				return false;
		} else if (!tenderOrAuctionItemName.equals(other.tenderOrAuctionItemName))
			return false;
		if (tenderOrAuctionNumber == null) {
			if (other.tenderOrAuctionNumber != null)
				return false;
		} else if (!tenderOrAuctionNumber.equals(other.tenderOrAuctionNumber))
			return false;
		if (transactionFlag == null) {
			if (other.transactionFlag != null)
				return false;
		} else if (!transactionFlag.equals(other.transactionFlag))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "PaymentDetails [siteName=" + siteName + ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", tenderOrAuctionId=" + tenderOrAuctionId + ", tenderOrAuctionNumber="
				+ tenderOrAuctionNumber + ", tenderOrAuctionItemId=" + tenderOrAuctionItemId
				+ ", tenderOrAuctionItemName=" + tenderOrAuctionItemName + ", bidderId=" + bidderId + ", bidderName="
				+ bidderName + ", userId=" + userId + ", loginId=" + loginId + ", ewalletNumber=" + ewalletNumber
				+ ", orderId=" + orderId + ", ebidReference=" + ebidReference + ", initiatedAmount=" + initiatedAmount
				+ ", paymentInitiatedDate=" + paymentInitiatedDate + ", paymentBankReference=" + paymentBankReference
				+ ", reconciledAmount=" + reconciledAmount + ", paymentCommission=" + paymentCommission
				+ ", paymentAmountWithCommsion=" + paymentAmountWithCommsion + ", paymentStatus=" + paymentStatus
				+ ", paymentReconciledDate=" + paymentReconciledDate + ", transactionFlag=" + transactionFlag
				+ ", ewalletBalance=" + ewalletBalance + ", key=" + key + ", salt=" + salt + "]";
	}

		

		
}
