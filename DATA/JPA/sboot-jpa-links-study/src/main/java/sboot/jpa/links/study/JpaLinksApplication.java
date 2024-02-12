package sboot.jpa.links.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sboot.jpa.links.study.entity.Employee;
import sboot.jpa.links.study.repository.EmployeeRepository;

import java.util.List;

@Slf4j
@SpringBootApplication
public class JpaLinksApplication implements CommandLineRunner {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		Employee johny = employeeRepository.save(new Employee("Johny"));
		List<Employee> all = employeeRepository.findAll();
		all.forEach(emp -> System.out.println("Employee id: " + emp.getId() + ", name: " + emp.getName()));
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaLinksApplication.class, args);
	}
}
