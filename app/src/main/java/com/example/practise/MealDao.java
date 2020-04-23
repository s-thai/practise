package com.example.practise;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDao {

    @Query("SELECT * FROM meal")
        List<Meal> getMeals();

    @Query("SELECT * FROM meal WHERE idMeal == :idMeal")
    Meal getMeal(String idMeal);

    @Insert
    void insertAll(Meal...meals);

    @Delete
    void deleteAll(Meal...meals);

}
