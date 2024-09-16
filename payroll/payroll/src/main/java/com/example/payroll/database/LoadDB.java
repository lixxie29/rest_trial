package com.example.payroll.database;

import com.example.payroll.domain.Employee;
import com.example.payroll.repository.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {

    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);
    //spring runs all command runner beans once app context is loaded, it requests a copy of the repo
    //it creates two entities and stores them

    @Bean
    CommandLineRunner initDB(EmployeeRepo repo){
        return args -> {
            log.info("preload" + repo.save(new Employee("John", "Doe", "proffesor")));
            log.info("preload" + repo.save(new Employee("Ale", "Xandra", "director")));
        };
    }
}
