package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {

	public static String getValueForKey(String key) throws IOException
	{
		FileInputStream fis= new FileInputStream("./PropertyFile/EnvironmentVaraibles.properties");
		Properties property = new Properties();
		property.load(fis);
		return property.getProperty(key);
	}
}
