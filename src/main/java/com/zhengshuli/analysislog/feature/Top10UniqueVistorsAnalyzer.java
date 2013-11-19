package com.zhengshuli.analysislog.feature;

import java.util.Iterator;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;
import com.zhengshuli.analysislog.domain.LogLine;
import com.zhengshuli.analysislog.feature.dto.Top10UniqueVisitorsDTO;
import com.zhengshuli.analysislog.parser.LogLineAnalyzer;

public class Top10UniqueVistorsAnalyzer implements LogLineAnalyzer{
    private Multiset<String> uniqueVistors = TreeMultiset.create();
    
    @Override
    public void process(LogLine logLine) {
        uniqueVistors.add(logLine.getRemoteAddr());
    }
    
    @Override
    public Top10UniqueVisitorsDTO result() {
        uniqueVistors = Multisets.copyHighestCountFirst(uniqueVistors);
        Iterator<String> visitors = uniqueVistors.elementSet().iterator();
        int i = 0;
        Top10UniqueVisitorsDTO dto = new Top10UniqueVisitorsDTO();
        while(visitors.hasNext() && i < 10){
            String ip = visitors.next();
            int visitCount = uniqueVistors.count(ip);
            dto.add(ip, visitCount);
            i++;
        }
        return dto;
    }
}
