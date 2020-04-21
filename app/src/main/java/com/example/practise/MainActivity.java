package com.example.practise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button launchButton;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchButton = findViewById(R.id.launchButton);

        launchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMealsMainActivity();
            }
        });

//        recipeTitle = findViewById(R.id.recipeTitleTextView);
//        recipeButton = findViewById(R.id.recipeButton);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RecipeAPI api = retrofit.create(RecipeAPI.class);
//        Call<MealsList> call = api.getRandomRecipe();
//
//
//        recipeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                call.clone().enqueue(new Callback<MealsList>() {
//                    @Override
//                    public void onResponse(Call<MealsList> call, Response<MealsList> response) {
//                        Log.d(TAG, "onResponse: Success");
//                        MealsList mealsList = response.body();
//                        Log.d(TAG, "strMeal is " + mealsList.getMeals().get(0).getStrMeal());
//                        recipeTitle.setText(mealsList.getMeals().get(0).getStrMeal());
//                    }
//
//                    @Override
//                    public void onFailure(Call<MealsList> call, Throwable t) {
//                        Log.d(TAG, "onFailure: Failure");
//                    }
//                });
//            }
//        });

    }

    private void launchMealsMainActivity(){
        Intent intent = new Intent(this, MealsMainActivity.class);
        startActivity(intent);
    }
}
