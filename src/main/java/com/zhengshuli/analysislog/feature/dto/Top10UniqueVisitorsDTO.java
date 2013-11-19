package com.zhengshuli.analysislog.feature.dto;

import java.util.List;

public class Top10UniqueVisitorsDTO {
    private List<Top10UniqueVisitor> top10Visitors;
    public void add(String ip, Integer visitCount){
        Top10UniqueVisitor visitor = new Top10UniqueVisitor(ip, visitCount);
        top10Visitors.add(visitor);
    }
    
    class Top10UniqueVisitor{
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
