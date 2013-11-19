package com.zhengshuli.analysislog.parser;

import com.zhengshuli.analysislog.domain.LogLine;

public interface LogLineAnalyzer {
    void process(LogLine logLine);
    Object result();
}
