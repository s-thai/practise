package com.example.practise;

import java.io.Serializable;
import java.util.List;

public class MealResponse implements Serializable
{

    private List<Meal> meals = null;
    private final static long serialVersionUID = 7181824665889592060L;

    /**
     * No args constructor for use in serialization
     *
     */
    public MealResponse() {
    }

    /**
     *
     * @param meals
     */
    public MealResponse(List<Meal> meals) {
        super();
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}