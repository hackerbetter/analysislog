package com.zhengshuli.analysislog.reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

public class CSVReporter {
    public static void report(ICSVView csvView){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csvView.csvName()), ',');
            writer.writeNext(csvView.header());
            List result = csvView.result();
            for(Object analysisItem : result){
                String[] line = csvView.toLine(analysisItem);
                writer.writeNext(line);
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
