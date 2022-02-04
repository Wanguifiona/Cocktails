package com.moringaschool.cocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailsActivity extends AppCompatActivity {
    @BindView(R.id.cocktailsTextView) TextView mCocktailsTextView;
    @BindView(R.id.listView) ListView mListView;
    private String[] cocktails = new String[] {"Old Fashion", "Negroni",
            "Daguiri", "Martini", "Margarita", "Manhattan",
            "Mojito", "Bloody Mary", "Moscow Mule", "Mai Tai",
            "Americano", "Piña Colada", "Irish Coffee",
            "Cosmopolitan", "Long Island Iced Tea"};
    private String[] ingredients = new String[] {"Lemon juice", "Egg White", "Vodka", "Brandy", "Gin", "Whisky", "Pineapple juice", "Mint", "Sugar", "Orange", "StrawBerry", "Coconut Cream", "Syrup", "Soda", "Water", "Ice" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);
        ButterKnife.bind(this);

        MyCocktailsArrayAdapter adapter = new MyCocktailsArrayAdapter(this, android.R.layout.simple_list_item_1, cocktails, ingredients);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Log.v("CocktailsActivity", "In the onItemClickListener!");
                Toast.makeText(CocktailsActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String cocktails = intent.getStringExtra("cocktails");
        mCocktailsTextView.setText("Here are the cocktail recipes for: " + cocktails);
        Log.d("CocktailsActivity", "In the onCreate method!");
    }

}