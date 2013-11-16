package com.zhengshuli.analysislog.domain;

public class LogLine {
	private String remoteaddr;
	private String remoteuser;
	private String timelocal;
	private String request;
	private String requestbody;
	public String getRemoteaddr() {
		return remoteaddr;
	}
	public void setRemoteaddr(String remoteaddr) {
		this.remoteaddr = remoteaddr;
	}
	public String getRemoteuser() {
		return remoteuser;
	}
	public void setRemoteuser(String remoteuser) {
		this.remoteuser = remoteuser;
	}
	public String getTimelocal() {
		return timelocal;
	}
	public void setTimelocal(String timelocal) {
		this.timelocal = timelocal;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getRequestbody() {
		return requestbody;
	}
	public void setRequestbody(String requestbody) {
		this.requestbody = requestbody;
	}
	
}
