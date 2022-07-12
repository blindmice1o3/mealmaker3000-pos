package com.jackingaming.mealmaker3000pos.models.menuitems;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuItem {
    public static final String JSON_NAME = "name";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_PRICE = "price";

    protected String name;
    protected String description;
    protected double price;
    protected List<CustomizationDecorator> customizationDecorators;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        customizationDecorators = new ArrayList<>();
    }

    public MenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        name = menuItemAsJSON.getString(JSON_NAME);
        description = menuItemAsJSON.getString(JSON_DESCRIPTION);
        price = menuItemAsJSON.getDouble(JSON_PRICE);
        customizationDecorators = new ArrayList<>();
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_NAME, name);
        json.put(JSON_DESCRIPTION, description);
        json.put(JSON_PRICE, price);
        return json;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<CustomizationDecorator> getCustomizationDecorators() {
        return customizationDecorators;
    }

    public boolean hasCustomizationDecorators() {
        return !customizationDecorators.isEmpty();
    }
}
