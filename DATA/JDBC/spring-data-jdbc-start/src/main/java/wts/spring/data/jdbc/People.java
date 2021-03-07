package wts.spring.data.jdbc;

import org.springframework.data.annotation.Id;

public class People {

	@Id
	Long id;
//    LocalDate dob;
	Integer age;
	String firstName;
	String lastName;

	@Override
	public String toString() {
		return "Person{" + "id=" + id 
				+ ", age=" + age + ", firstName='" + firstName + '\'' 
				+ ", lastName='" + lastName + '\'' + '}';
	}
}
