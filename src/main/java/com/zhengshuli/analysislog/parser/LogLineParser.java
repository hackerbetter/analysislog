package com.zhengshuli.analysislog.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zhengshuli.analysislog.domain.LogLine;

public class LogLineParser {
	private Pattern parsePattern;
	private LogFormat logFormat;
	
	public LogLineParser(LogFormat logFormat){
		this.logFormat = logFormat;
		parsePattern = Pattern.compile(logFormat.getRegex());
	}
	
	public LogLine parseToLogLine(String line, LogFormat logFormat){
		Matcher matcher = parsePattern.matcher(line);
		if (matcher.find()) {
			LogLine logLine = new LogLine();
			logLine.setRemoteaddr(matcher.group("remoteaddr"));
			logLine.setRemoteuser(matcher.group("remoteuser"));
			logLine.setTimelocal(matcher.group("timelocal"));
			logLine.setRequest(matcher.group("request"));
			logLine.setRequestbody(matcher.group("requestbody"));
			return logLine;	
		}
		return null;
	}
}
