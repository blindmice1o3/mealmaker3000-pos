package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators;

import android.util.Log;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class CustomizationDecorator extends Drink {
    protected Drink drink;

    public CustomizationDecorator(Drink drink, String name, String description, double price) {
        super(name, description, price);
        this.drink = drink;
    }

    public CustomizationDecorator(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        // TODO: insert new member variables
    }

    public boolean isAlreadyWrapped(String targetName) {
        String[] names = getName().split(",");
        for (String name : names) {
            String trimmedName = name.trim();
            Log.i("CustomizationDecorator", "trimmedName: " + trimmedName + ", targetName: " + targetName);
            if (trimmedName.equals(targetName)) {
                Log.i("CustomizationDecorator", "TRUE trimmedName: " + trimmedName + ", targetName: " + targetName);
                return true;
            }
        }

        return false;
    }

    public Drink getDrink() {
        return drink;
    }

    public abstract String getName();

    public abstract double getPrice();
}