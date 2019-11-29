package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String appConfigPath = PropertiesDemo.class.getClassLoader().getResource("properties/app.properties").getPath();
		 
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		  
		String appVersion = appProps.getProperty("version");
		System.out.println("Version: "+ appVersion);
		String appName = appProps.getProperty("name");
		System.out.println("App name: "+ appName);
	}
	
}

