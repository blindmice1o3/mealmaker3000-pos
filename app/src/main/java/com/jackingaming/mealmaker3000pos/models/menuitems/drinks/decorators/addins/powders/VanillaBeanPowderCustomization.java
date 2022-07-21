package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.powders;

import org.json.JSONException;
import org.json.JSONObject;

public class VanillaBeanPowderCustomization extends AddPowderCustomization {
    public static final String NAME = "1 scoop of vanilla bean powder";
    public static final double PRICE = 0.10;

    public VanillaBeanPowderCustomization() {
        super(NAME, PRICE);
    }

    public VanillaBeanPowderCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}