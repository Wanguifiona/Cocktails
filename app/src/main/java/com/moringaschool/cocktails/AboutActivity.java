package com.moringaschool.cocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.findCocktailsButton)
    Button mFindCocktailsButton;
    @BindView(R.id.cocktailsEditText)
    EditText mCocktailsEditText;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);
        mFindCocktailsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindCocktailsButton) {
            String cocktails = mCocktailsEditText.getText().toString();
            Intent intent = new Intent(AboutActivity.this, CocktailListActivity.class);
            intent.putExtra("cocktails", cocktails);
            startActivity(intent);
//                    Toast.makeText(AboutActivity.this, name, Toast.LENGTH_LONG).show();
        }
    }
}