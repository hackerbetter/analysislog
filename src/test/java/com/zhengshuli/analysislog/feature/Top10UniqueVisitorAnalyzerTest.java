package com.zhengshuli.analysislog.feature;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.zhengshuli.analysislog.FileFixture;
import com.zhengshuli.analysislog.TestConstants;
import com.zhengshuli.analysislog.feature.view.Top10UniqueVisitorsView.Top10UniqueVisitor;
import com.zhengshuli.analysislog.parser.LogLineParser;

public class Top10UniqueVisitorAnalyzerTest {
    private Top10UniqueVisitorsAnalyzer analyzer = new Top10UniqueVisitorsAnalyzer();
    
    @Test
    public void should_get_correct_top10(){
        LogLineParser logLineParser = new LogLineParser(TestConstants.DEFAULT_LOG_FORMAT, analyzer);
        
        List<String> lines = FileFixture.loadLines("/top10.txt");
        for (String line : lines){
            logLineParser.processLine(line);
        }
        List<Top10UniqueVisitor> visitors = analyzer.toView().result();
        
        assertEquals(4, visitors.size());
        
        Top10UniqueVisitor top1 = visitors.get(0);
        assertEquals(new Integer(11), top1.getVisitCount());
        assertEquals("125.88.122.102", top1.getIp());
        
        Top10UniqueVisitor top4 = visitors.get(3);
        assertEquals(new Integer(1), top4.getVisitCount());
        assertEquals("125.88.122.101", top4.getIp());
    }
}
