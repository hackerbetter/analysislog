package com.zhengshuli.analysislog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

public class AnalysisLog {

	private static Logger logger = Logger.getLogger(AnalysisLog.class);
	
	public static void main(String[] args) {
		AnalysisLog log = new AnalysisLog();
		if(args.length != 2){
			logger.error("please give two args, logDirPath and reportFilePath");
			return;
		}
		try {
			log.report(args[0],args[1]);
		}
		catch (FileNotFoundException e) {
			logger.error(e);
		}
	}

	private void analysisAvgMillsEachHour(File log,PrintStream printer){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(log.getAbsolutePath()));
			String line = "";
			int totalLineCount = 0;
			float allTotalResponseMills = 0;
			int lineCount = 0;
			float totalResponseMills = 0;
			String currentHour = "";
			while (!Strings.isNullOrEmpty(line = reader.readLine())){
				String[] items = line.split(" ");
				totalLineCount ++;
				try{
					String time = items[3];
					String hour = Lists.newArrayList(Splitter.on(":").split(time)).get(1);
					if(!hour.equals(currentHour) && !"".equals(currentHour)){
						printer.println("hour:"+currentHour+",avg mills:"+ totalResponseMills / lineCount + ",lineCount:" + lineCount );
						lineCount = 0;
						totalResponseMills = 0;
					}
					currentHour = hour;
					allTotalResponseMills += Float.valueOf(items[items.length - 2]);
					totalResponseMills += Float.valueOf(items[items.length - 2]);
					lineCount++;
				}catch(Exception e){
					totalLineCount--;
					lineCount--;
					logger.error("analysis log line error. line:"+line,e);
					continue;
				}
			}
			float avgMills = allTotalResponseMills / totalLineCount;
			printer.println(log.getName() + " report each [day] avg reponse miils");
			printer.println(avgMills);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void report(String logDirPath ,String reportFilePath) throws FileNotFoundException{
		File reportFile = new File(reportFilePath);
		PrintStream reportFilePrinter = new PrintStream(new FileOutputStream(reportFile,true));
		final String dir = logDirPath;
		String[] filePaths = new File(dir).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("lotserver.");
			}
		});
		Iterator<File> files = FluentIterable.from(Lists.newArrayList(filePaths)).transform(new Function<String,File>() {
			@Override
			public File apply(String filePath) {
				return new File(dir + filePath);
			}
		}).iterator();
		while(files.hasNext()){
			File file = files.next();
			reportFilePrinter.println(file.getName() + " report each [hour] avg reponse miils");
			analysisAvgMillsEachHour(file,reportFilePrinter);
			
		}
		reportFilePrinter.close();
	}
	
}
