package io.github.marcondesnjr.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Donor extends User implements Serializable {

    private String name;
    @Column(name = "blood_type")
    private String bloodType;  //FIXME: change to enum
    private String phone;
    private String sex;
    @Column(name = "birth_date")
    private Date birthDate;
    private String location;
    private String image;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id")
    private List<Donation> donations = new ArrayList<>();


    public void addDonation(Donation donation){
        this.donations.add(donation);
    }

}
