package resources;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class Application {

	public static void printBundle(ResourceBundle bundle) {
		System.out.printf("\nResource bundle: %s, language: %s\n\n", 
				bundle.getBaseBundleName(), bundle.getLocale());
		Enumeration <String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = bundle.getString(key);
			System.out.println(key + ": " + value);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

		System.out.println("Current Locale: " + Locale.getDefault());
		ResourceBundle bundle = ResourceBundle.getBundle("resources.MyLabels");
		
		printBundle(bundle);

		System.out.println("Say how are you in US English: " + bundle.getString("how_are_you"));

//		Locale.setDefault(new Locale("ms", "MY"));
//      System.out.println("Current Locale: " + Locale.getDefault());
		
		ResourceBundle bundle2 = ResourceBundle.getBundle("resources.MyLabels", new Locale("ms", "MY"));
		printBundle(bundle2);
		System.out.println("Say how are you in Malaysian Malaya language: " + bundle2.getString("how_are_you"));
		
		Locale.setDefault(new Locale("bg", "BG"));
		ResourceBundle bundle3 = ResourceBundle.getBundle("resources.MyLabels");
		printBundle(bundle3);
		System.out.println("Say how are you in Bulgarian language: " + bundle3.getString("how_are_you"));

	}


}
