package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.cupoptions;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class CupSizeCustomization extends CustomizationDecorator {

    // TODO: Implement CupSizeCustomization.

    public CupSizeCustomization(String name, String description, double price) {
        super(name, description, price);
    }

    public CupSizeCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}