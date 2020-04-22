package com.example.practise;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeAPI {

    //right now this just returns a list of recipes beginning with b
    @GET("search.php?f=b")
    Call<MealResponse> getRecipes();

    @GET("search.php")
    Call<MealResponse> getRecipesByFirstLetter(@Query(value = "f", encoded = true) String f);
}
