package com.moringaschool.cocktails;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyCocktailsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mCocktails;
    private String[] mIngredients;

    public MyCocktailsArrayAdapter(Context mContext, int resource, String[] mCocktails, String[] mIngredients) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mCocktails = mCocktails;
        this.mIngredients = mIngredients;
    }

    @Override
    public Object getItem(int position) {
        String cocktail = mCocktails[position];
        String ingredient = mIngredients[position];
        return String.format("%s \nMain ingredient: %s", cocktail, ingredient);
    }

    @Override
    public int getCount() {
        return mCocktails.length;
    }
}