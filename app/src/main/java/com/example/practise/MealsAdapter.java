package com.example.practise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsViewHolder> {
    private MealsMainActivity parentActivity;
    private List<Meal> meals;
    private boolean twoPane;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Meal meal = (Meal)v.getTag();
            if (twoPane == true){
                Bundle arguments = new Bundle();
                arguments.putString(RecipeFragment.ARG_ITEM_ID, meal.getIdMeal());
                RecipeFragment fragment = new RecipeFragment();
                fragment.setArguments(arguments);
                parentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.recipe_container, fragment).commit();
            }else{
                Context context = v.getContext();
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra(RecipeFragment.ARG_ITEM_ID, meal.getIdMeal());
                context.startActivity(intent);
            }
        }
    };

    public MealsAdapter(MealsMainActivity parent, List<Meal> meals, boolean twoPane){
        parentActivity = parent;
        this.meals = meals;
        this.twoPane = twoPane;
    }

    @Override
    public MealsAdapter.MealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  = inflater.inflate(R.layout.meals_row, parent, false);
        return new MealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealsViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.recipeName.setText(meal.getStrMeal());
        holder.itemView.setTag(meal);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return this.meals.size();
    }

    public class MealsViewHolder extends RecyclerView.ViewHolder{
        TextView recipeName;

        public MealsViewHolder(View view){
            super(view);
            recipeName = view.findViewById(R.id.recipeName);
        }
    }


    public void setMeals(List<Meal> meals) {
        this.meals.clear();
        this.meals.addAll(meals);
        notifyDataSetChanged();
    }
}
