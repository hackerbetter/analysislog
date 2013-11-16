package com.zhengshuli.analysislog.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFormat {
	public static final String EMPTY = "-";
	public static final String LOG_ELE_PREFIX = "$";
	public static final String LOG_ELE_REGEX = "\\$([a-z_]+)";
	public static final String ANY_CHAR_EXCEPT_QUOTE = "[^\"]+";
	public static final String ANY_CHAR_EXCEPT_WHITESPACE = "[^\\\\s]+";
	public static final String ANY_CHARACTOR = ".+";
	
	private List<String> logElements;
	
	/** the regex to capture all log elements */
	private String regex;
	
	public LogFormat(String logFormatStr){
		traslateRegexAndCollectLogElements(logFormatStr);
	}

	public List<String> getLogElements() {
		return logElements;
	}

	public String getRegex() {
		return regex;
	}

	private void traslateRegexAndCollectLogElements(String logFormatStr) {
		String regex = logFormatStr;
		regex = escape(regex);
		Pattern pattern = Pattern.compile(LOG_ELE_REGEX);
		Matcher matcher = pattern.matcher(logFormatStr);
		List<String> logElements = new ArrayList<>();
		while (matcher.find()) {
			String logElement = matcher.group(1).replace("_", "");
			String captureGroup = toCaptureGroup(logElement, 
					logFormatStr.length() == matcher.end() ? '\0' : logFormatStr.charAt(matcher.end()));
			regex = regex.replaceFirst("\\" + matcher.group(0), captureGroup);
			logElements.add(logElement);
		}
		this.logElements = logElements;
		System.out.println(regex);
		this.regex = regex;
	}

	private String escape(String regex) {
		return regex.replace("[", "\\[").replace("]", "\\]");
	}

	/**
	 * replace 'remoteaddr' to (?<remoteaddr>[^\\s]+)
	 * replace 'timelocal end with ]' to (?<timelocal>.+)
	 * replace 'request end with "' to (?<request>[^\"]+)
	 * @param logElement
	 * @return
	 */
	private String toCaptureGroup(String logElement,char nextChar) {
		StringBuilder captureGroup = new StringBuilder();
		captureGroup.append("(?<").append(logElement).append(">");
		switch (nextChar) {
		case ']':
			captureGroup.append(ANY_CHARACTOR);
			break;
		case '\"':
			captureGroup.append(ANY_CHAR_EXCEPT_QUOTE);
			break;
		default:
			captureGroup.append(ANY_CHAR_EXCEPT_WHITESPACE);
			break;
		}
		return captureGroup.append(")").toString();
	}
	
	
}
