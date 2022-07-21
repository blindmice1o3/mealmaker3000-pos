package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.AddInCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.CupOptionCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.EspressoShotCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.FlavorCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.MilkCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.SweetenerCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.TeaCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.ToppingCustomization;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class CustomizationDecorator {
    public static final String JSON_NAME = "name";
    public static final String JSON_PRICE = "price";

    protected String name;
    protected double price;

    private AddInCustomization addInCustomization;
    private CupOptionCustomization cupOptionCustomization;
    private EspressoShotCustomization espressoShotCustomization;
    private FlavorCustomization flavorCustomization;
    private MilkCustomization milkCustomization;
    private SweetenerCustomization sweetenerCustomization;
    private TeaCustomization teaCustomization;
    private ToppingCustomization toppingCustomization;

    public CustomizationDecorator(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public CustomizationDecorator(JSONObject customizationDecoratorAsJSON)
            throws JSONException {
        name = customizationDecoratorAsJSON.getString(JSON_NAME);
        price = customizationDecoratorAsJSON.getDouble(JSON_PRICE);
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject customizationDecoratorAsJSON = new JSONObject();
        customizationDecoratorAsJSON.put(JSON_NAME, name);
        customizationDecoratorAsJSON.put(JSON_PRICE, price);
        return customizationDecoratorAsJSON;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}