package com.zhengshuli.analysislog.parser;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.zhengshuli.analysislog.FileFixture;
import com.zhengshuli.analysislog.domain.LogLine;
import com.zhengshuli.analysislog.parser.LogFormat;
import com.zhengshuli.analysislog.parser.LogLineParser;

public class LogLineParserTest {
	@Test
	public void should_get_time_local(){
		String line = FileFixture.loadFirstLine("/normalline.txt");
		String timeLocal = null;
		StringBuilder regexp = new StringBuilder();
		regexp.append("\\[(?<timelocal>.+)\\]");
		Pattern pattern = Pattern.compile(regexp.toString());
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			timeLocal = matcher.group("timelocal");	
		}
		assertEquals("08/Nov/2013:00:00:01 +0800", timeLocal);
	}
	
	@Test
	public void should_get_logline(){
		LogFormat logFormat = new LogFormat(LogFormatTest.LOTSERVER_LOG_FORMAT);
		LogLineParser parser = new LogLineParser(logFormat);;
		String line = FileFixture.loadFirstLine("/normalline.txt");
		LogLine logLine = parser .parseToLogLine(line);
		assertEquals("125.88.122.103", logLine.getRemoteAddr());
		assertEquals("-", logLine.getRemoteUser());
		assertEquals("08/Nov/2013:00:00:01 +0800", logLine.getTimeLocal());
		assertEquals("POST /lotserver/RuyicaiServlet HTTP/1.1", logLine.getRequest());
		assertEquals("6ddaf4c", logLine.getRequestBody());
	}
}
