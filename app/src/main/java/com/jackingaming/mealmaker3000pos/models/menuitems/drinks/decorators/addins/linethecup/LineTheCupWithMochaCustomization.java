package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithMochaCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with mocha sauce";
    public static final String DESCRIPTION = "3 rotations of mocha sauce around the top of the cup.";
    public static final double PRICE = 0.30;

    public LineTheCupWithMochaCustomization() {
        super(NAME, DESCRIPTION, PRICE);
    }

    public LineTheCupWithMochaCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}