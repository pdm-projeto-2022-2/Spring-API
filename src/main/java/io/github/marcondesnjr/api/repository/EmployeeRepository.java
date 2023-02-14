package io.github.marcondesnjr.api.repository;

import io.github.marcondesnjr.api.model.Donor;
import io.github.marcondesnjr.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

}