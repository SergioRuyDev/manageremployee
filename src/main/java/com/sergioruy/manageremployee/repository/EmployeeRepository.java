package com.sergioruy.manageremployee.repository;

import com.sergioruy.manageremployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeCode(String employeeCode);
    void deleteByEmployeeCode(String employeeCode);

}
