package com.example.practise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {
//    TextView recipeName;
//    TextView ingredientsTitle;
//    TextView ingredients1;
//    TextView ingredients2;
//    TextView ingredients3;
//    TextView ingredients4;
//    TextView ingredients5;
//    TextView ingredients6;
//    TextView ingredients7;
//    TextView ingredients8;
//    TextView ingredients9;
//    TextView ingredients10;
//    TextView measure1;
//    TextView measure2;
//    TextView measure3;
//    TextView measure4;
//    TextView measure5;
//    TextView measure6;
//    TextView measure7;
//    TextView measure8;
//    TextView measure9;
//    TextView measure10;
//    TextView instructionsTitle;
//    TextView instructions;
//    List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        if (savedInstanceState == null){
            Bundle arguments = new Bundle();
            arguments.putString(RecipeFragment.ARG_ITEM_ID, getIntent().getStringExtra(RecipeFragment.ARG_ITEM_ID));
            RecipeFragment fragment = new RecipeFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_container, fragment)
                    .commit();
        }
//
//        recipeName = findViewById(R.id.recipeName);
//        ingredientsTitle = findViewById(R.id.ingredientsTitle);
//        ingredients1 = findViewById(R.id.ingredient1);
//        ingredients2 = findViewById(R.id.ingredient2);
//        ingredients3 = findViewById(R.id.ingredient3);
//        ingredients4 = findViewById(R.id.ingredient4);
//        ingredients5 = findViewById(R.id.ingredient5);
//        ingredients6 = findViewById(R.id.ingredient6);
//        ingredients7 = findViewById(R.id.ingredient7);
//        ingredients8 = findViewById(R.id.ingredient8);
//        ingredients9 = findViewById(R.id.ingredient9);
//        ingredients10 = findViewById(R.id.ingredient10);
//        measure1 = findViewById(R.id.measure1);
//        measure2 = findViewById(R.id.measure2);
//        measure3 = findViewById(R.id.measure3);
//        measure4 = findViewById(R.id.measure4);
//        measure5 = findViewById(R.id.measure5);
//        measure6 = findViewById(R.id.measure6);
//        measure7 = findViewById(R.id.measure7);
//        measure8 = findViewById(R.id.measure8);
//        measure9 = findViewById(R.id.measure9);
//        measure10 = findViewById(R.id.measure10);


    }
}
