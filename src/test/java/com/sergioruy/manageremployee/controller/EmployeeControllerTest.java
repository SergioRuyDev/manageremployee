package com.sergioruy.manageremployee.controller;

import com.sergioruy.manageremployee.api.controller.EmployeeController;
import com.sergioruy.manageremployee.model.Employee;
import com.sergioruy.manageremployee.service.EmployeeService;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.employeeController);
    }

    @Test
    public void shouldReturnSuccess_WhenGetEmployeeList() {

        org.mockito.Mockito.when(this.employeeService.findEmployeeById(1L))
                        .thenReturn(new Employee(1L, "Sergio", "sergio@gmail.com", "Backend Dev", "9999999", "www.image", "111222333"));

        given()
                .accept(ContentType.JSON)

        .when()
                .get("/employees/all")

        .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
