package io.github.marcondesnjr.api.repository;

import io.github.marcondesnjr.api.model.BloodType;
import io.github.marcondesnjr.api.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {

    List<Notification> findAllByBloodType(BloodType bloodType);
}