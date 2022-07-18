package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class CustomizationDecorator {
    public static final String JSON_NAME = "name";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_PRICE = "price";

    protected String name;
    protected String description;
    protected double price;

    public CustomizationDecorator(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public CustomizationDecorator(JSONObject customizationDecoratorAsJSON)
            throws JSONException {
        name = customizationDecoratorAsJSON.getString(JSON_NAME);
        description = customizationDecoratorAsJSON.getString(JSON_DESCRIPTION);
        price = customizationDecoratorAsJSON.getDouble(JSON_PRICE);
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject customizationDecoratorAsJSON = new JSONObject();
        customizationDecoratorAsJSON.put(JSON_NAME, name);
        customizationDecoratorAsJSON.put(JSON_DESCRIPTION, description);
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