package com.sergioruy.manageremployee.api.controller;

import com.sergioruy.manageremployee.dto.EmployeeDto;
import com.sergioruy.manageremployee.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getEmployeesPaginated(Pageable pageable) {
        Page<EmployeeDto> employeeDtos = employeeServiceImpl.getAllEmployeesPaginated(pageable);
        return ResponseEntity.ok(employeeDtos);
    }


    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employees = employeeServiceImpl.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/find/{code}")
    public ResponseEntity<EmployeeDto> getEmployeeUuId(@PathVariable("code") String code) {
        EmployeeDto employee = employeeServiceImpl.getEmployeeByCode(code);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody @Valid EmployeeDto employee) {
        EmployeeDto newEmployee = employeeServiceImpl.createEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{code}")
    public ResponseEntity<EmployeeDto> updateEmployeeByUuid(@PathVariable("code")String employeeCode,
                                                      @RequestBody @Valid EmployeeDto employee) {
        EmployeeDto updateEmployee = employeeServiceImpl.updateEmployee(employeeCode, employee);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("code")String employeeCode) {
        employeeServiceImpl.deleteByEmployeeCode(employeeCode);
        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }
}
