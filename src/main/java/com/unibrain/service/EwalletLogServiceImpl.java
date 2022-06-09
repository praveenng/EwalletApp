package com.unibrain.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.unibrain.dao.EwalletLogDao;
import com.unibrain.entity.EwalletLog;

@Service
public class EwalletLogServiceImpl implements EwalletLogService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private EwalletLogDao ewalletDao;

	@Override
	public Boolean saveEwalletLog(EwalletLog ewalletLog) {
		return ewalletDao.saveEwalletLog(ewalletLog);
	}

	@Override
	public EwalletLog prepareEwalletLog(String activityUserId, String ewalletId, String ewalletUserName, int userType,
			LocalDateTime logDateTime, HttpServletRequest request, int applicationLogValue, String... args) {
		EwalletLog ewalletLog = new EwalletLog();
		ewalletLog.setActivityUserId(activityUserId);
		ewalletLog.setEwalletId(ewalletId);
		ewalletLog.setEwalletUserName(ewalletUserName);
		ewalletLog.setUserType(userType);
		ewalletLog.setLogDateTime(logDateTime);
		ewalletLog.setIp(truncate(getClientIpAddr(request), 100));

		String applicationLogSubject = null;
		String applicationLogMatter = null;

		switch (applicationLogValue) {
		case 1:
			applicationLogSubject = messageSource.getMessage("log.UserRegistration.subject", null, null);
			applicationLogMatter = messageSource.getMessage("log.UserRegistration.matter", null, null);
			clientOsBrowser(request, ewalletLog);
			break;

		case 2:
			applicationLogSubject = messageSource.getMessage("log.UserLogin.subject", null, null);
			applicationLogMatter = messageSource.getMessage("log.UserLogin.matter", null, null);
			clientOsBrowser(request, ewalletLog);
			break;

		case 3:
			applicationLogSubject = messageSource.getMessage("log.UserLoginFailure.subject", null, null);
			applicationLogMatter = messageSource.getMessage("log.UserLoginFailure.matter", new String[] { args[0] },
					null);
			clientOsBrowser(request, ewalletLog);
			break;
			
		case 4:
			applicationLogSubject = messageSource.getMessage("log.UploadUserBankFile.subject", null, null);
			applicationLogMatter = messageSource.getMessage("log.UploadUserBankFile.matter", new String[] { args[0] },
					null);
			clientOsBrowser(request, ewalletLog);
			break;

		}
		ewalletLog.setLogSubject(applicationLogSubject);
		ewalletLog.setLogMatter(applicationLogMatter);

		return ewalletLog;
	}

	public void clientOsBrowser(HttpServletRequest request, EwalletLog ewalletLog) {
		int maxLength = 50;
		ewalletLog.setOs(truncate(getClientOS(request), maxLength));
		ewalletLog.setBrowser(truncate(getClientBrowser(request), maxLength));
		String sessionId = truncate(request.getSession().getId(), maxLength);
		ewalletLog.setSessionId(sessionId);
	}

	// =================truncate=======================//
	public static String truncate(String value, int length) {
		// Ensure String length is longer than requested size.
		if (value.length() > length) {
			return value.substring(0, length);
		} else {
			return value;
		}
	}

	public String getClientBrowser(HttpServletRequest request) {
		final String browserDetails = request.getHeader("User-Agent");
		final String user = browserDetails.toLowerCase();
		String browser = "";

		// ===============Browser===========================
		if (user.contains("msie")) {
			String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
			browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (user.contains("safari") && user.contains("version")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-"
					+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (user.contains("opr") || user.contains("opera")) {
			if (user.contains("opera"))
				browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-"
						+ (browserDetails.substring(browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
			else if (user.contains("opr"))
				browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/", "-"))
						.replace("OPR", "Opera");
		} else if (user.contains("chrome")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
		} else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
			// browser=(userAgent.substring(userAgent.indexOf("MSIE")).split("
			// ")[0]).replace("/", "-");
			browser = "Netscape-?";

		} else if (user.contains("firefox")) {
			browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
		} else if (user.contains("rv")) {
			browser = "IE";
		} else {
			browser = "UnKnown, More-Info: " + browserDetails;
		}

		return browser;
	}

	public String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}

	public String getClientOS(HttpServletRequest request) {
		final String operatingSystemDetails = request.getHeader("User-Agent");

		// =================OS=======================
		final String lowerCaseOS = operatingSystemDetails.toLowerCase();
		if (lowerCaseOS.contains("windows")) {
			return "Windows";
		} else if (lowerCaseOS.contains("mac")) {
			return "Mac";
		} else if (lowerCaseOS.contains("x11")) {
			return "Unix";
		} else if (lowerCaseOS.contains("android")) {
			return "Android";
		} else if (lowerCaseOS.contains("iphone")) {
			return "IPhone";
		} else {
			return "UnKnown, More-Info: " + operatingSystemDetails;
		}
	}

}
