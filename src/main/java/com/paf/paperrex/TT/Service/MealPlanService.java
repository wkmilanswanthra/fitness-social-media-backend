package com.paf.paperrex.TT.Service;

import com.paf.paperrex.TT.Entity.MealPlan;
import com.paf.paperrex.TT.Repository.MealPlanRepository;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;

    public MealPlanService(MealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    public MealPlan getMealPlanById(Long mealID) {
        System.out.println(":here");
        Optional<MealPlan> optionalMealPlan = mealPlanRepository.findById(mealID);
        System.out.println(":here 2");
        return optionalMealPlan.orElse(null);
    }

    public List<MealPlan> getMealPlansByUserId(Long userId) {
        return mealPlanRepository.findByUserId(userId);
    }

    public MealPlan createMealPlan(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    public MealPlan updateMealPlan(Long id, MealPlan updatedMealPlan) {
        Optional<MealPlan> optionalMealPlan = mealPlanRepository.findById(id);
        if (optionalMealPlan.isPresent()) {
            updatedMealPlan.setMealID(id);
            return mealPlanRepository.save(updatedMealPlan);
        } else {
            return null;
        }
    }

    public void deleteMealPlan(Long id) {
        mealPlanRepository.deleteById(id);
    }

    public List<MealPlan> getAllMealPlans() {
        List<MealPlan> mealPlans = mealPlanRepository.findAll();
        return mealPlans;
    }

}
