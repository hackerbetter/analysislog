import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;


public class TestAnalysiLine {
    private final String line = "192.168.190.103 45023 - - [29/Aug/2013:00:00:11 +0800] \"GET /msgcenter/letter/findCountUnread?userno=00071964 HTTP/1.1\" http_status 200 39 \"-\" \"Java/1.6.0_38\" \"-\"upstream_addr 192.168.190.102:9999 request_time 0.138 ";
    
    @Test
    public void testGetModuleName(){
        Matcher matcher = Pattern.compile("\\[(.*)\\]").matcher(line);
        System.out.println(matcher.group());
    }
    
}
