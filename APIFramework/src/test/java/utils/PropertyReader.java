package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	public static String readKey(String key)  {
		Properties properties = new Properties();
		
		try {
			
			FileInputStream fis = new FileInputStream("src/test/java/resources/data.properties");
			properties.load(fis);
			
		}
		catch(Exception e ){
			System.out.println(e.getMessage());
			
		}
		
		return properties.getProperty(key);
	}
	
	
	

}
