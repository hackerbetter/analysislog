package com.zhengshuli.analysislog.feature.dto;

import java.util.ArrayList;
import java.util.List;

public class Top10UniqueVisitorsDTO {
    private List<Top10UniqueVisitor> top10Visitors = new ArrayList<Top10UniqueVisitor>();
    public void add(String ip, Integer visitCount){
        Top10UniqueVisitor visitor = new Top10UniqueVisitor(ip, visitCount);
        top10Visitors.add(visitor);
    }
    
    public List<Top10UniqueVisitor> getTop10Visitors() {
        return top10Visitors;
    }
    public class Top10UniqueVisitor{
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
