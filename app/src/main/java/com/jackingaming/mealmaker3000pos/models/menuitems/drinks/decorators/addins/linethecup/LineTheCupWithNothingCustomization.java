package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithNothingCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with nothing";
    public static final String DESCRIPTION = "default subclass for LineTheCupCustomization";
    public static final double PRICE = 0.00;

    private Drink drink;

    public LineTheCupWithNothingCustomization(Drink drink) {
        super(NAME, DESCRIPTION, PRICE);
        this.drink = drink;
    }

    public LineTheCupWithNothingCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }

    @Override
    public String getName() {
        return drink.getName();
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + price;
    }
}