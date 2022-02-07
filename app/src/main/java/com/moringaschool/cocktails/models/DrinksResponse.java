
package com.moringaschool.cocktails.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DrinksResponse {

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks ;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DrinksResponse() {
    }

    /**
     * 
     * @param drinks
     */
    public DrinksResponse(List<Drink> drinks) {
        super();
        this.drinks = drinks;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

}
