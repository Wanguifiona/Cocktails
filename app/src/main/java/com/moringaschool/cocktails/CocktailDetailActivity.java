package com.moringaschool.cocktails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.moringaschool.cocktails.adapters.CocktailPagerAdapter;
import com.moringaschool.cocktails.models.Drink;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private CocktailPagerAdapter adapterViewPager;
    List<Drink> mCocktails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_detail);
        ButterKnife.bind(this);


        mCocktails = Parcels.unwrap(getIntent().getParcelableExtra("cocktails"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        adapterViewPager = new CocktailPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mCocktails);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}