package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;


public class ReadingJsonFile {

	public String readJSON(String filePath) 
	{
		String jsonBody = null;
		try {	
			String completeFilePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\TestData" + filePath +".json";
			FileInputStream fileInputStream = new FileInputStream(new File(completeFilePath));
			jsonBody = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8.name());
		}catch(IOException e) {
			e.getStackTrace();
		}
		return jsonBody;
	}
}
