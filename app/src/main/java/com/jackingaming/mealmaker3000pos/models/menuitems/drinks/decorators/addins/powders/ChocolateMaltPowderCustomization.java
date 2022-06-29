package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.powders;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

public class ChocolateMaltPowderCustomization extends AddPowderCustomization {
    public static final String NAME = "1 scoop of chocolate malt powder";
    public static final String DESCRIPTION = "1 scoop of chocolate malt powder";
    public static final double PRICE = 0.15;

    private Drink drink;

    public ChocolateMaltPowderCustomization(Drink drink) {
        super(NAME, DESCRIPTION, PRICE);
        this.drink = drink;
    }

    public ChocolateMaltPowderCustomization(JSONObject menuItemAsJSON) throws JSONException {
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
}