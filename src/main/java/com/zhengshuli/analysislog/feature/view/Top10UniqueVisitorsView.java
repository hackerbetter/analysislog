package com.zhengshuli.analysislog.feature.view;

import java.util.ArrayList;
import java.util.List;

import com.zhengshuli.analysislog.reporter.ICSVView;

public class Top10UniqueVisitorsView implements ICSVView<Top10UniqueVisitorsView.Top10UniqueVisitor> {
    private List<Top10UniqueVisitor> top10Visitors = new ArrayList<Top10UniqueVisitor>();
    
    public void addVisitor(String ip, Integer visitCount){
        Top10UniqueVisitor visitor = new Top10UniqueVisitor(ip, visitCount);
        top10Visitors.add(visitor);
    }
    
    @Override
    public List<Top10UniqueVisitor> result() {
        return top10Visitors;
    }

    @Override
    public String csvName() {
        return "top10_unique_visitors.csv";
    }

    @Override
    public String[] header() {
        return new String[]{"Visit IP", "Visit Count"};
    }

    @Override
    public String[] toLine(Top10UniqueVisitor visitor) {
        return new String[]{visitor.ip, visitor.visitCount.toString()};
    }
    
    public class Top10UniqueVisitor {
        private String ip;
        private Integer visitCount;
        
        public Top10UniqueVisitor(String ip, Integer visitCount) {
            this.ip = ip;
            this.visitCount = visitCount;
        }
        public String getIp() {
            return ip;
        }
        public Integer getVisitCount() {
            return visitCount;
        }
    }
}
