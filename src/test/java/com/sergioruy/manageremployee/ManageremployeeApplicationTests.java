package com.sergioruy.manageremployee;

import com.sergioruy.manageremployee.model.Employee;
import com.sergioruy.manageremployee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AllArgsConstructor
class ManageremployeeApplicationTests {

    private final EmployeeService employeeService;

//    @Test
//    public void testRegisterEmployeeName() {
//        Employee newEmployee = new Employee();
//        newEmployee.setName("Sergio");
//
//        newEmployee = employeeService.addEmployee(newEmployee);
//
//        assertThat(newEmployee).isNotNull();
//        assertThat(newEmployee.getId()).isNotNull();
//    }

    @Test
    public void testEmployeeNoName() {
        Employee newEmployee = new Employee();
        newEmployee.setName(null);

        ConstraintViolationException errorExpected =
                Assertions.assertThrows(ConstraintViolationException.class, () -> {
                    employeeService.addEmployee(newEmployee);
                });

        assertThat(errorExpected).isNotNull();
    }
}
