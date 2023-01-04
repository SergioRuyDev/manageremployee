package com.sergioruy.manageremployee.service;

import com.sergioruy.manageremployee.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Page<EmployeeDto> getAllEmployeesPaginated(Pageable pageable);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeId(Long employeeId);
    EmployeeDto createEmployee(EmployeeDto employee);
    EmployeeDto updateEmployee(EmployeeDto employee);
    void deleteEmployee(Long employeeId);

}
