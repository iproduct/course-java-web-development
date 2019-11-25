package hello;

public class Person extends Object {
	private long eik;
	private String name;
	private int age;
	
	public Person(long eik, String name, int age) { //constructor
		this.eik = eik;
		this.name = name;
		this.age = age;
	}
	
	public String getName() { // accessor
		return name;
	}
	
	public void setName(String aName) { // mutator
		name = aName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public long getEik() {
		return eik;
	}

	public void setEik(long eik) {
		this.eik = eik;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eik ^ (eik >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Person))
			return false;
		Person other = (Person) obj;
		if (eik != other.eik)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EIK: " + eik + ", Name: " + getName() + ", Age: " + getAge();
	}

	public static void main(String[] args) {
		Person p1 = new Person(198009171234L, "Ivan Pertov", 39);
		Person p2 = new Person(198009171234L, "Ivan Hristov Pertov", 38);
		System.out.println(p1);
		p1.setAge(p1.getAge() + 1);
		System.out.println(p1);
		System.out.println("Same person: " + p1.equals(p2));
	}

}
