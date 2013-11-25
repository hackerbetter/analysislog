package com.zhengshuli.analysislog.feature.view;

import java.util.ArrayList;
import java.util.List;

import com.zhengshuli.analysislog.reporter.ICSVView;

public class RequestTimeAvgView implements ICSVView<RequestTimeAvgView.RequestTimeAvg> {
    private List<RequestTimeAvg> avgs = new ArrayList<RequestTimeAvg>();
    @Override
    public List<RequestTimeAvg> result() {
        return avgs;
    }

    @Override
    public String csvName() {
        return "request_spend_time_avg.csv";
    }

    @Override
    public String[] header() {
        return new String[]{"time range", "request time avg", "request count"};
    }

    @Override
    public String[] toLine(RequestTimeAvg dto) {
        return new String[]{dto.timeRange, dto.requestTimeAvg.toString(), dto.getRequestCount().toString()};
    }
    
    public void addRequestTimeAvg(String timeRange, Double requestTimeAvg, Long requestCount) {
        RequestTimeAvg avg = new RequestTimeAvg(timeRange, requestTimeAvg, requestCount);
        avgs.add(avg);
    }

    public class RequestTimeAvg {
        private String timeRange;
        private Double requestTimeAvg;
        private Long requestCount;
        public Long getRequestCount() {
            return requestCount;
        }
        public void setRequestCount(Long requestCount) {
            this.requestCount = requestCount;
        }
        public String getTimeRange() {
            return timeRange;
        }
        public void setTimeRange(String timeRange) {
            this.timeRange = timeRange;
        }
        public Double getRequestTimeAvg() {
            return requestTimeAvg;
        }
        public void setRequestTimeAvg(Double requestTimeAvg) {
            this.requestTimeAvg = requestTimeAvg;
        }
        public RequestTimeAvg(String timeRange, Double requestTimeAvg,
                Long requestCount) {
            super();
            this.timeRange = timeRange;
            this.requestTimeAvg = requestTimeAvg;
            this.requestCount = requestCount;
        }
    }
}
