package com.worknext.africa.nija.worknext.data.repository;
import com.worknext.africa.nija.worknext.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
