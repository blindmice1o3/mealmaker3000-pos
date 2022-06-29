package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithMochaCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with mocha sauce";
    public static final String DESCRIPTION = "3 rotations of mocha sauce around the top of the cup.";
    public static final double PRICE = 0.30;

    private Drink drink;

    public LineTheCupWithMochaCustomization(Drink drink) {
        super(NAME, DESCRIPTION, PRICE);
        this.drink = drink;
    }

    public LineTheCupWithMochaCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }

    @Override
    public String getName() {
        return drink.getName() + " plus " + name;
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + price;
    }
}