package com.paf.paperrex.TT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_seq")
    @SequenceGenerator(name = "exercise_seq", sequenceName = "exercise_seq", allocationSize = 1)
    @Column(name = "exerciseid")
    private Long exerciseID;

    @ManyToOne
    @JoinColumn(name = "workoutPlan", nullable = false)
    @JsonIgnore
    private WorkoutPlan workoutPlan;

    @Column(name = "exercise_name")
    private String exerciseName;

    @Column(name = "sets")
    private int sets;

    @Column(name = "repetitions")
    private int repetitions;
}
