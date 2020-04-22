package com.example.practise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    String TAG = "RecipeFragment";
    private String letter;
    private Meal mMeal;

    public RecipeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey((ARG_ITEM_ID))){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);

            letter = getArguments().getString(ARG_ITEM_ID);
            Log.d(TAG, "letter is " + letter);
            //need to somehow retreive what letter was cicked in mealsmainactivity and pass it to the following method
            Call<MealResponse> mealsCall = recipeAPI.getRecipeByID(letter);
            mealsCall.enqueue(new Callback<MealResponse>() {
                @Override
                public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                    List<Meal> meals = response.body().getMeals();
                    for (Meal meal : meals){
                        if(meal.getIdMeal().equals(getArguments().getString(ARG_ITEM_ID))){
                            mMeal = meal;
                            break;
                        }
                    }
                    updateData();
                    RecipeFragment.this.getActivity().setTitle(mMeal.getStrMeal());
                }

                @Override
                public void onFailure(Call<MealResponse> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_recipe_fragment, container, false);
        updateData();
        return rootView;
    }

    private void updateData(){
        View rootView = getView();
        if (mMeal != null){
            ((TextView) rootView.findViewById(R.id.recipeName)).setText(mMeal.getStrMeal());

        }
    }
}
