package com.paf.paperrex.TT.Repository;

import com.paf.paperrex.TT.Entity.WorkoutMetric;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutMetricRepository extends JpaRepository<WorkoutMetric, Long> {

    List<WorkoutMetric> findByUserId(Long userId);
}
