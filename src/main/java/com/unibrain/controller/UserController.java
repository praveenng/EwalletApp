package com.unibrain.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unibrain.entity.BankMaster;
import com.unibrain.entity.EwalletLog;
import com.unibrain.entity.User;
import com.unibrain.enums.ApplicationLogEnum;
import com.unibrain.enums.EmailEventEnum;
import com.unibrain.enums.EnableDisableEnum;
import com.unibrain.enums.UserTypeEnum;
import com.unibrain.service.EmailAndMessageService;
import com.unibrain.service.EwalletLogService;
import com.unibrain.service.FileActivityService;
import com.unibrain.service.UserService;
import com.unibrain.util.PBKDF2WithHmacSHA256;
import com.unibrain.validator.UserValidator;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private EwalletLogService ewalletLogService;

	@Autowired
	private PBKDF2WithHmacSHA256 pbkdf2WithHmacSHA256;

	@Autowired
	private FileActivityService fileActivityService;

	@Autowired
	private EmailAndMessageService emailAndMessageService;

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	// InitBinder For User Validation
	@InitBinder("userForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}

	@GetMapping("/getMasterData")
	public ResponseEntity<Object> getMasterData() {
		Map<String, Object> responseMap = new HashMap<>();
		List<BankMaster> lBankMasters = userService.getBankMaster();
		Map<Integer, String> mBankMaster = lBankMasters.stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> x.getBankName()));
		responseMap.put("bankMaster", mBankMaster);

		logger.info("Loading master data...");
		return new ResponseEntity<Object>(responseMap, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/registration")
	public ResponseEntity<Object> userRegistration(@ModelAttribute("userForm") @Validated User user,
			BindingResult bindingResult, HttpServletRequest request) {

		Map<String, Object> responseMap = new HashMap<>();
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(responseMap, new HttpHeaders(),
				HttpStatus.OK);

		if (bindingResult.hasErrors()) {

			Map<String, String> errorMsgMap = new HashMap<>();

			List<FieldError> lFieldErrors = bindingResult.getFieldErrors();

			for (FieldError fieldErrorObject : lFieldErrors) {
				String errorMessage = messageSource.getMessage(String.valueOf(fieldErrorObject.getCode()), null,
						Locale.getDefault());
				errorMessage = (errorMessage != null) ? errorMessage : "Sorry,something went wrong";
				errorMsgMap.put(fieldErrorObject.getField(), errorMessage);
				logger.info("Error message==" + errorMessage);
			}
			responseMap.put("errorMsgMap", errorMsgMap);
			responseMap.put(bindingResult.getFieldError().getField(), bindingResult.getFieldError());
			return responseEntity;
		}

		// Uniqueness check for User id
		User userFromDB = userService.getUserByLoginId(user.getLoginId());
		if (userFromDB != null) {
			responseMap.put("errorMsg", "User Id already exists!");
			return responseEntity;
		}

		// Uniqueness check for PAN number
		Boolean panNumberExists = userService.searchPanNumber(user.getPanNumber());
		if (panNumberExists) {
			responseMap.put("errorMsg", "PAN number already exists!");
			return responseEntity;
		}

		// Uniqueness check for Adhar number
		Boolean adharNumberExists = userService.searchAdharNumber(user.getAdharNumber());
		if (adharNumberExists) {
			responseMap.put("errorMsg", "Adhar number already exists!");
			return responseEntity;
		}

		// Uniqueness check for Mobile number
		Boolean mobileNumberExists = userService.searchMobileNumber(user.getMobileNumber());
		if (mobileNumberExists) {
			responseMap.put("errorMsg", "Mobile number already exists!");
			return responseEntity;
		}

		// Uniqueness check for Email ID
		Boolean emailIDExists = userService.searchEmailID(user.getEmailId());
		if (emailIDExists) {
			responseMap.put("errorMsg", "Email ID number already exists!");
			return responseEntity;
		}

		String encodedPassword = pbkdf2WithHmacSHA256.SHA256SecurePassword(user.getPassword());
		user.setPassword(encodedPassword);
		user.setStatus(EnableDisableEnum.enable.getEnableDisableCharacterValue());
		LocalDateTime localDateTime = LocalDateTime.now();
		user.setCreatedDate(localDateTime);
		int userType = UserTypeEnum.ewallet_user.getUserTypeValue();
		user.setUserType(userType);

		EwalletLog ewalletLog = ewalletLogService.prepareEwalletLog(user.getLoginId(), null,
				user.getIndividualOrCompanyName(), userType, localDateTime, request,
				ApplicationLogEnum.user_registration.getApplicationLogValue());

		Boolean isSaved = userService.register(user, ewalletLog);
		responseMap.put("isSaved", isSaved);
		logger.info("Inside registration==" + isSaved);

		return responseEntity;
	}

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestParam("loginId") String loginId,
			@RequestParam("password") String password, @RequestParam("captcha") String captcha,
			HttpServletRequest request) {

		Map<String, Object> responseMap = new HashMap<>();
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(responseMap, new HttpHeaders(),
				HttpStatus.OK);

		HttpSession httpSession = request.getSession();
		String captchaFromSession = (String) httpSession.getAttribute("captcha");

		// if ((captcha != null && !captcha.equals(captchaFromSession))) {
		// logger.info("Invalid captcha..");
		// responseMap.put("errorMsg", "Invalid Captcha");
		// return responseEntity;
		// }

		User userFromDB = userService.getUserByLoginId(loginId);

		String errorMsg = null;

		if (userFromDB == null) {
			logger.info("Invalid User");
			errorMsg = "Invalid User";
		}

		EwalletLog ewalletLog = null;
		LocalDateTime localDateTime = LocalDateTime.now();

		if (errorMsg == null) {
			boolean isValidUser = pbkdf2WithHmacSHA256.validatePassword(password.trim(),
					userFromDB.getPassword().trim());

			if (isValidUser == false) {
				logger.info("Invalid Password");
				errorMsg = "Invalid Password";

				ewalletLog = ewalletLogService.prepareEwalletLog(userFromDB.getLoginId(), userFromDB.getEwalletId(),
						userFromDB.getIndividualOrCompanyName(), userFromDB.getUserType(), localDateTime, request,
						ApplicationLogEnum.user_login_failure.getApplicationLogValue(), errorMsg);
				ewalletLogService.saveEwalletLog(ewalletLog);
			}
		}

		if (errorMsg != null) {
			responseMap.put("errorMsg", errorMsg);
			return responseEntity;
		}

		ewalletLog = ewalletLogService.prepareEwalletLog(userFromDB.getLoginId(), userFromDB.getEwalletId(),
				userFromDB.getIndividualOrCompanyName(), userFromDB.getUserType(), localDateTime, request,
				ApplicationLogEnum.user_login.getApplicationLogValue());

		Boolean isSaved = ewalletLogService.saveEwalletLog(ewalletLog);
		logger.info("User login successful???" + isSaved);
		if (isSaved) {
			storeSessionValues(userFromDB, request);
			responseMap.put("isValidUser", true);
			responseMap.put("id", userFromDB.getId());
		} else {
			errorMsg = "Something went wrong. Please retry.";
			responseMap.put("errorMsg", errorMsg);
		}

		return responseEntity;
	}

	public void storeSessionValues(User loginUser, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("id", loginUser.getId());
		session.setAttribute("loginId", loginUser.getLoginId());
		session.setAttribute("ewalletId", loginUser.getEwalletId());
		session.setAttribute("userIndividualOrCompanyName", loginUser.getIndividualOrCompanyName());
		session.setAttribute("userType", loginUser.getUserType());
		logger.info("Setting up session values...");
	}

	@PostMapping("/uploadUserBankFile")
	public ResponseEntity<Object> uploadUserBankFile(@RequestParam("id") Integer id,
			@RequestParam("bankFile") MultipartFile bankFile, HttpServletRequest request) {

		Map<String, Object> responseMap = new HashMap<>();

		String errorMsg = null, successMsg = null;

		logger.info("ID from front end===============" + id);

		User user = userService.getUserById(id);

		String module = "BankFile";

		String bankFilePath = fileActivityService.getUserFilePath(user.getEwalletId(), module);
		logger.info("bankFilePath from service===============" + bankFilePath);

		errorMsg = fileActivityService.validateAndSaveBankFile(bankFile, bankFilePath);
		logger.info("errorMsg===============" + errorMsg);

		if (errorMsg == null) {

			LocalDateTime localDateTime = LocalDateTime.now();
			String fileName = bankFile.getOriginalFilename();

			EwalletLog ewalletLog = ewalletLogService.prepareEwalletLog(user.getLoginId(), user.getEwalletId(),
					user.getIndividualOrCompanyName(), user.getUserType(), localDateTime, request,
					ApplicationLogEnum.user_bank_file_upload.getApplicationLogValue(), fileName);

			Boolean isBankFileUploaded = userService.updateUserBankFileName(id, fileName, ewalletLog);

			if (isBankFileUploaded) {
				successMsg = "File uploaded successfully!";
				responseMap.put("successMsg", successMsg);
			} else {
				File file = new File(bankFilePath + fileName);
				if (file.exists()) {
					file.delete();
				}
				errorMsg = "Failed to upload file!";
				responseMap.put("errorMsg", errorMsg);
			}
		}

		return new ResponseEntity<Object>(responseMap, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/getUser/{encId}")
	public User getUser(@PathVariable("encId") String encId) {

		Integer id = Integer.parseInt(getDecryptedValue(encId));
		User user = userService.getUserById(id);
		return user;
	}

	@GetMapping("/getOtp/{encUserId}")
	public ResponseEntity<Object> getOTPSendEmailAndSMS(@PathVariable String encUserId, HttpServletRequest request) {

		Map<String, Object> responseMap = new HashMap<>();
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(responseMap, new HttpHeaders(),
				HttpStatus.OK);

		HttpSession session = request.getSession();
		Integer userId = Integer.parseInt(getDecryptedValue(encUserId));
		User user = userService.getUserById(userId);

		Random random = new Random();
		String generatedOtp = String.format("%04d", random.nextInt(10000));

		session.removeAttribute("otp");

		if (generatedOtp != null && generatedOtp.length() == 4) {

			logger.info("Inside message sender...");

			// TO Send SMS through API
			String key = "f86a7b6e-38bd-4fe7-ba72-b980d9019624";
			String senderId = "EWIZRD";
			String visitLink = "euniwizarde.com";
			String text = "Dear Customer, " + generatedOtp + " is the OTP for login to " + visitLink
					+ " Do not share with anyone by any means. This is confidential and to be used by you only.";
			String mobileNo = user.getMobileNumber().trim();

			URLConnection myURLConnection = null;
			URL myURL = null;
			BufferedReader reader = null;
			// Send SMS API
			String smsUrl = "https://teleduce.corefactors.in/sendsms/?";
			// Prepare parameter string
			StringBuilder sbPostData = new StringBuilder(smsUrl);
			sbPostData.append("key=" + key);
			sbPostData.append("&text=" + text);
			sbPostData.append("&route=" + 0);
			sbPostData.append("&from=" + senderId);
			sbPostData.append("&to=" + mobileNo);
			// final string
			smsUrl = sbPostData.toString();
			try {
				// prepare connection
				myURL = new URL(smsUrl);
				myURLConnection = myURL.openConnection();
				myURLConnection.connect();
				reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
				reader.close();
				logger.info("Message sent sucessfully..");
			} catch (IOException e) {
				e.printStackTrace();
				responseMap.put("successErrorMsg", "Something went wrong!");
				responseMap.put("isOTPgenerated", false);
				return responseEntity;
			}

			try {
				emailAndMessageService.buildEmailContent(user.getEmailId().trim(),
						EmailEventEnum.OTP_based_login.getEmailEventValue(), request, String.valueOf(user.getId()),
						generatedOtp, visitLink);
			} catch (Exception e) {
				e.printStackTrace();
			}

			session.setAttribute("otp", generatedOtp);
			responseMap.put("successErrorMsg", "OTP Sent SuccessFully to your Mobile number and E-mail");
			responseMap.put("isOTPgenerated", true);
		} else {
			responseMap.put("successErrorMsg", "Illegal Access");
			responseMap.put("isOTPgenerated", false);
		}

		return responseEntity;
	}

	@PostMapping("/validateOtpLogin/{encUserId}")
	public ResponseEntity<Object> validateOtpLogin(@PathVariable String encUserId, HttpServletRequest request) {

		Map<String, Object> responseMap = new HashMap<>();
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(responseMap, new HttpHeaders(),
				HttpStatus.OK);

		HttpSession session = request.getSession();
		Integer id = Integer.parseInt(getDecryptedValue(encUserId));
		Integer sessionId = Integer.parseInt(session.getAttribute("id").toString());
		User user = userService.getUserById(id);

		String errorMsg = null;
		responseMap.put("isOTPValidated", false);

		Boolean isValid = false;
		isValid = id.equals(sessionId);

		if (!isValid) {
			session.removeAttribute("otp");
			errorMsg = "Logged in User is Not Valid";
			responseMap.put("successErrorMsg", errorMsg);
			return responseEntity;
		}

		isValid = user.getEmailId().equals(request.getParameter("emailId"));

		if (!isValid) {
			session.removeAttribute("otp");
			errorMsg = "Registered Email is Not Matching With Entered Email in OTP Screen";
			responseMap.put("successErrorMsg", errorMsg);
			return responseEntity;
		}

		isValid = user.getMobileNumber().equals(request.getParameter("mobileNumber"));

		if (!isValid) {
			session.removeAttribute("otp");
			errorMsg = "Registered Mobile Number is Not Matching With Entered Mobile Number in OTP Screen";
			responseMap.put("successErrorMsg", errorMsg);
			return responseEntity;
		}

		isValid = session.getAttribute("otp").equals(request.getParameter("otp"));

		if (isValid) {
			responseMap.put("isOTPValidated", true);
			return responseEntity;
		} else {
			session.removeAttribute("otp");
			errorMsg = "OTP is Invalid, Please Re-send OTP and Login";
			responseMap.put("successErrorMsg", errorMsg);
			return responseEntity;
		}
	}

}
