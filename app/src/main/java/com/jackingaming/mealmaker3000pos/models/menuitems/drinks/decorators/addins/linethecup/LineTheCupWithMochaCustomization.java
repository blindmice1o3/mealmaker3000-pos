package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONException;
import org.json.JSONObject;

public class LineTheCupWithMochaCustomization extends LineTheCupCustomization {
    public static final String NAME = "line the cup with mocha sauce";
    public static final String DESCRIPTION = "3 rotations of mocha sauce around the top of the cup.";
    public static final double PRICE = 0.30;

    public LineTheCupWithMochaCustomization(Drink drink) {
        super(drink, NAME, DESCRIPTION, PRICE);
    }

    public LineTheCupWithMochaCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }

    @Override
    public String getName() {
        return drink.getName() + " plus " + name;
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + price;
    }

    @Override
    public boolean isAlreadyWrapped() {
        if (this instanceof LineTheCupWithMochaCustomization) {
            return true;
        }

        if (drink instanceof CustomizationDecorator) {
            CustomizationDecorator customizationDecorator = (CustomizationDecorator) drink;
            return customizationDecorator.isAlreadyWrapped();
        }

        return false;
    }
}