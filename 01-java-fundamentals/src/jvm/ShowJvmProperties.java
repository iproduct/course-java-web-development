package jvm;

public class ShowJvmProperties {

	public static void main(String[] args) {
		System.out.println("JVM properties:\n========================");
		System.getProperties().list(System.out);
		System.out.println(System.getProperty("user.name"));
		System.out.println("\nSystem variables:\n========================");
		System.getenv().forEach((k, v) -> {
			System.out.println(k + " --> " + v);
		});
	}
	
}
