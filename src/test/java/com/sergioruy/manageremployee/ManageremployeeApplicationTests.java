package com.sergioruy.manageremployee;

import com.sergioruy.manageremployee.exception.UserNotFoundException;
import com.sergioruy.manageremployee.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ManageremployeeApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void when_Employee_NotExist() {

        UserNotFoundException errorExpected =
            Assertions.assertThrows(UserNotFoundException.class, () -> {
                employeeService.deleteEmployee(100L);
            });
    }
}
