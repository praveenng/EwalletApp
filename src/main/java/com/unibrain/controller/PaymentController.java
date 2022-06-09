package com.unibrain.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.unibrain.entity.EwalletTransaction;
import com.unibrain.entity.PaymentDetails;
import com.unibrain.entity.User;
import com.unibrain.enums.PaymentStatusEnum;
import com.unibrain.enums.TransactionFlagEnums;
import com.unibrain.razorpayConstants.RazorPayConstants;
import com.unibrain.service.PaymentService;
import com.unibrain.service.UserService;

@RestController
@RequestMapping("payment")
public class PaymentController {

	@Autowired
	UserService userService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	private Environment environment;

	@PostMapping("/depositRequest")
	public ResponseEntity<Object> getDepositToEwalletRequest(@ModelAttribute("payment") PaymentDetails paymentDetails,
			HttpServletRequest request) {

		System.out.println("payment Details === " + paymentDetails.getInitiatedAmount());
		RazorpayClient client = null;
		HttpSession session = request.getSession();
		String orderId = null;
		HashMap<String, Object> map = new HashMap<>();
		User users = userService.getUserById((Integer) session.getAttribute("id"));
		String randomNumber = generateRandomNumber();
		String ebidReference = users.getEwalletId() + randomNumber;
		System.out.println("session obj=== " + session.getAttribute("ewalletId"));
		
		try {
			client = new RazorpayClient("rzp_test_ncLOWb1GgwbT1G", "Nccni0GH2Hk2KlTJ0JWcq57F");

			JSONObject options = new JSONObject();
			Integer amount = paymentDetails.getInitiatedAmount().multiply(new BigDecimal(100)).intValue();
			options.put("amount", amount);
			options.put("currency", "INR");
			options.put("receipt", ebidReference);

			JSONObject notes = new JSONObject();
			notes.put("siteName", "ewallet");
			notes.put("feePath", "deposit");
			notes.put("departmentId", "1");
			notes.put("userId", users.getId());
			notes.put("ewalletId", session.getAttribute("ewalletId").toString());

			options.put("notes", notes);
			Order order = client.Orders.create(options);
			orderId = order.get("id");
			map.put("orderId", orderId);
			map.put("users", users);
			map.put("amount", amount);
			map.put("ewalletId", session.getAttribute("ewalletId").toString());
			
			
			// save Initiated Amount by setting to paymentDetails
			paymentDetails.setEbidReference(ebidReference);
			paymentDetails.setEwalletNumber(session.getAttribute("ewalletId").toString());
			paymentDetails.setUserId(users.getId());
			paymentDetails.setLoginId(users.getLoginId());
			paymentDetails.setOrderId(orderId);
			paymentDetails.setPaymentInitiatedDate(LocalDateTime.now());
			paymentDetails.setPaymentStatus(PaymentStatusEnum.initiated.getStatusType());
			paymentDetails.setTransactionFlag(TransactionFlagEnums.Deposit.getFlag());
			paymentService.saveInitiatedEPaymentDetails(paymentDetails);

		} catch (RazorpayException e) {
			System.out.println("Raz exp == " + e);
			e.printStackTrace();
		} catch (JSONException e) {
			System.out.println("JSON Exp == " + e);
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(map, new HttpHeaders(), HttpStatus.OK);
	}

	public String generateRandomNumber() {
		String SALTCHARS = "ABCDEFGHIJKMNPQRSTUVWXYZ23456789";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 5) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String randomString = salt.toString();
		return randomString;
	}

	@RequestMapping(path = "/razorpay-gateway-response", method = RequestMethod.POST)
	public ModelAndView razorpayGatewayResponse(@RequestParam("razorpay_order_id") String orderId,
			@RequestParam("razorpay_payment_id") String paymentId,
			@RequestParam("razorpay_signature") String razorpaySignature, HttpServletRequest request,
			HttpSession session) {

		System.out.println("Order Id == " + orderId);
		System.out.println("payment Id === " + paymentId);

		Boolean isPaymentVerified = false;
		RazorpayClient client = null;
		BigDecimal paymentAmountWithComission = null;
		BigDecimal paymentComission = null;
		BigDecimal hundredInDecimal = new BigDecimal(100);
		String STATUS = null;
		BigDecimal amount = BigDecimal.ZERO;
		@SuppressWarnings("unused")
		String CHECKSUMHASH = null;
		String ebidReference = null;
		String ewalletNumber = null;

		try {
			client = new RazorpayClient("rzp_test_ncLOWb1GgwbT1G", "Nccni0GH2Hk2KlTJ0JWcq57F");

			JSONObject options = new JSONObject();
			options.put("razorpay_payment_id", paymentId);
			options.put("razorpay_order_id", orderId);
			options.put("razorpay_signature", razorpaySignature);
			isPaymentVerified = Utils.verifyPaymentSignature(options, "Nccni0GH2Hk2KlTJ0JWcq57F");
			System.out.println("isPyment verified == " + isPaymentVerified);
			if (isPaymentVerified) {

				// CAPTURE ORDER USING ORDER ID FROM RESPONSE
				Order order = client.Orders.fetch(orderId);

				// CAPTURE PAYMENT USING PAYMENT ID FROM RESPONSE
				Payment payment = client.Payments.fetch(paymentId);
				System.out.println("Payment === " + payment.toJson().toString());

				STATUS = payment.get("status");
				ebidReference = order.get("receipt");
				paymentAmountWithComission = new BigDecimal(payment.get("amount").toString()).divide(hundredInDecimal);
				// TXNDATE = payment.get("captured_at");
				JSONObject notes = order.toJson().getJSONObject("notes");
				// udf1 = notes.getString("siteName");
				// udf2 = notes.getString("departmentId");
				// udf3 = notes.getString("userId");
				// udf4 = notes.getString("feePath");
				// udf5 = notes.getString("moduleWiseId");
				ewalletNumber = notes.getString("ewalletId");
				paymentComission = new BigDecimal(payment.get("fee").toString()).divide(hundredInDecimal);
				amount = paymentAmountWithComission.subtract(paymentComission);
				CHECKSUMHASH = razorpaySignature;

				if (payment.toJson().get("status").toString().equalsIgnoreCase(RazorPayConstants.CAPTURED)) {
					STATUS = PaymentStatusEnum.sucess.getStatusType();
				} else if (payment.toJson().get("status").toString().equalsIgnoreCase(RazorPayConstants.FAILED)) {
					STATUS = PaymentStatusEnum.fail.getStatusType();
				} else {
					STATUS = PaymentStatusEnum.initiated.getStatusType();
				}
			} else {
				System.out.println("Verification Failed");
			}
		} catch (RazorpayException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		EwalletTransaction payments = paymentService.getPaymentsBasedOnEbidReference(ebidReference);
		User user = userService.getUserByEWalletId(ewalletNumber);
		payments.setPaymentTransactionReference(paymentId);
		payments.setPaymentReconciledDate(LocalDateTime.now());
		payments.setPaymentActualAmount(amount);
		payments.setPaymentCommission(paymentComission);
		payments.setPaymentAmountWithCommission(paymentAmountWithComission);
		payments.setPaymentBankStatus(PaymentStatusEnum.getStatus(STATUS));
		payments.setPaymentStatus(PaymentStatusEnum.getStatus(STATUS));
		payments.setPaymentCompletionStatus(STATUS);

		BigDecimal ewalletBalance = user.getWalletBalance() != null ? user.getWalletBalance() : BigDecimal.ZERO;
		ewalletBalance = ewalletBalance.add(amount);
		user.setWalletBalance(ewalletBalance);
		payments.setCurrentBalance(ewalletBalance);

		Boolean isUpdated = paymentService.updatePaymentDataWithEWallet(payments, user);
		System.out.println("Is updated == "+isUpdated);
		if (STATUS.equals(PaymentStatusEnum.sucess.getStatusType())) {
			return new ModelAndView("forward:/payment/paymentSuccess");
		} else {
			return new ModelAndView("forward:/payment/paymentFailure");
		}
	}

	@RequestMapping(value = "/paymentSuccess", method = RequestMethod.POST)
	public ResponseEntity<Object> paymentSuccess(HttpServletRequest request, HttpServletResponse response) {
		HttpHeaders headers = new HttpHeaders();
		String redirectUrl = null, baseUrl = null;

		String paymentEnvironment = environment.getProperty("payment.environment");
		// String contextPath =
		// environment.getProperty("server.servlet.context-path").substring(1);
		if (paymentEnvironment.equals("local")) {
			baseUrl = environment.getProperty("dev.client.url");
		} else if (paymentEnvironment.equals("production")) {
			baseUrl = "http://localhost:3000";
		}
		redirectUrl = baseUrl + "PaymentSuccessfully";

		headers.add("Location", redirectUrl);
		return new ResponseEntity<Object>(headers, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/paymentFailure", method = RequestMethod.POST)
	public ResponseEntity<Object> paymentFailure(HttpServletRequest request, HttpServletResponse response) {
		HttpHeaders headers = new HttpHeaders();
		String redirectUrl = null, baseUrl = null;
		// String encryptedTransactionId= getEncryptedValue(transactionId);
		String paymentEnvironment = environment.getProperty("payment.environment");
		String contextPath = environment.getProperty("server.servlet.context-path").substring(1);
		if (paymentEnvironment.equals("local")) {
			baseUrl = environment.getProperty("dev.client.url");
		} else if (paymentEnvironment.equals("production")) {
			baseUrl = "http://localhost:3000";
		}

		redirectUrl = baseUrl + "PaymentFailured";

		headers.add("Location", redirectUrl);
		return new ResponseEntity<Object>(headers, HttpStatus.FOUND);
	}

	@PostMapping("/generateChallan")
	public ResponseEntity<Object> generateChallan(@ModelAttribute("payment") PaymentDetails paymentDetails,
			HttpServletRequest request) {

		RazorpayClient client = null;
		String orderId = null;
		HttpSession session = request.getSession();

		HashMap<String, Object> map = new HashMap<>();

		User users = userService.getUserByEWalletId((String) session.getAttribute("ewalletId"));
		String rndString = generateRandomNumber();
		String accountNumber = users.getEwalletId() + rndString;
		Boolean checkAccountNumberExist = paymentService.checkAccountNumberExist(accountNumber);
		System.out.println(checkAccountNumberExist);
		if (checkAccountNumberExist) {
			do {
				rndString = generateRandomNumber();
				accountNumber = users.getEwalletId() + rndString;
				checkAccountNumberExist = paymentService.checkAccountNumberExist(accountNumber);
			} while (checkAccountNumberExist);
		}

		try {
			client = new RazorpayClient("rzp_test_ncLOWb1GgwbT1G", "Nccni0GH2Hk2KlTJ0JWcq57F");
			JSONObject options = new JSONObject();
			Integer amount = paymentDetails.getInitiatedAmount().multiply(new BigDecimal(100)).intValue();
			System.out.println("amount === " + amount);
			options.put("amount", amount);
			options.put("currency", "INR");
			options.put("receipt", accountNumber);
			JSONObject notes = new JSONObject();
			notes.put("siteName", "ewallet");
			notes.put("payment_ebid_reference", accountNumber);

			options.put("notes", notes);
			Order order = client.Orders.create(options);
			orderId = order.get("id");
			System.out.println("Order Id of razorPay == " + orderId);
			map.put("orderId", orderId);
			map.put("users", users);
			map.put("amount", amount);
		} catch (RazorpayException e) {
			System.out.println("Raz exp == " + e);
			e.printStackTrace();
		} catch (JSONException e) {
			System.out.println("JSON Exp == " + e);
			e.printStackTrace();
		}

		EwalletTransaction payments = new EwalletTransaction();
		payments.setPaymentTransactionReference(accountNumber);
		payments.setPaymentInitiatedAmount(paymentDetails.getInitiatedAmount());
		payments.setEwalletUserId(users.getId());
		payments.setEwalletLoginId(users.getLoginId());
		payments.setEwalletId(users.getEwalletId());
		payments.setPaymentPrimaryMode('E');
		payments.setPaymentModeId(6);
		payments.setPaymentActualAmount(paymentDetails.getInitiatedAmount());
		payments.setPaymentStatus(4);
		payments.setPaymentCompletionStatus("Challan generated");
		payments.setOrderId(orderId);

		Boolean isSaved = paymentService.saveInitiatedPayment(payments);
		System.out.println("isSaved====" + isSaved);

		return new ResponseEntity<Object>(map, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/validateRazorpayNeftPayment", method = RequestMethod.POST)
	public void validateRZPAY(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response)
			throws JSONException, IOException, RazorpayException {

		JSONObject payloadJSON = new JSONObject(payload);
		JSONObject payment = payloadJSON.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
		JSONObject notes = payment.getJSONObject("notes");

		HttpSession session = request.getSession();
		session.setAttribute("dataSourceName", notes.getString("siteName"));
		EwalletTransaction payments = paymentService
				.getPaymentByEbidReference(notes.getString("payment_ebid_reference"));

		System.err.println(payload);

		JSONObject virtualAccount = payloadJSON.getJSONObject("payload").getJSONObject("virtual_account")
				.getJSONObject("entity");
		Integer amt = (Integer) virtualAccount.get("amount_paid");
		Double dAmt = (double) amt;
		Double amount = dAmt / 100;
		BigDecimal requestAmount = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
		if (payments != null) {

			if (payments.getPaymentInitiatedAmount().compareTo(requestAmount) == 1) {
				System.err.println("Invalid amount");
			} else {
				System.err.println("Valid amount");

				payments.setPaymentTransactionReference(payment.getString(("id")));
				payments.setPaymentStatus(1);
				payments.setPaymentCompletionStatus("Success");

				Integer commission = (Integer) payment.get("fee");
				Double dCommission = (double) commission;
				Double commissionD = dCommission / 100;
				BigDecimal commissionAmount = new BigDecimal(commissionD).setScale(2, BigDecimal.ROUND_HALF_UP);
				payments.setPaymentCommission(commissionAmount);

				User user = userService.getUserByEWalletId(payments.getEwalletId());

				BigDecimal walletBalance = user.getWalletBalance().add(requestAmount);
				user.setWalletBalance(walletBalance);

				Boolean isUpdated = paymentService.updatePayment(payments, user);
				System.err.println("isUpdated===" + isUpdated);
			}

		}

	}

	@PostMapping("/getUser")
	public ResponseEntity<Object> getUser(HttpServletRequest req) {

		HttpSession session = req.getSession();
		Integer userId = (Integer) session.getAttribute("id");

		User user = userService.getUserById(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);

		return new ResponseEntity<Object>(map, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/getUserSessionValues")
	public ResponseEntity<Object> getUserSessionValues(HttpServletRequest request) throws IllegalAccessException {

		HttpHeaders headers = new HttpHeaders();

		HttpSession httpSession = request.getSession();
		Enumeration<String> enumeration = httpSession.getAttributeNames();
		HashMap<Object, Object> sessionValuesMap = new HashMap<>();
		while (enumeration.hasMoreElements()) {
			Object sessionObject;
			if ((sessionObject = enumeration.nextElement()) != null) {
				sessionValuesMap.put(sessionObject, httpSession.getAttribute(sessionObject.toString()));
			}

		}

		return new ResponseEntity<Object>(sessionValuesMap, headers, HttpStatus.CREATED);

	}
}
