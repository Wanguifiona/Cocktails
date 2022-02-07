package com.moringaschool.cocktails.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailsClient {

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/";
    public static CocktailsApi getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(CocktailsApi.class);
    }

}


