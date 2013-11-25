package com.zhengshuli.analysislog.feature;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

import org.joda.time.DateTime;

import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;
import com.zhengshuli.analysislog.domain.LogLine;
import com.zhengshuli.analysislog.feature.view.RequestTimeAvgView;
import com.zhengshuli.analysislog.parser.ILogLineAnalyzer;

public class RequestTimeAnalyzer implements ILogLineAnalyzer<RequestTimeAvgView> {
    private Multimap<String, Double> requestAvgTimes = TreeMultimap.create(Ordering.natural(), new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return -1;
        }
    });
    
    @Override
    public void analyze(LogLine logLine) {
        if(logLine == null) {
            return;
        }
        DateTime timeLocal = logLine.getTimeLocal();
        String timeRange = getTimeRange(timeLocal.getHourOfDay(), timeLocal.getMinuteOfHour());
        Double requestTime = Double.parseDouble(logLine.getRequestTime());
        requestAvgTimes.put(timeRange, requestTime);
    }
    
    public String getTimeRange(Integer hour, Integer min) {
        if(min <= 30){
            return hourFormat(hour) + ":00 - " + hourFormat(hour) + ":30";
        }
        return hourFormat(hour) + ":30 - " + hourFormat(hour + 1) + ":00";
    }
    
    public String hourFormat(Integer hour){
        return hour > 9 ? hour.toString() : "0" + hour.toString(); 
    }

    @Override
    public RequestTimeAvgView toView() {
        Set<String> timeRanges = requestAvgTimes.keySet();
        RequestTimeAvgView view = new RequestTimeAvgView();
        for(String timeRange : timeRanges) {
            Collection<Double> requestTimes = requestAvgTimes.get(timeRange);
            Double requestTimeAvg = getRequestTimeAvg(requestTimes);
            view.addRequestTimeAvg(timeRange, requestTimeAvg, Long.parseLong(requestTimes.size() + ""));
            
        }
        return view;
    }
    
    private Double getRequestTimeAvg(Collection<Double> requestTimes){
        Double total = 0.0;
        for(Double i : requestTimes){
            total += i;
        }
        return total / requestTimes.size();
    }
}
