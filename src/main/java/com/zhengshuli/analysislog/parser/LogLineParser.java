package com.zhengshuli.analysislog.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.io.LineProcessor;
import com.zhengshuli.analysislog.domain.LogLine;
import com.zhengshuli.analysislog.feature.Top10UniqueVisitorsAnalyzer;
import com.zhengshuli.analysislog.reporter.IView;

public class LogLineParser implements LineProcessor<List<IView>>{
	private Pattern parsePattern;
	private LogFormat logFormat;
	
	private List<ILogLineAnalyzer> logLineAnalyzers = new ArrayList<ILogLineAnalyzer>();
	
	public LogLineParser(LogFormat logFormat){
	    setLogFormat(logFormat);
		registerDefaultLogLineAnalyzer();
	}
	
	public LogLineParser(LogFormat logFormat, ILogLineAnalyzer... customAnalyzers){
	    setLogFormat(logFormat);
	    for(ILogLineAnalyzer analyzer: customAnalyzers) {
	        logLineAnalyzers.add(analyzer);
	    }
    }
	
	public LogLine parse(String line){
		Matcher matcher = parsePattern.matcher(line);
		if (matcher.find()) {
			LogLine logLine = new LogLine();
			logLine.setRemoteAddr(matcher.group("remoteAddr"));
			logLine.setRemoteUser(matcher.group("remoteUser"));
			logLine.setRequest(matcher.group("request"));
			logLine.setTimeLocal(matcher.group("timeLocal"));
			logLine.setRequestBody(matcher.group("requestBody"));
			logLine.setRequestTime(matcher.group("requestTime"));
			return logLine;	
		}
		return null;
	}

    @Override
    public boolean processLine(String line) {
        LogLine logLine = parse(line);
        for(ILogLineAnalyzer analyzer: logLineAnalyzers) {
            analyzer.analyze(logLine);
        }
        return true;
    }

    @Override
    public List<IView> getResult() {
        List<IView> views = new ArrayList<IView>();
        for(ILogLineAnalyzer analyzer: logLineAnalyzers) {
            views.add(analyzer.toView());
        }
        return views;
    }
    
    private void setLogFormat(LogFormat logFormat) {
        this.logFormat = logFormat;
        parsePattern = Pattern.compile(logFormat.getRegex());
    }
    
    private void registerDefaultLogLineAnalyzer(){
        logLineAnalyzers.add(new Top10UniqueVisitorsAnalyzer());
    }
}
