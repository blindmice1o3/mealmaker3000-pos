package com.jackingaming.mealmaker3000pos.models.menuitems.drinks;

import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Drink extends MenuItem {

    // size, hot/cold (hot/iced),

    public Drink(String name, String description, double price) {
        super(name, description, price);
    }

    public Drink(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
    }

    @Override
    public double getPrice() {
        double priceOfCustomizationDecorators = 0.0;
        for (CustomizationDecorator customizationDecorator : customizationDecorators) {
            priceOfCustomizationDecorators = priceOfCustomizationDecorators + customizationDecorator.getPrice();
        }
        return price + priceOfCustomizationDecorators;
    }
}