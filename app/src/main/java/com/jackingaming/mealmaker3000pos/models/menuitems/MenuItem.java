package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class MenuItem {
    public static final String JSON_NAME = "name";
    public static final String JSON_PRICE = "price";

    protected String name;
    protected double price;

    public MenuItem() {
        name = "default-unknown-menu-item";
        price = -0.001;
    }

    public MenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        name = menuItemAsJSON.getString(JSON_NAME);
        price = menuItemAsJSON.getDouble(JSON_PRICE);
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_NAME, name);
        json.put(JSON_PRICE, price);
        return json;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
