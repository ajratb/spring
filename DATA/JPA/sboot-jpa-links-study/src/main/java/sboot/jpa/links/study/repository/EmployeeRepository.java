package sboot.jpa.links.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sboot.jpa.links.study.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
