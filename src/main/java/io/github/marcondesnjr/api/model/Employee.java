package io.github.marcondesnjr.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee extends User implements Serializable {

    private String name;
    private String registration;
    private String image;

}
