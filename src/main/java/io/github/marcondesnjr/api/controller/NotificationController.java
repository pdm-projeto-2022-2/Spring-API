package io.github.marcondesnjr.api.controller;

import io.github.marcondesnjr.api.model.BloodType;
import io.github.marcondesnjr.api.service.NotificationService;
import io.github.marcondesnjr.api.service.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notifications")
    public ResponseEntity<?> createNotification(@RequestBody NotificationDTO notificationDTO){
        var resp = notificationService.create(notificationDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{id}").buildAndExpand(resp.getId()).toUri()).body(resp);
    }

    @GetMapping("/notifications")
    public ResponseEntity<?> listNotifications(@RequestParam BloodType bloodType){
        var list = notificationService.getAllByBloodType(bloodType);
        return ResponseEntity.ok(list);
    }

}
