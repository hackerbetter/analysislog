package com.zhengshuli.analysislog;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.io.Files;

public class FileFixture {
	public static String loadFirstLine(String fileName){
		String oneLine = null;
		try {
			oneLine = Files.readFirstLine(loadFile(fileName), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return oneLine;
	}
	
	public static List<String> loadLines(String fileName){
        List<String> lines = null;
        try {
            lines = Files.readLines(loadFile(fileName), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static File loadFile(String fileName) {
        try {
            return new File(FileFixture.class.getResource(fileName).toURI());
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteFile(String fileName) {
        new File(fileName).delete();
    }
}
