package com.zhengshuli.analysislog;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import com.google.common.io.Files;

public class FileFixture {
	public static String loadFirstLine(String fileName){
		String oneLine = null;
		try {
			oneLine = Files.readFirstLine(new File(FileFixture.class.getResource(fileName).toURI()), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oneLine;
	}
}
