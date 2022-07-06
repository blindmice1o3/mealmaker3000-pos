package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.powders;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

public class VanillaBeanPowderCustomization extends AddPowderCustomization {
    public static final String NAME = "1 scoop of vanilla bean powder";
    public static final String DESCRIPTION = "1 scoop of vanilla bean powder";
    public static final double PRICE = 0.10;

    public VanillaBeanPowderCustomization(Drink drink) {
        super(drink, NAME, DESCRIPTION, PRICE);
    }

    public VanillaBeanPowderCustomization(JSONObject menuItemAsJSON) throws JSONException {
        super(menuItemAsJSON);
    }

    @Override
    public String getName() {
        return drink.getName() + " , " + name;
    }

    @Override
    public double getPrice() {
        return drink.getPrice() + price;
    }
}