package com.zhengshuli.analysislog.parser;

import com.zhengshuli.analysislog.domain.LogLine;

public interface ILogLineAnalyzer {
    void analyze(LogLine logLine);
    Object analyzeResult();
}
