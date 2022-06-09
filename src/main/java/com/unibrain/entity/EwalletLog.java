package com.unibrain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ewallet_logs")
public class EwalletLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "serial")
	private Integer id;

	@Column(name = "activity_user_id")
	private String activityUserId;
	
	@Column(name = "ewallet_id")
	private String ewalletId;

	@Column(name = "ewallet_user_name")
	private String ewalletUserName;
	
	@Column(name = "user_type")
	private Integer userType;

	@Column(name = "log_date_time")
	private LocalDateTime logDateTime;

	@Column(name = "log_subject")
	private String logSubject;

	@Column(name = "log_matter")
	private String logMatter;

	@Column(name = "ip")
	private String ip;

	@Column(name = "browser")
	private String browser;

	@Column(name = "os")
	private String os;

	@Column(name = "session_id")
	private String sessionId;

	public EwalletLog() {

	}

	public EwalletLog(Integer id, String activityUserId, String ewalletId, String ewalletUserName, Integer userType,
			LocalDateTime logDateTime, String logSubject, String logMatter, String ip, String browser, String os,
			String sessionId) {
		super();
		this.id = id;
		this.activityUserId = activityUserId;
		this.ewalletId = ewalletId;
		this.ewalletUserName = ewalletUserName;
		this.userType = userType;
		this.logDateTime = logDateTime;
		this.logSubject = logSubject;
		this.logMatter = logMatter;
		this.ip = ip;
		this.browser = browser;
		this.os = os;
		this.sessionId = sessionId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityUserId() {
		return activityUserId;
	}

	public void setActivityUserId(String activityUserId) {
		this.activityUserId = activityUserId;
	}

	public String getEwalletId() {
		return ewalletId;
	}

	public void setEwalletId(String ewalletId) {
		this.ewalletId = ewalletId;
	}

	public String getEwalletUserName() {
		return ewalletUserName;
	}

	public void setEwalletUserName(String ewalletUserName) {
		this.ewalletUserName = ewalletUserName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public LocalDateTime getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(LocalDateTime logDateTime) {
		this.logDateTime = logDateTime;
	}

	public String getLogSubject() {
		return logSubject;
	}

	public void setLogSubject(String logSubject) {
		this.logSubject = logSubject;
	}

	public String getLogMatter() {
		return logMatter;
	}

	public void setLogMatter(String logMatter) {
		this.logMatter = logMatter;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
