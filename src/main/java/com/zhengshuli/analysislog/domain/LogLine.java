package com.zhengshuli.analysislog.domain;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LogLine {
    private static final DateTimeFormatter REQUEST_TIME_FORMATTER = DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.ENGLISH);
    
	private String remoteAddr;
	private String remoteUser;
	private DateTime timeLocal;
	private String request;
	private String requestBody;
	private String requestTime;

    public String getRequestTime() {
        return requestTime;
    }
    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
    public String getRemoteAddr() {
        return remoteAddr;
    }
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    public String getRemoteUser() {
        return remoteUser;
    }
    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }
    public DateTime getTimeLocal() {
        return timeLocal;
    }
    public void setTimeLocal(String timeLocal) {
        this.timeLocal = REQUEST_TIME_FORMATTER.parseDateTime(timeLocal);
    }
    public String getRequest() {
        return request;
    }
    public void setRequest(String request) {
        this.request = request;
    }
    public String getRequestBody() {
        return requestBody;
    }
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
	
}
