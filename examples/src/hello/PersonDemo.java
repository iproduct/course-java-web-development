package hello;

public class PersonDemo {

	public static void main(String[] args) {
		Person p1 = new Person("1234567890", "Georgi Petrov", 29, "Plovdiv");
		Person p2 = new Person("1234567890", "Georgi Petrov", 29, "Plovdiv");
		System.out.println(p1.equals(p2));
//		p1.setIdNumber("1234567890");
//		p1.setName("Georgi Petrov");
//		p1.setAge(29);
//		p1.setAddress("Sofia 1000");
//		System.out.println("IdNumber: " + p1.getIdNumber());
//		System.out.println("Name: " + p1.getName());
//		System.out.println("Age: " + p1.getAge());
//		System.out.println("Address: " + p1.getAddress());
		System.out.println(p1);

	}

}
