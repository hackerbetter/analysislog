package com.zhengshuli.analysislog.domain;

public class LogLine {
	private String remoteAddr;
	private String remoteUser;
	private String timeLocal;
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
    public String getTimeLocal() {
        return timeLocal;
    }
    public void setTimeLocal(String timeLocal) {
        this.timeLocal = timeLocal;
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
