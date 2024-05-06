package com.paf.paperrex.TT.Controller;

import com.paf.paperrex.TT.Entity.WorkoutMetric;
import com.paf.paperrex.TT.Service.WorkoutMetricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workoutMetrics")
public class WorkoutMetricController {

    private final WorkoutMetricService workoutMetricService;

    public WorkoutMetricController(WorkoutMetricService workoutMetricService) {
        this.workoutMetricService = workoutMetricService;
    }

    @GetMapping
    public ResponseEntity<List<WorkoutMetric>> getAllWorkoutMetrics() {
        List<WorkoutMetric> workoutMetrics = workoutMetricService.getAllWorkoutMetrics();
        return ResponseEntity.ok(workoutMetrics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutMetric> getWorkoutMetricById(@PathVariable("id") Long id) {
        WorkoutMetric workoutMetric = workoutMetricService.getWorkoutMetricById(id);
        if (workoutMetric == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(workoutMetric);
    }

    @PostMapping
    public ResponseEntity<WorkoutMetric> createWorkoutMetric(@RequestBody WorkoutMetric workoutMetric) {
        WorkoutMetric createdWorkoutMetric = workoutMetricService.createWorkoutMetric(workoutMetric);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkoutMetric);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutMetric> updateWorkoutMetric(@PathVariable("id") Long id, @RequestBody WorkoutMetric workoutMetric) {
        WorkoutMetric updatedWorkoutMetric = workoutMetricService.updateWorkoutMetric(id, workoutMetric);
        return ResponseEntity.ok(updatedWorkoutMetric);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutMetric(@PathVariable("id") Long id) {
        workoutMetricService.deleteWorkoutMetric(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutMetric>> getWorkoutMetricsByUserId(@PathVariable("userId") Long userId) {
        List<WorkoutMetric> workoutMetrics = workoutMetricService.getWorkoutMetricsByUserId(userId);
        return ResponseEntity.ok(workoutMetrics);
    }
}
