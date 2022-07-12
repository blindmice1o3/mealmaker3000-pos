package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class CustomizationDecorator extends Drink {
    public CustomizationDecorator(String name, String description, double price) {
        super(name, description, price);
    }

    public CustomizationDecorator(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        // TODO: insert new member variables
    }
}