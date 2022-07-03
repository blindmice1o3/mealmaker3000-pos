package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators;

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

    public Drink getDrink() {
        return drink;
    }

    public abstract String getName();

    public abstract double getPrice();

    public abstract boolean isAlreadyWrapped();
}