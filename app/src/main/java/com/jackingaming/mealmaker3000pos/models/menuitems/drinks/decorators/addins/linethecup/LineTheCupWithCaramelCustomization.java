package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithCaramelCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with caramel drizzle";
    public static final String DESCRIPTION = "3 rotations of caramel drizzle around the top of the cup.";
    public static final double PRICE = 0.60;

    public LineTheCupWithCaramelCustomization() {
        super(NAME, DESCRIPTION, PRICE);
    }

    public LineTheCupWithCaramelCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}