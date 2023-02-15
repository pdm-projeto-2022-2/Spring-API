package io.github.marcondesnjr.api;

import io.github.marcondesnjr.api.model.Employee;
import io.github.marcondesnjr.api.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class ApiApplication {

    private final EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @PostConstruct
    private void createEmployee(){
        var employee = new Employee();
        employee.setName("Admin");
        employee.setEmail("admin@admin.com");
        employee.setPassword("$2a$10$tG57e8dXVq2ZweYt50z.keKHLQ7mRN7cBwWdB.r0.s6e1PnGzTzle");
        employee.setImage("admin.png");
        try{
            employeeRepository.save(employee);
        }catch (Exception e){
            Logger.getLogger(ApiApplication.class.getName()).log(Level.INFO, e.getMessage(),e);
        }
    }

}
