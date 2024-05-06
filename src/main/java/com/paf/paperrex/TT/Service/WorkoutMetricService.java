package com.paf.paperrex.TT.Service;

import com.paf.paperrex.TT.Entity.WorkoutMetric;
import com.paf.paperrex.TT.Repository.WorkoutMetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutMetricService {

    private final WorkoutMetricRepository workoutMetricRepository;

    public WorkoutMetricService(WorkoutMetricRepository workoutMetricRepository) {
        this.workoutMetricRepository = workoutMetricRepository;
    }

    public List<WorkoutMetric> getAllWorkoutMetrics() {
        return workoutMetricRepository.findAll();
    }

    public WorkoutMetric getWorkoutMetricById(Long id) {
        Optional<WorkoutMetric> workoutMetricOptional = workoutMetricRepository.findById(id);
        return workoutMetricOptional.orElse(null);
    }

    public WorkoutMetric createWorkoutMetric(WorkoutMetric workoutMetric) {
        return workoutMetricRepository.save(workoutMetric);
    }

    public WorkoutMetric updateWorkoutMetric(Long id, WorkoutMetric newWorkoutMetric) {
        Optional<WorkoutMetric> existingWorkoutMetricOptional = workoutMetricRepository.findById(id);
        if (existingWorkoutMetricOptional.isPresent()) {
            WorkoutMetric existingWorkoutMetric = existingWorkoutMetricOptional.get();
            existingWorkoutMetric.setMetricType(newWorkoutMetric.getMetricType());
            existingWorkoutMetric.setMetricValue(newWorkoutMetric.getMetricValue());
            existingWorkoutMetric.setDescription(newWorkoutMetric.getDescription());
            return workoutMetricRepository.save(existingWorkoutMetric);
        }
        return null;
    }

    public void deleteWorkoutMetric(Long id) {
        workoutMetricRepository.deleteById(id);
    }

    public List<WorkoutMetric> getWorkoutMetricsByUserId(Long userId) {
        return workoutMetricRepository.findByUserId(userId);
    }
}
