package com.example.payroll.controller;

import java.util.List;

import com.example.payroll.domain.Employee;
import com.example.payroll.repository.EmployeeRepo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeRepo repo;

    EmployeeController(EmployeeRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/employees")
    List<Employee> all() {
        return repo.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee employee) {
        return repo.save(employee);
    }

    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repo.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repo.save(employee);
                })
                .orElseGet(() -> repo.save(newEmployee));
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
