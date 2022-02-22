package com.moringaschool.cocktails.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.cocktails.Constants;
import com.moringaschool.cocktails.R;
import com.moringaschool.cocktails.models.Drink;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailDetailFragment extends Fragment implements View.OnClickListener {
    Drink mCocktails;
    @BindView(R.id.cocktailNameTextView)
    TextView mCocktailNameTextView;
    @BindView(R.id.cocktailImageView)
    ImageView mCocktailImageView;
    @BindView(R.id.categoryTextView)
    TextView mCategoryTextView;
    @BindView(R.id.ingredientsTextView)
    TextView mIngredientsTextView;
    @BindView(R.id.instructionsTextView)
    TextView mInstructionsTextView;
    @BindView(R.id.measurementsTextView)
    TextView mMeasurementsTextView;
    @BindView(R.id.glassTextView)
    TextView mGlassTextView;
    @BindView(R.id.saveCocktailButton)
    TextView mSaveCocktailButton;


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
        mMeasurementsTextView.setText(mCocktails.getStrMeasure1() + "\n" + mCocktails.getStrMeasure2());
        mGlassTextView.setText(mCocktails.getStrGlass());

        mSaveCocktailButton.setOnClickListener(this);

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mCocktails = (Drink) bundle.getSerializable("cocktail");

    }

    @Override
    public void onClick(View v) {
        if (v == mSaveCocktailButton) {
            DatabaseReference cocktailRef = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child(Constants.FIREBASE_CHILD_COCKTAILS);
            cocktailRef.push().setValue(mCocktails);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mSaveCocktailButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_COCKTAILS)
                    .child(uid);
            DatabaseReference pushRef = restaurantRef.push();
            String pushId = pushRef.getKey();
            mCocktails.setPushId(pushId);
            pushRef.setValue(mCocktails);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.mixed_anim);
        mSaveCocktailButton.startAnimation(animation);
    }
}
