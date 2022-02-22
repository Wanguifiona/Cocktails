package com.moringaschool.cocktails.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.moringaschool.cocktails.Constants;
import com.moringaschool.cocktails.R;
import com.moringaschool.cocktails.adapters.CocktailListAdapter;
import com.moringaschool.cocktails.models.Drink;
import com.moringaschool.cocktails.models.DrinksResponse;
import com.moringaschool.cocktails.network.CocktailsApi;
import com.moringaschool.cocktails.network.CocktailsClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CocktailListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentCocktails;

    private static final String TAG = CocktailListActivity.class.getSimpleName();
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private CocktailListAdapter mAdapter;
    public List<Drink> cocktails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);
        ButterKnife.bind(this);

        cocktails = new ArrayList<>();


        Intent intent = getIntent();
        String cocktail = intent.getStringExtra("cocktails");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentCocktails = mSharedPreferences.getString(Constants.PREFERENCES_COCKTAIL_KEY, null);

        if(mRecentCocktails != null) {
            fetchCocktails(mRecentCocktails);
        }
    }
//    Animation animation = AnimationUtils.loadAnimation(this,R.anim.layout_animation);
//        controller.start();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String cocktail) {
                addToSharedPreferences(cocktail);
                search(Character.toString(cocktail.charAt(0)));
                return true;
            }
            @Override
            public boolean onQueryTextChange(String cocktail) {
                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void startRecyclerView(List<Drink> cocktails) {
            CocktailListAdapter cocktailListAdapter = new CocktailListAdapter(CocktailListActivity.this, cocktails);
            mRecyclerView.setAdapter(cocktailListAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showCocktails() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_COCKTAIL_KEY, location).apply();
    }
    private void fetchCocktails(String Cocktail){
        CocktailsApi client = CocktailsClient.getClient();
        Call<DrinksResponse> call = client.getDrinks();

        call.enqueue(new Callback<DrinksResponse>() {
            @Override
            public void onResponse(Call<DrinksResponse> call, Response<DrinksResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    List<Drink> allDrinks = response.body().getDrinks();
                    for (Drink drink : allDrinks) {
                        Log.d(TAG, "onResponse: " + drink.getStrDrink());
                    }
                    cocktails.addAll(allDrinks);

                    startRecyclerView(cocktails);
                    showCocktails();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<DrinksResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void search(String character){
        CocktailsApi client = CocktailsClient.getClient();
        Call<DrinksResponse> call = client.getSearchDrinks(character);

        call.enqueue(new Callback<DrinksResponse>() {
            @Override
            public void onResponse(Call<DrinksResponse> call, Response<DrinksResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    List<Drink> allDrinks = response.body().getDrinks();
                    for (Drink drink : allDrinks) {
                        Log.d(TAG, "onResponse: " + drink.getStrDrink());
                    }
                    cocktails.clear();
                    cocktails.addAll(allDrinks);

                    startRecyclerView(cocktails);
                    showCocktails();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<DrinksResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                hideProgressBar();
                showFailureMessage();
            }

        });
    }
}