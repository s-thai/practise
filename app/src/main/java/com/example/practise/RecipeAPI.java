package com.example.practise;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeAPI {

    @GET("random.php")
    Call<MealsList> getRandomRecipe();

}
