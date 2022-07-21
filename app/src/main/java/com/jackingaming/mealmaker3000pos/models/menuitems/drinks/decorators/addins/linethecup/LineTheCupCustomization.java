package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class LineTheCupCustomization extends CustomizationDecorator {
    public LineTheCupCustomization(String name, double price) {
        super(name, price);
    }

    public LineTheCupCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}