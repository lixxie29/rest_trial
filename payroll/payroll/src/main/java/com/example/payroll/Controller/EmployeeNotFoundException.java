package com.example.payroll.Controller;

public class EmployeeNotFoundException extends X {
    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
//https://spring.io/guides/tutorials/rest