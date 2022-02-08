package com.moringaschool.cocktails.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.cocktails.CocktailDetailFragment;
import com.moringaschool.cocktails.models.Drink;

import java.util.List;

public class CocktailPagerAdapter extends FragmentPagerAdapter {
    private List<Drink> mCocktails;

    public CocktailPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Drink> cocktails){
        super (fm, behavior);
        mCocktails = cocktails;
    }
    @Override
    public Fragment getItem(int position) {

        return CocktailDetailFragment.newInstance(mCocktails.get(position));

    }

    @Override
    public int getCount() {
        return mCocktails.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCocktails.get(position).getStrDrink();
    }
}
