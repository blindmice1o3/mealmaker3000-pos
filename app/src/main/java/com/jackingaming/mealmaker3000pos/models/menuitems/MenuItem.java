package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class MenuItem {
    public static final String JSON_NAME = "name";
    public static final String JSON_PRICE = "price";

    protected String name;
    protected double price;

    public MenuItem() {
    }

    public MenuItem(JSONObject json) {
        try {
            name = json.getString(JSON_NAME);
            price = json.getDouble(JSON_PRICE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put(JSON_NAME, name);
            json.put(JSON_PRICE, price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
