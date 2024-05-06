package com.paf.paperrex.TT.Entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workout_plans")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "workout_plan_seq")
    @SequenceGenerator(name = "workout_plan_seq", sequenceName = "workout_plan_seq", allocationSize = 1)
    @Column(name = "planid")
    private Long planID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "workoutPlan", cascade = CascadeType.ALL)
    private List<Exercise> exercises;
}
