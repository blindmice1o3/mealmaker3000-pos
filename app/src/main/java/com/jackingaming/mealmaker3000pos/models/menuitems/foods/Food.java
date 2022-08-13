package com.jackingaming.mealmaker3000pos.models.menuitems.foods;

import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Food extends MenuItem {
    public Food(String name, String description, double price, boolean handedOff) {
        super(name, description, price, handedOff);
    }

    public Food(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
    }
}