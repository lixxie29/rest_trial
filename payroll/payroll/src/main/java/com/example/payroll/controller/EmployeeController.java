package com.example.payroll.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.payroll.domain.Employee;
import com.example.payroll.repository.EmployeeRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class EmployeeController {

    private final EmployeeRepo repo;

    EmployeeController(EmployeeRepo repo) {
        this.repo = repo;
    }

//    @GetMapping("/employees")             //non-rest version
//    List<Employee> all() {
//        return repo.findAll();
//    }

//  CollectionModel - spring hateoas container that encapsulates collections of employee resources

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all(){

        List<EntityModel<Employee>> employees = repo.findAll().stream().map(employee -> EntityModel.of(employee,

                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"))).collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }




    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repo.save(newEmployee);
    }

//    //single item         //non-rest version of the methods
//    @GetMapping("/employees/{id}")
//    Employee one(@PathVariable Long id){
//        return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
//    }

//    @GetMapping("/employees/{id}")
//    Employee getEmployee(@PathVariable Long id) {
//        return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
//    }

    //single item
    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id){
        Employee employee = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
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
