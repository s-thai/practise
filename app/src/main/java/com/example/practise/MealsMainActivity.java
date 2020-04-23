package com.example.practise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsMainActivity extends AppCompatActivity{
    private boolean twoPane;
    private String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "v", "w", "y"};
    private String letterSelected;
    private MealsAdapter mealsAdapter;
    private String TAG = "MealsMainActivity";
    private Spinner letterSpinner;
    private MealDatabase mealDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_main);

        if (findViewById(R.id.recipe_container) != null){
            twoPane = true;
        }

        RecyclerView recyclerView = findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mealsAdapter = new MealsAdapter(this, new ArrayList<Meal>(), twoPane);
        recyclerView.setAdapter(mealsAdapter);

        letterSpinner = findViewById(R.id.letterSpinner);
        ArrayAdapter letterSpinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, alphabet);
        letterSpinner.setAdapter(letterSpinnerAdapter);



        letterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                letterSelected = letterSpinner.getItemAtPosition(position).toString();
                Log.d(TAG, "letter selected is " + letterSelected);
                Bundle bundle = new Bundle();
                bundle.putString(RecipeFragment.ARG_ITEM_ID, letterSelected);
                RecipeFragment rf = new RecipeFragment();
                rf.setArguments(bundle);
                mealDatabase = Room.databaseBuilder(getApplicationContext(), MealDatabase.class, "meal-database").build();
                new GetMealDatabaseTask().execute();
                new GetMealTask().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private class GetMealTask extends AsyncTask<Void, Void, List<Meal>>{

        @Override
        protected List<Meal> doInBackground(Void... voids) {
            try{
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);
                Call<MealResponse> mealsCall = recipeAPI.getRecipesByFirstLetter(letterSelected);
                Response<MealResponse> mealsResponse = mealsCall.execute();
                List<Meal> meals = mealsResponse.body().getMeals();
                mealDatabase.mealDao().deleteAll(mealDatabase.mealDao().getMeals().toArray(new Meal[mealDatabase.mealDao().getMeals().size()]));
                mealDatabase.mealDao().insertAll(meals.toArray(new Meal[meals.size()]));
                return meals;
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Meal> meals){
            mealsAdapter.setMeals(meals);
        }
    }

    private class GetMealDatabaseTask extends AsyncTask<Void, Void, List<Meal>>{

        @Override
        protected List<Meal> doInBackground(Void... voids) {
            return mealDatabase.mealDao().getMeals();
        }

        @Override
        protected void onPostExecute(List<Meal> meals){
            mealsAdapter.setMeals(meals);
        }
    }

}
