package hello;

public class Person {
	private String idNumber;
	private String name;
	private int age;
	private String address = "Sofia 1000";
	
	public Person(){}
	
	public Person(String idNumber, String name, int age, String address){
		this.idNumber = idNumber;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public String getIdNumber(){
		return idNumber;
	}

	public void setIdNumber(String newIdNumber){
		idNumber  = newIdNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idNumber == null) ? 0 : idNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdNumber: " + idNumber
				+ ", Name: " + name
				+ ", Age: " + age
				+ ", Address: " + address;
	}
	
	
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.idNumber = "1234567890";
		p1.name  = "Georgi Petrov";
		p1.age = 29;
		System.out.println("IdNumber: " + p1.idNumber);
		System.out.println("Name: " + p1.name);
		System.out.println("Age: " + p1.age);
		System.out.println("Address: " + p1.address);

	}

}
