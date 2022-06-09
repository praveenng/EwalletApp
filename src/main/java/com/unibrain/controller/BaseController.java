package com.unibrain.controller;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.unibrain.enums.UserTypeEnum;
import com.unibrain.util.ParamEncryptDecryptUtil;

@Controller
public class BaseController {

	@Autowired
	protected ParamEncryptDecryptUtil encryptDecryptUtil;

	public String getBaseUrl(HttpServletRequest request) {
		return null;
	}

	public String getEncryptedValue(String valueForEncode) {
		final String KEY = "ffea52a9ae99bbcb";
		String encodedValue = encryptDecryptUtil.aesEncrypt(valueForEncode, KEY);
		encodedValue = ((encodedValue.contains("/") ? encodedValue.replace("/", "92AS") : encodedValue));
		return encodedValue;
	}

	public String getDecryptedValue(String valueForDecode) {

		valueForDecode = ((valueForDecode.contains("92AS") ? valueForDecode.replace("92AS", "/") : valueForDecode));
		Pattern regex = Pattern.compile("^[a-zA-Z0-9-=+/_-]*");
		Boolean isPatternMatched = regex.matcher(valueForDecode).matches() ? true : false;
		Boolean isLengthMatched = (valueForDecode != null && (valueForDecode.length() == 24
				|| valueForDecode.length() == 44 || valueForDecode.length() == 64 || valueForDecode.length() == 88
				|| valueForDecode.length() == 108 || valueForDecode.length() == 128));
		String decodedValue = null;
		if (isPatternMatched && isLengthMatched) {

			final String KEY = "ffea52a9ae99bbcb";
			decodedValue = encryptDecryptUtil.aesDecrypt(valueForDecode, KEY);
		}

		if (decodedValue == null) {
			throw new RuntimeException("Illegal Parameter");
		}
		return decodedValue;
	}

	// This method can be used to validate get method with extra parameter
	// If only path variables used in get method then we can use this method to
	// avoid extra query string
	public Boolean checkIsExtraParameterExist(HttpServletRequest request) {
		String queryString = request.getQueryString();
		Map<String, String[]> params = request.getParameterMap();
		if (params.size() >= 1 || queryString != null) {
			return true;
		} else {
			return false;
		}
	}

	// Validate ewallet user session values
	public Boolean ewalletUserTypeCheck(HttpServletRequest request) {

		HttpSession httpSession = request.getSession();
		Integer userType = (Integer) httpSession.getAttribute("userType");
		String ewalletUserId = (String) httpSession.getAttribute("ewalletUserId");
		Boolean isEwalletUser = (userType != null && userType.equals(UserTypeEnum.ewallet_user.getUserTypeValue()))
				? true : false;
		if (isEwalletUser && ewalletUserId != null && !ewalletUserId.isEmpty()) {
			return true;
		}
		return false;
	}
}
