package com.zhengshuli.analysislog.parser;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.zhengshuli.analysislog.FileFixture;
import com.zhengshuli.analysislog.TestConstants;
import com.zhengshuli.analysislog.domain.LogLine;

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
		LogFormat logFormat = new LogFormat(TestConstants.DEFAULT_LOG_FORMAT_STR);
		LogLineParser parser = new LogLineParser(logFormat);;
		String line = FileFixture.loadFirstLine("/normalline.txt");
		LogLine logLine = parser .parse(line);
		assertEquals("125.88.122.103", logLine.getRemoteAddr());
		assertEquals("-", logLine.getRemoteUser());
		assertEquals(2013, logLine.getTimeLocal().getYear());
		assertEquals("POST /lotserver/RuyicaiServlet HTTP/1.1", logLine.getRequest());
		assertEquals("6ddaf4c", logLine.getRequestBody());
	}
}
