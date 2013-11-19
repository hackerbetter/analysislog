package com.zhengshuli.analysislog.domain;

import java.io.File;

import com.zhengshuli.analysislog.parser.LogFormat;

public class LogFile {
    private LogFormat logFormat;
    private File file;
    
    public LogFile(LogFormat logFormat, File file) {
        this.logFormat = logFormat;
        this.file = file;
    }
    
    public LogFormat getLogFormat() {
        return logFormat;
    }
    public File getFile() {
        return file;
    }
}
