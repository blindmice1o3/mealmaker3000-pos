package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithNothingCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with nothing";
    public static final String DESCRIPTION = "default subclass for LineTheCupCustomization";
    public static final double PRICE = 0.00;

    public LineTheCupWithNothingCustomization() {
        super(NAME, DESCRIPTION, PRICE);
    }

    public LineTheCupWithNothingCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}