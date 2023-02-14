package io.github.marcondesnjr.api.service.dto;

import io.github.marcondesnjr.api.model.BloodType;
import lombok.Data;

import java.time.Instant;

@Data
public class NotificationDTO {

    private Long id;
    private BloodType bloodType;
    private String title;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

}
