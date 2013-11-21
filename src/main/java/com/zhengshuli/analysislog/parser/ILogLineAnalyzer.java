package com.zhengshuli.analysislog.parser;

import com.zhengshuli.analysislog.domain.LogLine;
import com.zhengshuli.analysislog.reporter.IView;

public interface ILogLineAnalyzer<T extends IView> {
    void analyze(LogLine logLine);
    T toView();
}
