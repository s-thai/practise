package com.example.practise;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Meal.class}, version = 1)

public abstract class MealDatabase extends RoomDatabase {

    public abstract MealDao mealDao();

}
