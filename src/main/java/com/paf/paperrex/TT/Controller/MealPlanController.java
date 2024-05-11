package com.paf.paperrex.TT.Controller;

import com.paf.paperrex.TT.Entity.MealPlan;
import com.paf.paperrex.TT.Service.MealPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mealPlans")
public class MealPlanController {

    private final MealPlanService mealPlanService;

    public MealPlanController(MealPlanService mealPlanService) {
        this.mealPlanService = mealPlanService;
    }

    @GetMapping
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {
        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
        if (!mealPlans.isEmpty()) {
            return ResponseEntity.ok(mealPlans);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealPlan> getMealPlanById(@PathVariable("id") Long id) {
        System.out.println("id: " + id);
        MealPlan mealPlan = mealPlanService.getMealPlanById(id);
        System.out.println("mealPlan: " + mealPlan);
        if (mealPlan != null) {
            return ResponseEntity.ok(mealPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealPlan>> getMealPlansByUserId(@PathVariable Long userId) {
        List<MealPlan> mealPlans = mealPlanService.getMealPlansByUserId(userId);
        return ResponseEntity.ok(mealPlans);
    }

    @PostMapping
    public ResponseEntity<MealPlan> createMealPlan(@RequestBody MealPlan mealPlan) {
        MealPlan createdMealPlan = mealPlanService.createMealPlan(mealPlan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMealPlan);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MealPlan> updateMealPlan(@PathVariable Long id, @RequestBody MealPlan mealPlan) {
        MealPlan updatedMealPlan = mealPlanService.updateMealPlan(id, mealPlan);
        if (updatedMealPlan != null) {
            return ResponseEntity.ok(updatedMealPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealPlan(@PathVariable Long id) {
        mealPlanService.deleteMealPlan(id);
        return ResponseEntity.noContent().build();
    }
}
