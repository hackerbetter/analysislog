package com.zhengshuli.analysislog.parser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.io.LineProcessor;
import com.zhengshuli.analysislog.domain.LogLine;
import com.zhengshuli.analysislog.feature.Top10UniqueVistorsAnalyzer;

public class LogLineParser implements LineProcessor{
	private Pattern parsePattern;
	private LogFormat logFormat;
	
	private List<LogLineAnalyzer> logLineAnalyzers = new ArrayList<LogLineAnalyzer>();
	
	public LogLineParser(LogFormat logFormat){
		this.logFormat = logFormat;
		parsePattern = Pattern.compile(logFormat.getRegex());
		
		registerLogLineAnalyzer();
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

    @Override
    public boolean processLine(String line) throws IOException {
        LogLine logLine = parseToLogLine(line);
        for(LogLineAnalyzer processor: logLineAnalyzers) {
            processor.process(logLine);
        }
        return true;
    }

    @Override
    public Object getResult() {
        return null;
    }
    
    private void registerLogLineAnalyzer(){
        logLineAnalyzers.add(new Top10UniqueVistorsAnalyzer());
    }
}
