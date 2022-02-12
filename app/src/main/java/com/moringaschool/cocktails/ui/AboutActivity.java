package com.moringaschool.cocktails.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.cocktails.Constants;
import com.moringaschool.cocktails.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @BindView(R.id.findCocktailsButton) Button mFindCocktailsButton;
    @BindView(R.id.cocktailsEditText) EditText mCocktailsEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mFindCocktailsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindCocktailsButton) {
            String cocktails = mCocktailsEditText.getText().toString();

            if(!(cocktails).equals("")) {
                addToSharedPreferences(cocktails);
            }

            Intent intent = new Intent(AboutActivity.this, CocktailListActivity.class);
//            intent.putExtra("cocktails", cocktails);
            startActivity(intent);
//                    Toast.makeText(AboutActivity.this, name, Toast.LENGTH_LONG).show();
        }
    }
    private void addToSharedPreferences(String strDrink) {
        mEditor.putString(Constants.PREFERENCES_COCKTAIL_KEY, strDrink).apply();
    }
}