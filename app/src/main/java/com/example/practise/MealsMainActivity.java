package com.example.practise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsMainActivity extends AppCompatActivity {

    RecyclerView recipeRecyclerView;
    MealsAdapter mealsAdapter;
    String TAG = "MealsMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_main);

        recipeRecyclerView = findViewById(R.id.recipeRecyclerView);

        mealsAdapter = new MealsAdapter(new ArrayList<Meal>());
        recipeRecyclerView.setAdapter(mealsAdapter);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);
        Call<MealResponse> call = recipeAPI.getRecipes();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.d(TAG, "Success!");
                List<Meal> meals = response.body().getMeals();
                mealsAdapter.setMeals(meals);
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.d(TAG, "Failure");
            }
        });

    }
}
