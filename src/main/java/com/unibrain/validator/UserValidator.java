package com.unibrain.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.unibrain.entity.User;

public class UserValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {

		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loginId", "required.loginId", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required.confirmPassword",
				"Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "individualOrCompanyName", "required.individualOrCompanyName",
				"Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "required.mobileNumber",
				"Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "required.emailId", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "panNumber", "required.panNumber", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adharNumber", "required.adharNumber",
				"Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber", "required.accountNumber",
				"Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountHolderName", "required.accountHolderName",
				"Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankId", "required.bankId", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ifsc", "required.ifsc", "Field name is required.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchName", "required.branchName",
				"Field name is required.");

		User user = (User) target;
		Pattern pattern = null;

		ValidatorPatternUtil validatorPatternUtil = new ValidatorPatternUtil();

		// Login ID pattern validation
		pattern = validatorPatternUtil.getLoginIdPattern();
		if (!(pattern.matcher(user.getMobileNumber()).matches())) {
			errors.rejectValue("loginId", "user.loginId.invalid");
		}

		// Password validation
		pattern = validatorPatternUtil.getPasswordPattern();

		if (!(pattern.matcher(user.getPassword()).matches())) {
			errors.rejectValue("password", "user.password.invalid");
		}
		if (!(pattern.matcher(user.getConfirmPassword()).matches())) {
			errors.rejectValue("confirmPassword", "user.confirmPassword.invalid");
		}
		// Password and retype password match
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			errors.rejectValue("confirmPassword", "user.confirmPassword.invalid");
		}

		// mobile number pattern validation
		pattern = validatorPatternUtil.getForiegnMobileNumberPattern();
		if (!(pattern.matcher(user.getMobileNumber()).matches())) {
			errors.rejectValue("mobileNumber", "user.mobileNumber.invalid");
		}

		// email id pattern validation
		pattern = validatorPatternUtil.getEmailIdPattern();
		if (!(pattern.matcher(user.getEmailId()).matches())) {
			errors.rejectValue("emailId", "user.emailId.invalid");
		}

		// Pan number pattern validation
		pattern = validatorPatternUtil.getPanNumberPattern();

		if (!(pattern.matcher(user.getPanNumber()).matches())) {
			errors.rejectValue("panNumber", "user.panNumber.invalid");
		}

		// // Adhar number pattern validation
		// Pattern adharPattern = validatorPatternUtil.;
		//
		// if (!(panNumberPattern.matcher(user.getPanNumber()).matches())) {
		// errors.rejectValue("panNumber", "departmentUser.panNumber.invalid");
		// }

		// Acc No validation
		pattern = validatorPatternUtil.getBankAccountNumberPattern();

		if (!(pattern.matcher(user.getAccountNumber()).matches())) {
			errors.rejectValue("accountNumber", "user.accountNumber.invalid");
		}

		// IFSC code validation
		pattern = validatorPatternUtil.getIFSCPattern();

		if (!(pattern.matcher(user.getIfsc()).matches())) {
			errors.rejectValue("ifsc", "user.ifsc.invalid");
		}

		// Branch name validation
		pattern = validatorPatternUtil.getTextAreaOnePattern();

		if (!(pattern.matcher(user.getBranchName()).matches())) {
			errors.rejectValue("branchName", "user.branchName.invalid");
		}

		// Branch name validation
		if (!(pattern.matcher(user.getAccountHolderName()).matches())) {
			errors.rejectValue("accountHolderName", "user.accountHolderName.invalid");
		}

		// Individual/Company name validation
		if (!(pattern.matcher(user.getIndividualOrCompanyName()).matches())) {
			errors.rejectValue("individualOrCompanyName", "user.individualOrCompanyName.invalid");
		}
	}
}
