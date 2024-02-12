package sboot.jpa.links.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sboot.jpa.links.study.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
