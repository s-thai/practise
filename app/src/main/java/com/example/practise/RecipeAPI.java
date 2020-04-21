package com.example.practise;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeAPI {

    //right now this
    @GET("search.php?f=b")
    Call<MealResponse> getRecipes();

}
