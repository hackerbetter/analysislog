package com.zhengshuli.analysislog.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;

import com.zhengshuli.analysislog.domain.LogLine;

public class LogLineParser {
	private Pattern parsePattern;
	private LogFormat logFormat;
	
	public LogLineParser(LogFormat logFormat){
		this.logFormat = logFormat;
		parsePattern = Pattern.compile(logFormat.getRegex());
	}
	
	public LogLine parseToLogLine(String line){
		Matcher matcher = parsePattern.matcher(line);
		if (matcher.find()) {
			LogLine logLine = new LogLine();
			List<String> logElements = logFormat.getLogElements();
			for(String logElement : logElements){
			    String value = matcher.group(logElement);
			    try {
                    PropertyUtils.setProperty(logLine, logElement, value);
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                catch (NoSuchMethodException e) {
                    // TODO fix this 
                }
			}
			return logLine;	
		}
		return null;
	}
}
