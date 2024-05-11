package com.paf.paperrex.TT.Service;

import com.paf.paperrex.TT.Entity.Exercise;
import com.paf.paperrex.TT.Entity.WorkoutPlan;
import com.paf.paperrex.TT.Repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanService  {

    private final WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
    for (Exercise exercise : workoutPlan.getExercises()) {
        exercise.setWorkoutPlan(workoutPlan);
    }
    return workoutPlanRepository.save(workoutPlan);
    }

    public WorkoutPlan getWorkoutPlanById(Long id) {
        Optional<WorkoutPlan> optionalWorkoutPlan = workoutPlanRepository.findById(id);
        return optionalWorkoutPlan.orElse(null);
    }

    public List<WorkoutPlan> getWorkoutPlansByUserId(Long userId) {
        return workoutPlanRepository.findByUserId(userId);
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return workoutPlanRepository.findAll();
    }

    public WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan workoutPlan) {
        Optional<WorkoutPlan> optionalWorkoutPlan = workoutPlanRepository.findById(id);
        // System.out.println("optionalWorkoutPlan: " + optionalWorkoutPlan);
        // System.out.println("workoutPlan: " + workoutPlan);
        if (optionalWorkoutPlan.isPresent()) {
            WorkoutPlan existingWorkoutPlan = optionalWorkoutPlan.get();
            existingWorkoutPlan.setPlanName(workoutPlan.getPlanName());
            existingWorkoutPlan.setDescription(workoutPlan.getDescription());
            for (Exercise exercise : workoutPlan.getExercises()) {
                exercise.setWorkoutPlan(existingWorkoutPlan);
            }
            existingWorkoutPlan.setExercises(workoutPlan.getExercises());
            return workoutPlanRepository.save(existingWorkoutPlan);
        }
        return null;
    }

    public void deleteWorkoutPlan(Long id) {
        workoutPlanRepository.deleteById(id);
    }

}
