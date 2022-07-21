package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithMochaCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with mocha sauce";
    public static final double PRICE = 0.30;

    public LineTheCupWithMochaCustomization() {
        super(NAME, PRICE);
    }

    public LineTheCupWithMochaCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}