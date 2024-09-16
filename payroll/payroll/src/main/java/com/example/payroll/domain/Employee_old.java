package com.example.payroll.domain;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee_old {
    private @Id @GeneratedValue Long id;
    private String name;
    private String role;

    public Employee_old() {}

    public Employee_old(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Long getId() {return this.id;}
    public String getName() {return this.name;}
    public String getRole() {return this.role;}

    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setRole(String role) {this.role = role;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee_old employee)) return false;
        return Objects.equals(getId(), employee.getId()) && Objects.equals(getName(), employee.getName()) && Objects.equals(getRole(), employee.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRole());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
