package com.sergioruy.manageremployee.controller;

import com.sergioruy.manageremployee.api.controller.EmployeeController;
import com.sergioruy.manageremployee.model.Employee;
import com.sergioruy.manageremployee.service.EmployeeServiceImpl;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;


@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    public EmployeeController employeeController;

    @MockBean
    public EmployeeServiceImpl employeeServiceImpl;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.employeeController);
    }

    @Test
    public void shouldReturnSuccess_WhenGetEmployeeById() {

        Mockito.when(this.employeeServiceImpl.findEmployeeById(1L))
                        .thenReturn(new Employee(1L, "Sergio", "sergio@gmail.com", "Backend Dev", "9999999", "www.image", "111222333"));

        given()
                .accept(ContentType.JSON)

        .when()
                .get("/employees/find/{id}", 1L)

        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldReturnFailed_WhenGetEmployeeById() {

        Mockito.when(this.employeeServiceImpl.findEmployeeById(null))
                .thenReturn(null);

        given()
                .accept(ContentType.JSON)
        .when()
                .get("/employees/find/{id}", (Object) null)
        .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }

//    @Test
//    public void shouldReturnSuccess_WhenCreateNewEmployee() {
//        Employee employee = new Employee(1L, "Sergio", "sergio@gmail.com", "Backend Dev", "9999999", "www.image", "111222333");
//
//        Mockito.when(this.employeeService.addEmployee(employee))
//                .thenReturn(new Employee(1L, "Sergio", "sergio@gmail.com", "Backend Dev", "9999999", "www.image", "111222333"));
//
//        given()
//                .accept(ContentType.JSON)
//        .when()
//                .post("/employees/add", employee)
//        .then()
//                .statusCode(HttpStatus.SC_CREATED);
//
//
//    }
}
