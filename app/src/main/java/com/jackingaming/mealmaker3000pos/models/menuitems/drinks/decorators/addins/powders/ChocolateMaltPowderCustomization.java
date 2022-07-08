package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.powders;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

public class ChocolateMaltPowderCustomization extends AddPowderCustomization {
    public static final String NAME = "1 scoop of chocolate malt powder";
    public static final String DESCRIPTION = "1 scoop of chocolate malt powder";
    public static final double PRICE = 0.15;

    public ChocolateMaltPowderCustomization(Drink drink) {
        super(drink, NAME, DESCRIPTION, PRICE);
    }

    public ChocolateMaltPowderCustomization(JSONObject menuItemAsJSON) throws JSONException {
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