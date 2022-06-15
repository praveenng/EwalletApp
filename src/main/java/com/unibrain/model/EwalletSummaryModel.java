package com.unibrain.model;

public class EwalletSummaryModel {
	
	private String date;
	private String narration;
	private String paymentReference;
	private String debit;
	private String credit;
	private String balance;
	
	public EwalletSummaryModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EwalletSummaryModel(String date, String narration, String paymentReference, String debit, String credit,
			String balance) {
		super();
		this.date = date;
		this.narration = narration;
		this.paymentReference = paymentReference;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getPaymentReference() {
		return paymentReference;
	}
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}
	public String getDebit() {
		return debit;
	}
	public void setDebit(String debit) {
		this.debit = debit;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((debit == null) ? 0 : debit.hashCode());
		result = prime * result + ((narration == null) ? 0 : narration.hashCode());
		result = prime * result + ((paymentReference == null) ? 0 : paymentReference.hashCode());
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
		EwalletSummaryModel other = (EwalletSummaryModel) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (debit == null) {
			if (other.debit != null)
				return false;
		} else if (!debit.equals(other.debit))
			return false;
		if (narration == null) {
			if (other.narration != null)
				return false;
		} else if (!narration.equals(other.narration))
			return false;
		if (paymentReference == null) {
			if (other.paymentReference != null)
				return false;
		} else if (!paymentReference.equals(other.paymentReference))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EwalletSummaryModel [date=" + date + ", narration=" + narration + ", paymentReference="
				+ paymentReference + ", debit=" + debit + ", credit=" + credit + ", balance=" + balance + "]";
	}
	
	

}
