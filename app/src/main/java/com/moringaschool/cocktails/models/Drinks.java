
package com.moringaschool.cocktails.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Drinks {

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Drinks() {
    }

    /**
     * 
     * @param drinks
     */
    public Drinks(List<Drink> drinks) {
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
