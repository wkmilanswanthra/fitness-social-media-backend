package com.paf.paperrex.TT.Repository;

import com.paf.paperrex.TT.Entity.Notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
}
