package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithNothingCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with nothing";
    public static final String DESCRIPTION = "default subclass for LineTheCupCustomization";
    public static final double PRICE = 0.00;

    public LineTheCupWithNothingCustomization(Drink drink) {
        super(drink, NAME, DESCRIPTION, PRICE);
    }

    public LineTheCupWithNothingCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }

    @Override
    public String getName() {
        if (drink != null) {
            return drink.getName() + " , " + name;
        } else {
            return name;
        }
    }

    @Override
    public double getPrice() {
        if (drink != null) {
            return drink.getPrice() + price;
        } else {
            return price;
        }
    }
}