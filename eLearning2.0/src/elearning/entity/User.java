package elearning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class User {
        @Id
        @GeneratedValue(strategy=GenerationType.TABLE, generator="USER_TABLE_GEN")
        @TableGenerator(name="USER_TABLE_GEN", table="SEQUENCE_TABLE",
            pkColumnName="SEQ_NAME",
            valueColumnName="SEQ_COUNT", allocationSize=1, pkColumnValue="USER_SEQ")
	private long id;
	private String fname;
	private String lname;
	private String nickname;
	private String email;
	private String password;
	private int age;
	
	public User() {
	}

	public User(String fname, String lname, String nickname, String email,
			String password) {
		this.fname = fname;
		this.lname = lname;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}

	public User(String fname, String lname, String nickname, String email,
			String password, int age) {
		this.fname = fname;
		this.lname = lname;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", "
				+ (fname != null ? "fname=" + fname + ", " : "")
				+ (lname != null ? "lname=" + lname + ", " : "")
				+ (nickname != null ? "nickname=" + nickname + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (password != null ? "password=" + password + ", " : "")
				+ (age != 0 ? "age=" + age : "") + "]";
	}
	
}
