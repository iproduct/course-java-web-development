package jdbcintro.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
	private long id;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private String middleName;
	@NonNull
	private String jobTitle;
	@NonNull
	private long departmentId;
	@NonNull
	private long managerId;
	@NonNull
	private Date hireDate;
	@NonNull
	private double salary;
	@NonNull
	private long addressId;

}
