package jvm;

public class ShowJvmProperties {

	public static void main(String[] args) {
		System.getProperties().list(System.out);
		System.out.println(System.getProperty("user.name"));
	}
	
}
