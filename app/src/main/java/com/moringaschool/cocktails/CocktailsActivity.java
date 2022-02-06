package com.moringaschool.cocktails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.cocktails.models.Drink;
import com.moringaschool.cocktails.models.DrinksResponse;
import com.moringaschool.cocktails.network.CocktailsApi;
import com.moringaschool.cocktails.network.CocktailsClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CocktailsActivity extends AppCompatActivity {
    private static final String TAG = CocktailsActivity.class.getSimpleName();
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.cocktailsTextView) TextView mCocktailsTextView;
    @BindView(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails);
        ButterKnife.bind(this);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String cocktail = ((TextView)view).getText().toString();
                Toast.makeText(CocktailsActivity.this, cocktail, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String cocktail = intent.getStringExtra("cocktails");
        mCocktailsTextView.setText("Here are the cocktail recipes for: " + cocktail);



        CocktailsApi client = CocktailsClient.getClient();
        Call<DrinksResponse> call = client.getCocktails();

        call.enqueue(new Callback<DrinksResponse>() {
            @Override
            public void onResponse(Call<DrinksResponse> call, Response<DrinksResponse> response) {
                hideProgressBar();
                if(response.isSuccessful()){
                    List<Drink> cocktailsList= response.body().getDrinks();
                    String[] cocktails = new String[cocktailsList.size()];
                    String[] ingredients = new String[cocktailsList.size()];

                    for (int i = 0; i < cocktails.length; i++) {
                        cocktails[i] = cocktailsList.get(i).getStrDrink();
                    }

                    ArrayAdapter adapter = new MyCocktailsArrayAdapter(CocktailsActivity.this, android.R.layout.simple_list_item_1, cocktails, ingredients);
                    mListView.setAdapter(adapter);
                    showCoktails();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<DrinksResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }

        });
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showCoktails() {
        mListView.setVisibility(View.VISIBLE);
        mCocktailsTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}