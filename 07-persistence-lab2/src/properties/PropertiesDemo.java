package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {

	public static void printProperties(Properties props) {
//		props.list(System.out);
		Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements()) {
			String k = (String) keys.nextElement();
			System.out.println(k + " = " + props.getProperty(k));
		}

		System.out.println();
		Set<Entry<Object, Object>> entries = props.entrySet();
		for (Entry<Object, Object> e : entries) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		URL appConfigPath = PropertiesDemo.class.getClassLoader()
				.getResource("properties/app.properties");

		System.out.println("Path: " + appConfigPath);

		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath.getPath()));

		printProperties(appProps);

		Path configPath;
		try {
			configPath = Paths.get(appConfigPath.toURI());
			Path xmlPath = configPath.resolve("../application.xml").normalize();
			System.out.println(xmlPath);

			appProps.storeToXML(new FileOutputStream(xmlPath.toString()), 
					"application configuration", 
					Charset.forName("utf-8").toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

//		String appVersion = appProps.getProperty("version");
//		System.out.println("Version: "+ appVersion);
//		String appName = appProps.getProperty("name");
//		System.out.println("App name: "+ appName);
//		String appDate = appProps.getProperty("date");
//		System.out.println("App date: "+ appDate);
	}

}
