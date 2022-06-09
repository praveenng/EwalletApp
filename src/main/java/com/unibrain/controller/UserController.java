package com.unibrain.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unibrain.entity.BankMaster;
import com.unibrain.entity.EwalletLog;
import com.unibrain.entity.User;
import com.unibrain.enums.ApplicationLogEnum;
import com.unibrain.enums.EnableDisableEnum;
import com.unibrain.enums.UserTypeEnum;
import com.unibrain.service.EwalletLogService;
import com.unibrain.service.FileActivityService;
import com.unibrain.service.UserService;
import com.unibrain.util.PBKDF2WithHmacSHA256;
import com.unibrain.validator.UserValidator;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private EwalletLogService ewalletLogService;

	@Autowired
	private PBKDF2WithHmacSHA256 pbkdf2WithHmacSHA256;

	@Autowired
	private FileActivityService fileActivityService;

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
	public ResponseEntity<Object> login(@ModelAttribute("userLogin") User user, HttpServletRequest request) {

		Map<String, Object> responseMap = new HashMap<>();

		User userFromDB = userService.getUserByLoginId(user.getLoginId());

		String errorMsg = null;

		if (userFromDB == null) {
			logger.info("Invalid User");
			errorMsg = "Invalid User";
		}

		EwalletLog ewalletLog = null;
		LocalDateTime localDateTime = LocalDateTime.now();

		if (errorMsg == null) {
			boolean isValidUser = pbkdf2WithHmacSHA256.validatePassword(user.getPassword().trim(),
					userFromDB.getPassword().trim());

			if (isValidUser == false) {
				logger.info("Invalid Password");
				System.out.println("Invalid Password");
				errorMsg = "Invalid Password";

				ewalletLog = ewalletLogService.prepareEwalletLog(userFromDB.getLoginId(), userFromDB.getEwalletId(),
						userFromDB.getIndividualOrCompanyName(), userFromDB.getUserType(), localDateTime, request,
						ApplicationLogEnum.user_login_failure.getApplicationLogValue(), errorMsg);
				ewalletLogService.saveEwalletLog(ewalletLog);
			}
		}

		if (errorMsg != null) {
			responseMap.put("errorMsg", errorMsg);
			return new ResponseEntity<Object>(responseMap, new HttpHeaders(), HttpStatus.OK);
		}

		ewalletLog = ewalletLogService.prepareEwalletLog(userFromDB.getLoginId(), userFromDB.getEwalletId(),
				userFromDB.getIndividualOrCompanyName(), userFromDB.getUserType(), localDateTime, request,
				ApplicationLogEnum.user_login.getApplicationLogValue());

		Boolean isSaved = ewalletLogService.saveEwalletLog(ewalletLog);
		logger.info("User login successful???" + isSaved);
		if (isSaved) {
			storeSessionValues(userFromDB, request);
			responseMap.put("success", true);
		} else {
			errorMsg = "Something went wrong. Please retry.";
			responseMap.put("errorMsg", errorMsg);
		}

		return new ResponseEntity<Object>(responseMap, new HttpHeaders(), HttpStatus.OK);
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
			@RequestParam("bankFile") MultipartFile bankFile, HttpServletRequest request,
			HttpServletResponse response) {

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
				errorMsg = "Failed to upload file!";
				responseMap.put("errorMsg", errorMsg);
			}
		}

		return new ResponseEntity<Object>(responseMap, new HttpHeaders(), HttpStatus.OK);

	}
}
