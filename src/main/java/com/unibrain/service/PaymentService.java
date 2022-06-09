package com.unibrain.service;

import com.unibrain.entity.EwalletTransaction;
import com.unibrain.entity.PaymentDetails;
import com.unibrain.entity.User;

public interface PaymentService {

	public void saveInitiatedEPaymentDetails(PaymentDetails paymentDetails);

	public EwalletTransaction getPaymentsBasedOnEbidReference(String ebidReference);

	public Boolean updatePaymentDataWithEWallet(EwalletTransaction payments, User user);
	
	public EwalletTransaction getPaymentByEbidReference(String ebidReference);
	public Boolean checkAccountNumberExist(String accountNumber);
	public Boolean saveInitiatedPayment(EwalletTransaction payment);
	public Boolean updatePayment(EwalletTransaction payment,User user);
}
