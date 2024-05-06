package com.paf.paperrex.TT.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workout_metrics")
public class WorkoutMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_metric_seq")
    @SequenceGenerator(name = "workout_metric_seq", sequenceName = "workout_metric_seq", allocationSize = 1)
    private Long metricID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "metric_type")
    private String metricType;

    @Column(name = "metric_value")
    private String metricValue;

    @Column(name = "description")
    private String description;
}
