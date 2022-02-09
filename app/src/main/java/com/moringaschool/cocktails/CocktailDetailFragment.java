package com.moringaschool.cocktails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.cocktails.models.Drink;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailDetailFragment extends Fragment {
    Drink mCocktails;
    @BindView(R.id.cocktailNameTextView) TextView mCocktailNameTextView;
    @BindView(R.id.cocktailImageView) ImageView mCocktailImageView;
    @BindView(R.id.categoryTextView) TextView mCategoryTextView;
    @BindView(R.id.ingredientsTextView) TextView mIngredientsTextView;
    @BindView(R.id.instructionsTextView) TextView mInstructionsTextView;
    @BindView(R.id.measurementsTextView) TextView mMeasurementsTextView;
    @BindView(R.id.glassTextView) TextView mGlassTextView;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_cocktail_detail, container, false);
        ButterKnife.bind(this, view);
        mCocktailNameTextView.setText(mCocktails.getStrDrink());
        Picasso.get().load(mCocktails.getStrDrinkThumb()).into(mCocktailImageView);
        mCategoryTextView.setText(mCocktails.getStrCategory());
        mIngredientsTextView.setText(mCocktails.getStrIngredient1() + "\n" + mCocktails.getStrIngredient2());
        mInstructionsTextView.setText(mCocktails.getStrInstructions());
        mMeasurementsTextView.setText(mCocktails.getStrMeasure1()+ "\n" + mCocktails.getStrMeasure2());
        mGlassTextView.setText(mCocktails.getStrGlass());
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCocktails = (Drink) bundle.getSerializable("cocktail");

    }
}
