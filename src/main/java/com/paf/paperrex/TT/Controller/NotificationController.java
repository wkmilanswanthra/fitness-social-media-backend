package com.paf.paperrex.TT.Controller;

import com.paf.paperrex.TT.Entity.Notification;
import com.paf.paperrex.TT.Service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }

    @PatchMapping("seen/{notificationId}")
    public ResponseEntity<Notification> markSeen(@PathVariable Long notificationId) {
        Notification updatedNotification = notificationService.markSeen(notificationId);
        return ResponseEntity.ok(updatedNotification);
    }
    

}
