package io.github.marcondesnjr.api.service.dto;

import io.github.marcondesnjr.api.model.Donation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DonationDTO {


    private Long id;
    private LocalDate date;
    private Donation.DonationStatus status;
    private DonorDTO donor;

}
