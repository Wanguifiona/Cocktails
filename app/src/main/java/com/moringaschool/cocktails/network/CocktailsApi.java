package com.moringaschool.cocktails.network;

import com.moringaschool.cocktails.models.Drink;
import com.moringaschool.cocktails.models.DrinksResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailsApi {
    @GET("search.php?f=g")
    Call<DrinksResponse> getDrinks();
    @GET("search.php?")
    Call<DrinksResponse> getSearchDrinks(
            @Query("f") String character
    );


}
