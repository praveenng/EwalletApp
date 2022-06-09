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

@Entity
@Table(name = "ewallet_transactions")
public class EwalletTransaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "site_name")
	private String siteName;
	
	@Column(name = "department_id")
	private Integer departmentId;
	
	@Column(name = "department_name")
	private String departmentName;
	
	@Column(name = "ewallet_user_id")
	private Integer ewalletUserId;
	
	@Column(name = "ewallet_login_id")
	private String ewalletLoginId;
	
	@Column(name = "ewallet_id")
	private String ewalletId;
	
	@Column(name = "tender_or_auction_id")
	private Integer tenderOrAuctionId;
	
	@Column(name = "tender_or_auction_number")
	private String tenderOrAuctionNumber;
	
	@Column(name = "tender_or_auction_item_id")
	private Integer tenderOrAuctionItemId;
	
	@Column(name = "tender_or_auction_item_name")
	private String tenderOrAuctionItemName;
	
	@Column(name = "bidder_id")
	private Integer bidderId;
	
	@Column(name = "bidder_name")
	private String bidderName;
	
	@Column(name = "payment_transaction_master_id")
	private Integer paymentTransactionMasterId;
	
	@Column(name = "module_id")
	private Integer moduleId;
	
	@Column(name = "module_name")
	private String moduleName;
	
	@Column(name = "bank_id")
	private Integer bankId;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "payment_ewallet_reference")
	private String paymentEwalletReference;
	
	@Column(name = "payment_primary_mode")
	private Character paymentPrimaryMode;
	
	@Column(name = "payment_mode_id")
	private Integer paymentModeId;
	
	@Column(name = "payment_initiated_amount")
	private BigDecimal paymentInitiatedAmount;
	
	@Column(name = "payment_transaction_reference")
	private String paymentTransactionReference;
	
	@Column(name = "payment_commission")
	private BigDecimal paymentCommission;
	
	@Column(name = "payment_amount_with_commission")
	private BigDecimal paymentAmountWithCommission;
	
	@Column(name = "payment_actual_amount")
	private BigDecimal paymentActualAmount;
	
	@Column(name = "transaction_flag")
	private Character transactionFlag;
	
	@Column(name = "debit_credit_flag")
	private Character debitCreditFlag;
	
	@Column(name = "payment_bank_status")
	private Integer paymentBankStatus;
	
	@Column(name = "payment_status")
	private Integer paymentStatus;
	
	@Column(name = "payment_completion_status")
	private String paymentCompletionStatus;
	
	@Column(name = "payment_initiated_date")
	private LocalDateTime paymentInitiatedDate;
	
	@Column(name = "payment_reconciled_date")
	private LocalDateTime paymentReconciledDate;
	
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "ebid_payment_id")
	private Integer ebidPaymentId;
	
	@Column(name = "current_balance")
	private BigDecimal currentBalance;

	public EwalletTransaction() {
		super();
	}

	public EwalletTransaction(Integer id, String siteName, Integer departmentId, String departmentName,
			Integer ewalletUserId, String ewalletLoginId, String ewalletId, Integer tenderOrAuctionId,
			String tenderOrAuctionNumber, Integer tenderOrAuctionItemId, String tenderOrAuctionItemName,
			Integer bidderId, String bidderName, Integer paymentTransactionMasterId, Integer moduleId,
			String moduleName, Integer bankId, String bankName, String paymentEwalletReference,
			Character paymentPrimaryMode, Integer paymentModeId, BigDecimal paymentInitiatedAmount,
			String paymentTransactionReference, BigDecimal paymentCommission, BigDecimal paymentAmountWithCommission,
			BigDecimal paymentActualAmount, Character transactionFlag, Character debitCreditFlag,
			Integer paymentBankStatus, Integer paymentStatus, String paymentCompletionStatus,
			LocalDateTime paymentInitiatedDate, LocalDateTime paymentReconciledDate, String orderId,
			Integer ebidPaymentId, BigDecimal currentBalance) {
		super();
		this.id = id;
		this.siteName = siteName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.ewalletUserId = ewalletUserId;
		this.ewalletLoginId = ewalletLoginId;
		this.ewalletId = ewalletId;
		this.tenderOrAuctionId = tenderOrAuctionId;
		this.tenderOrAuctionNumber = tenderOrAuctionNumber;
		this.tenderOrAuctionItemId = tenderOrAuctionItemId;
		this.tenderOrAuctionItemName = tenderOrAuctionItemName;
		this.bidderId = bidderId;
		this.bidderName = bidderName;
		this.paymentTransactionMasterId = paymentTransactionMasterId;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.bankId = bankId;
		this.bankName = bankName;
		this.paymentEwalletReference = paymentEwalletReference;
		this.paymentPrimaryMode = paymentPrimaryMode;
		this.paymentModeId = paymentModeId;
		this.paymentInitiatedAmount = paymentInitiatedAmount;
		this.paymentTransactionReference = paymentTransactionReference;
		this.paymentCommission = paymentCommission;
		this.paymentAmountWithCommission = paymentAmountWithCommission;
		this.paymentActualAmount = paymentActualAmount;
		this.transactionFlag = transactionFlag;
		this.debitCreditFlag = debitCreditFlag;
		this.paymentBankStatus = paymentBankStatus;
		this.paymentStatus = paymentStatus;
		this.paymentCompletionStatus = paymentCompletionStatus;
		this.paymentInitiatedDate = paymentInitiatedDate;
		this.paymentReconciledDate = paymentReconciledDate;
		this.orderId = orderId;
		this.ebidPaymentId = ebidPaymentId;
		this.currentBalance = currentBalance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getEwalletUserId() {
		return ewalletUserId;
	}

	public void setEwalletUserId(Integer ewalletUserId) {
		this.ewalletUserId = ewalletUserId;
	}

	public String getEwalletLoginId() {
		return ewalletLoginId;
	}

	public void setEwalletLoginId(String ewalletLoginId) {
		this.ewalletLoginId = ewalletLoginId;
	}

	public String getEwalletId() {
		return ewalletId;
	}

	public void setEwalletId(String ewalletId) {
		this.ewalletId = ewalletId;
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

	public Integer getPaymentTransactionMasterId() {
		return paymentTransactionMasterId;
	}

	public void setPaymentTransactionMasterId(Integer paymentTransactionMasterId) {
		this.paymentTransactionMasterId = paymentTransactionMasterId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPaymentEwalletReference() {
		return paymentEwalletReference;
	}

	public void setPaymentEwalletReference(String paymentEwalletReference) {
		this.paymentEwalletReference = paymentEwalletReference;
	}

	public Character getPaymentPrimaryMode() {
		return paymentPrimaryMode;
	}

	public void setPaymentPrimaryMode(Character paymentPrimaryMode) {
		this.paymentPrimaryMode = paymentPrimaryMode;
	}

	public Integer getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(Integer paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public BigDecimal getPaymentInitiatedAmount() {
		return paymentInitiatedAmount;
	}

	public void setPaymentInitiatedAmount(BigDecimal paymentInitiatedAmount) {
		this.paymentInitiatedAmount = paymentInitiatedAmount;
	}

	public String getPaymentTransactionReference() {
		return paymentTransactionReference;
	}

	public void setPaymentTransactionReference(String paymentTransactionReference) {
		this.paymentTransactionReference = paymentTransactionReference;
	}

	public BigDecimal getPaymentCommission() {
		return paymentCommission;
	}

	public void setPaymentCommission(BigDecimal paymentCommission) {
		this.paymentCommission = paymentCommission;
	}

	public BigDecimal getPaymentAmountWithCommission() {
		return paymentAmountWithCommission;
	}

	public void setPaymentAmountWithCommission(BigDecimal paymentAmountWithCommission) {
		this.paymentAmountWithCommission = paymentAmountWithCommission;
	}

	public BigDecimal getPaymentActualAmount() {
		return paymentActualAmount;
	}

	public void setPaymentActualAmount(BigDecimal paymentActualAmount) {
		this.paymentActualAmount = paymentActualAmount;
	}

	public Character getTransactionFlag() {
		return transactionFlag;
	}

	public void setTransactionFlag(Character transactionFlag) {
		this.transactionFlag = transactionFlag;
	}

	public Character getDebitCreditFlag() {
		return debitCreditFlag;
	}

	public void setDebitCreditFlag(Character debitCreditFlag) {
		this.debitCreditFlag = debitCreditFlag;
	}

	public Integer getPaymentBankStatus() {
		return paymentBankStatus;
	}

	public void setPaymentBankStatus(Integer paymentBankStatus) {
		this.paymentBankStatus = paymentBankStatus;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentCompletionStatus() {
		return paymentCompletionStatus;
	}

	public void setPaymentCompletionStatus(String paymentCompletionStatus) {
		this.paymentCompletionStatus = paymentCompletionStatus;
	}

	public LocalDateTime getPaymentInitiatedDate() {
		return paymentInitiatedDate;
	}

	public void setPaymentInitiatedDate(LocalDateTime paymentInitiatedDate) {
		this.paymentInitiatedDate = paymentInitiatedDate;
	}

	public LocalDateTime getPaymentReconciledDate() {
		return paymentReconciledDate;
	}

	public void setPaymentReconciledDate(LocalDateTime paymentReconciledDate) {
		this.paymentReconciledDate = paymentReconciledDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getEbidPaymentId() {
		return ebidPaymentId;
	}

	public void setEbidPaymentId(Integer ebidPaymentId) {
		this.ebidPaymentId = ebidPaymentId;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankId == null) ? 0 : bankId.hashCode());
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((bidderId == null) ? 0 : bidderId.hashCode());
		result = prime * result + ((bidderName == null) ? 0 : bidderName.hashCode());
		result = prime * result + ((currentBalance == null) ? 0 : currentBalance.hashCode());
		result = prime * result + ((debitCreditFlag == null) ? 0 : debitCreditFlag.hashCode());
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + ((ebidPaymentId == null) ? 0 : ebidPaymentId.hashCode());
		result = prime * result + ((ewalletId == null) ? 0 : ewalletId.hashCode());
		result = prime * result + ((ewalletLoginId == null) ? 0 : ewalletLoginId.hashCode());
		result = prime * result + ((ewalletUserId == null) ? 0 : ewalletUserId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((moduleId == null) ? 0 : moduleId.hashCode());
		result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((paymentActualAmount == null) ? 0 : paymentActualAmount.hashCode());
		result = prime * result + ((paymentAmountWithCommission == null) ? 0 : paymentAmountWithCommission.hashCode());
		result = prime * result + ((paymentBankStatus == null) ? 0 : paymentBankStatus.hashCode());
		result = prime * result + ((paymentCommission == null) ? 0 : paymentCommission.hashCode());
		result = prime * result + ((paymentCompletionStatus == null) ? 0 : paymentCompletionStatus.hashCode());
		result = prime * result + ((paymentEwalletReference == null) ? 0 : paymentEwalletReference.hashCode());
		result = prime * result + ((paymentInitiatedAmount == null) ? 0 : paymentInitiatedAmount.hashCode());
		result = prime * result + ((paymentInitiatedDate == null) ? 0 : paymentInitiatedDate.hashCode());
		result = prime * result + ((paymentModeId == null) ? 0 : paymentModeId.hashCode());
		result = prime * result + ((paymentPrimaryMode == null) ? 0 : paymentPrimaryMode.hashCode());
		result = prime * result + ((paymentReconciledDate == null) ? 0 : paymentReconciledDate.hashCode());
		result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + ((paymentTransactionMasterId == null) ? 0 : paymentTransactionMasterId.hashCode());
		result = prime * result + ((paymentTransactionReference == null) ? 0 : paymentTransactionReference.hashCode());
		result = prime * result + ((siteName == null) ? 0 : siteName.hashCode());
		result = prime * result + ((tenderOrAuctionId == null) ? 0 : tenderOrAuctionId.hashCode());
		result = prime * result + ((tenderOrAuctionItemId == null) ? 0 : tenderOrAuctionItemId.hashCode());
		result = prime * result + ((tenderOrAuctionItemName == null) ? 0 : tenderOrAuctionItemName.hashCode());
		result = prime * result + ((tenderOrAuctionNumber == null) ? 0 : tenderOrAuctionNumber.hashCode());
		result = prime * result + ((transactionFlag == null) ? 0 : transactionFlag.hashCode());
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
		EwalletTransaction other = (EwalletTransaction) obj;
		if (bankId == null) {
			if (other.bankId != null)
				return false;
		} else if (!bankId.equals(other.bankId))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
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
		if (currentBalance == null) {
			if (other.currentBalance != null)
				return false;
		} else if (!currentBalance.equals(other.currentBalance))
			return false;
		if (debitCreditFlag == null) {
			if (other.debitCreditFlag != null)
				return false;
		} else if (!debitCreditFlag.equals(other.debitCreditFlag))
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
		if (ebidPaymentId == null) {
			if (other.ebidPaymentId != null)
				return false;
		} else if (!ebidPaymentId.equals(other.ebidPaymentId))
			return false;
		if (ewalletId == null) {
			if (other.ewalletId != null)
				return false;
		} else if (!ewalletId.equals(other.ewalletId))
			return false;
		if (ewalletLoginId == null) {
			if (other.ewalletLoginId != null)
				return false;
		} else if (!ewalletLoginId.equals(other.ewalletLoginId))
			return false;
		if (ewalletUserId == null) {
			if (other.ewalletUserId != null)
				return false;
		} else if (!ewalletUserId.equals(other.ewalletUserId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (moduleId == null) {
			if (other.moduleId != null)
				return false;
		} else if (!moduleId.equals(other.moduleId))
			return false;
		if (moduleName == null) {
			if (other.moduleName != null)
				return false;
		} else if (!moduleName.equals(other.moduleName))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (paymentActualAmount == null) {
			if (other.paymentActualAmount != null)
				return false;
		} else if (!paymentActualAmount.equals(other.paymentActualAmount))
			return false;
		if (paymentAmountWithCommission == null) {
			if (other.paymentAmountWithCommission != null)
				return false;
		} else if (!paymentAmountWithCommission.equals(other.paymentAmountWithCommission))
			return false;
		if (paymentBankStatus == null) {
			if (other.paymentBankStatus != null)
				return false;
		} else if (!paymentBankStatus.equals(other.paymentBankStatus))
			return false;
		if (paymentCommission == null) {
			if (other.paymentCommission != null)
				return false;
		} else if (!paymentCommission.equals(other.paymentCommission))
			return false;
		if (paymentCompletionStatus == null) {
			if (other.paymentCompletionStatus != null)
				return false;
		} else if (!paymentCompletionStatus.equals(other.paymentCompletionStatus))
			return false;
		if (paymentEwalletReference == null) {
			if (other.paymentEwalletReference != null)
				return false;
		} else if (!paymentEwalletReference.equals(other.paymentEwalletReference))
			return false;
		if (paymentInitiatedAmount == null) {
			if (other.paymentInitiatedAmount != null)
				return false;
		} else if (!paymentInitiatedAmount.equals(other.paymentInitiatedAmount))
			return false;
		if (paymentInitiatedDate == null) {
			if (other.paymentInitiatedDate != null)
				return false;
		} else if (!paymentInitiatedDate.equals(other.paymentInitiatedDate))
			return false;
		if (paymentModeId == null) {
			if (other.paymentModeId != null)
				return false;
		} else if (!paymentModeId.equals(other.paymentModeId))
			return false;
		if (paymentPrimaryMode == null) {
			if (other.paymentPrimaryMode != null)
				return false;
		} else if (!paymentPrimaryMode.equals(other.paymentPrimaryMode))
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
		if (paymentTransactionMasterId == null) {
			if (other.paymentTransactionMasterId != null)
				return false;
		} else if (!paymentTransactionMasterId.equals(other.paymentTransactionMasterId))
			return false;
		if (paymentTransactionReference == null) {
			if (other.paymentTransactionReference != null)
				return false;
		} else if (!paymentTransactionReference.equals(other.paymentTransactionReference))
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
		return true;
	}

	@Override
	public String toString() {
		return "EwalletTransaction [id=" + id + ", siteName=" + siteName + ", departmentId=" + departmentId
				+ ", departmentName=" + departmentName + ", ewalletUserId=" + ewalletUserId + ", ewalletLoginId="
				+ ewalletLoginId + ", ewalletId=" + ewalletId + ", tenderOrAuctionId=" + tenderOrAuctionId
				+ ", tenderOrAuctionNumber=" + tenderOrAuctionNumber + ", tenderOrAuctionItemId="
				+ tenderOrAuctionItemId + ", tenderOrAuctionItemName=" + tenderOrAuctionItemName + ", bidderId="
				+ bidderId + ", bidderName=" + bidderName + ", paymentTransactionMasterId=" + paymentTransactionMasterId
				+ ", moduleId=" + moduleId + ", moduleName=" + moduleName + ", bankId=" + bankId + ", bankName="
				+ bankName + ", paymentEwalletReference=" + paymentEwalletReference + ", paymentPrimaryMode="
				+ paymentPrimaryMode + ", paymentModeId=" + paymentModeId + ", paymentInitiatedAmount="
				+ paymentInitiatedAmount + ", paymentTransactionReference=" + paymentTransactionReference
				+ ", paymentCommission=" + paymentCommission + ", paymentAmountWithCommission="
				+ paymentAmountWithCommission + ", paymentActualAmount=" + paymentActualAmount + ", transactionFlag="
				+ transactionFlag + ", debitCreditFlag=" + debitCreditFlag + ", paymentBankStatus=" + paymentBankStatus
				+ ", paymentStatus=" + paymentStatus + ", paymentCompletionStatus=" + paymentCompletionStatus
				+ ", paymentInitiatedDate=" + paymentInitiatedDate + ", paymentReconciledDate=" + paymentReconciledDate
				+ ", orderId=" + orderId + ", ebidPaymentId=" + ebidPaymentId + ", currentBalance=" + currentBalance
				+ "]";
	}

		
}
