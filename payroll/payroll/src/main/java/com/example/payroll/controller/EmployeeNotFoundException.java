package com.example.payroll.controller;

class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
//https://spring.io/guides/tutorials/rest