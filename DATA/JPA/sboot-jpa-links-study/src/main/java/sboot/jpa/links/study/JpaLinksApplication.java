package sboot.jpa.links.study;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sboot.jpa.links.study.entity.Department;
import sboot.jpa.links.study.entity.Employee;
import sboot.jpa.links.study.repository.DepartmentRepository;
import sboot.jpa.links.study.repository.EmployeeRepository;

import java.util.List;

@Slf4j
@SpringBootApplication
public class JpaLinksApplication implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public void run(String... args) throws Exception {
        Employee johny = employeeRepository.save(new Employee("Johny"));
        List<Employee> allEmps = employeeRepository.findAll();
        allEmps.forEach(emp -> System.out.println("Employee id: " + emp.getId() + ", name: " + emp.getName()));
        Department mainDepartment = departmentRepository.save(new Department("Main"));
        Department secondaryDepartment = departmentRepository.save(new Department("Secondary"));
        List<Department> allDeps = departmentRepository.findAll();
        allDeps.forEach(dep -> System.out.println("Dep id is: " + dep.getId() + ", name is: " + dep.getName()));
        secondaryDepartment.setParentDepartment(mainDepartment);
        secondaryDepartment = departmentRepository.save(secondaryDepartment);
//        System.out.println("Sec dep has parent dep with name: " + secondaryDepartment.getParentDepartment().getName());
        Department found = departmentRepository.findById(secondaryDepartment.getId())
                .orElseThrow(EntityNotFoundException::new);
//        System.out.println("Found dep with name: " + found.getName());
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaLinksApplication.class, args);
    }
}
