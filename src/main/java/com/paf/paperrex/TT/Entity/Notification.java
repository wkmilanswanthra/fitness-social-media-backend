package com.paf.paperrex.TT.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
    @SequenceGenerator(name = "notification_seq", sequenceName = "notification_seq", allocationSize = 1)
    private Long notificationID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "notification_type")
    private String notificationType;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "seen")
    private boolean seen;
}
