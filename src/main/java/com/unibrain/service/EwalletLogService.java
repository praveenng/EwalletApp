package com.unibrain.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.unibrain.entity.EwalletLog;

public interface EwalletLogService {
	public EwalletLog prepareEwalletLog(String activityUserId, String ewalletId, String ewalletUserName, int userType,
			LocalDateTime logDateTime, HttpServletRequest request, int applicationLogValue, String... args);

	public Boolean saveEwalletLog(EwalletLog ewalletLog);
}
