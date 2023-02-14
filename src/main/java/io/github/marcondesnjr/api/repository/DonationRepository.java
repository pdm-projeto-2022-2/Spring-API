package io.github.marcondesnjr.api.repository;

import io.github.marcondesnjr.api.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}