package com.moringaschool.cocktails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.moringaschool.cocktails.models.Drink;

import butterknife.ButterKnife;

public class CocktailDetailActivity extends AppCompatActivity {

    Drink mCocktails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_detail);
        ButterKnife.bind(this);

       Intent intent = getIntent();
        mCocktails =(Drink) intent.getSerializableExtra("drink");
        int startingPosition = getIntent().getIntExtra("position", 0);

        Bundle bundle = new Bundle();
        bundle.putSerializable("cocktail", mCocktails);
        Fragment fragment = new CocktailDetailFragment();
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
          .replace(R.id.pagerHeader, fragment, "my fragment")
          .commit();


    }
}