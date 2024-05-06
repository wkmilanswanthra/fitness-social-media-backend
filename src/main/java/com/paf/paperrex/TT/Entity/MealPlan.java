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
@Table(name = "meal_plans")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_plan_seq")
    @SequenceGenerator(name = "meal_plan_seq", sequenceName = "meal_plan_seq", allocationSize = 1)
    private Long mealID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    @Column(name = "cooking_instructions")
    private String cookingInstructions;

    @ElementCollection
    @CollectionTable(name = "meal_photos", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "photo_url")
    private List<String> photos;

    @Column(name = "dietary_preferences")
    private List<String> dietaryPreferences;
}
