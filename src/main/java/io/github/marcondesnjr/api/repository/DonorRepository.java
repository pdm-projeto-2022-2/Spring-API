package io.github.marcondesnjr.api.repository;

import io.github.marcondesnjr.api.model.BloodType;
import io.github.marcondesnjr.api.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findAllByBloodType(String bloodType);
    Optional<Donor> findByEmail(String email);
}