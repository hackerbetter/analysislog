package com.zhengshuli.analysislog.parser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class LogFormatTest {
	public static final String LOTSERVER_LOG_FORMAT = "$remote_addr - $remote_user [$time_local] \"$request\" "
			+ "http_status $status $body_bytes_sent \"$http_referer\" \"$http_user_agent\" \"$http_x_forwarded_for\" "
			+ "upstream_addr $upstream_addr $request_time $request_body";
	
	@Test
	public void should_get_all_log_elements(){
		LogFormat logFormat = new LogFormat(LOTSERVER_LOG_FORMAT);
		List<String> elements = logFormat.getLogElements();
		assertThat(elements.size(), is(12));
		assertTrue(elements.contains("remoteAddr"));
	}
	
	@Test
	public void should_translate_to_regex(){
		String logFormatStr = "$remote_addr - $remote_user [$time_local] \"$request\" upstream_addr $upstream_addr";
		String expectRegex = "(?<remoteAddr>[^\\s]+) - (?<remoteUser>[^\\s]+) \\[(?<timeLocal>.+)\\] \"(?<request>[^\"]+)\" "
				+ "upstream_addr (?<upstreamAddr>[^\\s]+)";
		LogFormat logFormat = new LogFormat(logFormatStr);
		assertThat(logFormat.getRegex(), is(expectRegex));
	}
	
//	@Test
//	public void should_to_capture_group(){
//		String normalLogEle = "remoteaddr";
//		assertThat(LogFormat.toCaptureGroup(normalLogEle, ' '), is("(?<remoteaddr>[^\\s]+)"));
//		assertThat(LogFormat.toCaptureGroup(normalLogEle, ']'), is("(?<remoteaddr>.+)"));
//		assertThat(LogFormat.toCaptureGroup(normalLogEle, '\"'), is("(?<remoteaddr>[^\"]+)"));
//	}
}
