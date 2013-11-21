package com.zhengshuli.analysislog.reporter;

public interface ICSVView<T> extends IView<T>{
    String csvName();
    String[] header();
    String[] toLine(T dto);
}
