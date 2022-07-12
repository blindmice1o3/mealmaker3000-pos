package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.powders;

import org.json.JSONException;
import org.json.JSONObject;

public class ChocolateMaltPowderCustomization extends AddPowderCustomization {
    public static final String NAME = "1 scoop of chocolate malt powder";
    public static final String DESCRIPTION = "1 scoop of chocolate malt powder";
    public static final double PRICE = 0.15;

    public ChocolateMaltPowderCustomization() {
        super(NAME, DESCRIPTION, PRICE);
    }

    public ChocolateMaltPowderCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}