package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithCaramelCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with caramel drizzle";
    public static final String DESCRIPTION = "3 rotations of caramel drizzle around the top of the cup.";
    public static final double PRICE = 0.60;

    public LineTheCupWithCaramelCustomization(Drink drink) {
        super(drink, NAME, DESCRIPTION, PRICE);

    }

    public LineTheCupWithCaramelCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }

    @Override
    public String getName() {
        return drink.getName() + " , " + name;
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + price;
    }
}