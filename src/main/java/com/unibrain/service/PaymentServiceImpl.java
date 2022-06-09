package com.unibrain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unibrain.dao.PaymentDao;
import com.unibrain.entity.EwalletTransaction;
import com.unibrain.entity.PaymentDetails;
import com.unibrain.entity.User;
import com.unibrain.enums.PaymentGatewayBankEnum;
import com.unibrain.enums.PaymentModeEnum;
import com.unibrain.enums.PaymentModeMasterEnum;
import com.unibrain.enums.PaymentStatusEnum;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentDao paymentDao;

	@Override
	public void saveInitiatedEPaymentDetails(PaymentDetails paymentDetails) {
		EwalletTransaction payments = new EwalletTransaction();
		payments.setEwalletUserId(paymentDetails.getUserId());
		payments.setEwalletLoginId(paymentDetails.getLoginId());
		payments.setEwalletId(paymentDetails.getEwalletNumber());
		payments.setBankId(PaymentGatewayBankEnum.razorpay.getGateWayId());
		payments.setBankName(PaymentGatewayBankEnum.razorpay.getGateWayDescription());
		payments.setEwalletId(paymentDetails.getEwalletNumber());
		payments.setOrderId(paymentDetails.getOrderId());
		payments.setPaymentEwalletReference(paymentDetails.getEbidReference());
		payments.setPaymentPrimaryMode(PaymentModeEnum.epayment.getPaymentModeCharacterValue());
		payments.setPaymentModeId(PaymentModeMasterEnum.epayment.getPaymentModeMasterId());
		payments.setPaymentInitiatedAmount(paymentDetails.getInitiatedAmount());
		payments.setPaymentStatus(PaymentStatusEnum.initiated.getStatusValue());
		payments.setPaymentInitiatedDate(paymentDetails.getPaymentInitiatedDate());
		payments.setTransactionFlag(paymentDetails.getTransactionFlag());
		paymentDao.saveInitiatedEPaymentDetails(payments);
		
	}

	@Override
	public EwalletTransaction getPaymentsBasedOnEbidReference(String ebidReference) {
		return paymentDao.getPaymentsBasedOnEbidReference(ebidReference);
	}

	@Override
	public Boolean updatePaymentDataWithEWallet(EwalletTransaction payments, User user) {
		return paymentDao.updatePaymentDataWithEWallet(payments, user);
	}
	
	@Override
	public EwalletTransaction getPaymentByEbidReference(String ebidReference) {
		return paymentDao.getPaymentByEbidReference(ebidReference);
		
	}

	@Override
	public Boolean checkAccountNumberExist(String accountNumber) {
		return paymentDao.checkAccountNumberExist(accountNumber);
	}

	@Override
	public Boolean saveInitiatedPayment(EwalletTransaction payment) {
		return paymentDao.saveInitiatedPayment(payment);
	}

	@Override
	public Boolean updatePayment(EwalletTransaction payment,User user) {
		return paymentDao.updatePayment(payment,user);
	}


}
