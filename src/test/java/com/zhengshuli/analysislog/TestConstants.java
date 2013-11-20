package com.zhengshuli.analysislog;

import com.zhengshuli.analysislog.parser.LogFormat;

public class TestConstants {
    public static final String DEFAULT_LOG_FORMAT_STR = "$remote_addr - $remote_user [$time_local] \"$request\" "
            + "http_status $status $body_bytes_sent \"$http_referer\" \"$http_user_agent\" \"$http_x_forwarded_for\" "
            + "upstream_addr $upstream_addr $request_time $request_body";
    
    public static final LogFormat DEFAULT_LOG_FORMAT = new LogFormat(DEFAULT_LOG_FORMAT_STR);
}
