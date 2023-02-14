package io.github.marcondesnjr.api.service.dto;

import io.github.marcondesnjr.api.model.Donation;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DonorDTO extends UserDTO{
    private String name;
    private String bloodType;
    private String phone;
    private String sex;
    private Date birthDate;
    private String image;
    private List<DonationDTO> donations = new ArrayList<>();
    private String location;
    private Date createdAt; //FIXME
    private Date updatedAt;
}
