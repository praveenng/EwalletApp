package com.unibrain.enums;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentModeMasterEnum {

	
	epayment(1, "E-Payment", "E-Payment"),neft(2, "NEFT", "E-Payment");

	private Integer paymentModeMasterId;
	private String paymentModeDescription;
	private String paymentModeCategory;

	private PaymentModeMasterEnum(Integer paymentModeMasterId, String paymentModeDescription,
			String paymentModeCategory) {
		this.paymentModeMasterId = paymentModeMasterId;
		this.paymentModeDescription = paymentModeDescription;
		this.paymentModeCategory = paymentModeCategory;
	}

	public Integer getPaymentModeMasterId() {
		return paymentModeMasterId;
	}

	public String getPaymentModeDescription() {
		return paymentModeDescription;
	}

	public String getPaymentModeCategory() {
		return paymentModeCategory;
	}

	public static int getPaymentModeStatusId(String name) {
		int statusValue = 0;
		PaymentModeMasterEnum[] values = PaymentModeMasterEnum.values();
		for (PaymentModeMasterEnum value : values) {
			if (value.getPaymentModeDescription().equals(name)) {
				statusValue = value.getPaymentModeMasterId();
			}

		}
		return statusValue;
	}

	public static String getPaymentModeStatusValue(Integer id) {
		String statusValue = null;
		PaymentModeMasterEnum[] values = PaymentModeMasterEnum.values();
		for (PaymentModeMasterEnum value : values) {
			if (value.getPaymentModeMasterId().equals(id)) {
				statusValue = value.getPaymentModeDescription();
			}

		}
		return statusValue;
	}
	
	public static Optional<PaymentModeMasterEnum> getPaymentModeDescriptionById(int id) {
		return Arrays.asList(PaymentModeMasterEnum.values()).stream().filter(e -> e.getPaymentModeMasterId() == id)
				.findFirst();
	}

}
