package com.paf.paperrex.TT.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "followers", uniqueConstraints=
        @UniqueConstraint(columnNames={"user_id", "follower_user_id"}))
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follower_seq")
    @SequenceGenerator(name = "follower_seq", sequenceName = "follower_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "follower_user_id", nullable = false)
    private User follower;
}
