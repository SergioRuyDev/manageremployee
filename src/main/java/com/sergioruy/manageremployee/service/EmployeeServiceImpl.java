package com.sergioruy.manageremployee.service;

import com.sergioruy.manageremployee.dto.EmployeeDto;
import com.sergioruy.manageremployee.exception.EmailExistException;
import com.sergioruy.manageremployee.exception.UserNotFoundException;
import com.sergioruy.manageremployee.mapper.AutoEmployeeMapper;
import com.sergioruy.manageremployee.model.Employee;
import com.sergioruy.manageremployee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
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
    public EmployeeDto getEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new UserNotFoundException("Employee by id " + employeeId + " was not found"));
        return AutoEmployeeMapper.MAPPER.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (optionalEmployee.isPresent()){
            throw new EmailExistException("Employee e-mail " + employeeDto.getEmail() + "Already Exist.");
        }
        return null;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id) {
        try {
            employeeRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        }
    }





    @Override
    public EmployeeDto updateEmployee(EmployeeDto employee) {
        return null;
    }


}
