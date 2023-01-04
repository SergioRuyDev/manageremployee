package com.sergioruy.manageremployee.service;

import com.sergioruy.manageremployee.dto.EmployeeDto;
import com.sergioruy.manageremployee.exception.EmailExistException;
import com.sergioruy.manageremployee.exception.ResourceNotFoundException;
import com.sergioruy.manageremployee.mapper.AutoEmployeeMapper;
import com.sergioruy.manageremployee.model.Employee;
import com.sergioruy.manageremployee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeDto> getAllEmployeesPaginated(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map((employee) -> AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee -> AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee)))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeByCode(String employeeCode) {
        Employee employee = findOrFail(employeeCode);
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (optionalEmployee.isPresent()){
            throw new EmailExistException("Employee e-mail " + employeeDto.getEmail() + " Already Exist.");
        }
        employeeDto.setEmployeeCode(UUID.randomUUID().toString());
        Employee employee = AutoEmployeeMapper.MAPPER.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(String employeeCode, EmployeeDto employee) {
        Employee existEmployee = findOrFail(employeeCode);

        BeanUtils.copyProperties(employee, existEmployee, "id");
        Employee updatedEmployee = employeeRepository.save(existEmployee);
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteByEmployeeCode(String employeeCode) {
        findOrFail(employeeCode);
        employeeRepository.deleteByEmployeeCode(employeeCode);
    }


    public Employee findOrFail(String employeeCode) {
        return employeeRepository.findByEmployeeCode(employeeCode).orElseThrow(
                () -> new ResourceNotFoundException("Employee by code " + employeeCode + " not found"));
    }
}
