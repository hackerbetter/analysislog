package com.zhengshuli.analysislog.feature;

import java.util.Iterator;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;
import com.zhengshuli.analysislog.domain.LogLine;
import com.zhengshuli.analysislog.feature.view.Top10UniqueVisitorsView;
import com.zhengshuli.analysislog.parser.ILogLineAnalyzer;

public class Top10UniqueVisitorsAnalyzer implements ILogLineAnalyzer<Top10UniqueVisitorsView>{
    private Multiset<String> uniqueVistors = TreeMultiset.create();
    
    @Override
    public void analyze(LogLine logLine) {
        if(logLine == null) {
            return;
        }
        uniqueVistors.add(logLine.getRemoteAddr());
    }
    
    @Override
    public Top10UniqueVisitorsView toView() {
        uniqueVistors = Multisets.copyHighestCountFirst(uniqueVistors);
        Iterator<String> visitors = uniqueVistors.elementSet().iterator();
        int i = 0;
        Top10UniqueVisitorsView view = new Top10UniqueVisitorsView();
        while(visitors.hasNext() && i < 10){
            String ip = visitors.next();
            int visitCount = uniqueVistors.count(ip);
            view.addVisitor(ip, visitCount);
            i++;
        }
        return view;
    }
}
