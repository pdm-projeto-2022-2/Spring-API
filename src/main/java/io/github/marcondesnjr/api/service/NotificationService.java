package io.github.marcondesnjr.api.service;

import io.github.marcondesnjr.api.model.BloodType;
import io.github.marcondesnjr.api.repository.NotificationRepository;
import io.github.marcondesnjr.api.service.dto.Mapper;
import io.github.marcondesnjr.api.service.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;


    @Transactional
    public NotificationDTO create(NotificationDTO notificationDTO){
        var notification = Mapper.toEntity(notificationDTO);
        notification = notificationRepository.save(notification);
        return Mapper.toDTO(notification);
    }

    public List<NotificationDTO> getAllByBloodType(BloodType bloodType){
        var list = notificationRepository.findAllByBloodType(bloodType);
        list.addAll(notificationRepository.findAllByBloodType(BloodType.ALL));
        return list.stream().map(Mapper::toDTO).toList();
    }
}
