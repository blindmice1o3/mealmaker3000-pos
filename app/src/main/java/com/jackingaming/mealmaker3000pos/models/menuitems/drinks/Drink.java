package com.jackingaming.mealmaker3000pos.models.menuitems.drinks;

import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Drink extends MenuItem {

    // size, hot/cold (hot/iced),

    public Drink(String name, String description, double price) {
        super(name, description, price);
    }

    public Drink(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
    }
}