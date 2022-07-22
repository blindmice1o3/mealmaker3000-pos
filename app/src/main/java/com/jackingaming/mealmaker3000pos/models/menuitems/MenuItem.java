package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class MenuItem {
    public static final String JSON_NAME = "name";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_PRICE = "price";

    protected String name;
    protected String description;
    protected double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public MenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        name = menuItemAsJSON.getString(JSON_NAME);
        description = menuItemAsJSON.getString(JSON_DESCRIPTION);
        price = menuItemAsJSON.getDouble(JSON_PRICE);
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject menuItemAsJSON = new JSONObject();
        menuItemAsJSON.put(JSON_NAME, name);
        menuItemAsJSON.put(JSON_DESCRIPTION, description);
        menuItemAsJSON.put(JSON_PRICE, price);
        return menuItemAsJSON;
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
}