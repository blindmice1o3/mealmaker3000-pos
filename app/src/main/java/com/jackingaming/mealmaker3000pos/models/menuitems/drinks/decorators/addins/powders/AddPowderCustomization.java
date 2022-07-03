package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.powders;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class AddPowderCustomization extends CustomizationDecorator {
    public AddPowderCustomization(Drink drink, String name, String description, double price) {
        super(drink, name, description, price);
    }

    public AddPowderCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }
}