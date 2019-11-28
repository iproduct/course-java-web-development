package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String appConfigPath = rootPath + "app.properties";
		 
		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));
		  
		String appVersion = appProps.getProperty("version");
		System.out.println("Version: "+ appVersion);
	}
	
}

