package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class MenuItem {
    public static final String JSON_PRICE = "price";

    protected double price;

    public MenuItem() {
    }

    public MenuItem(JSONObject json) {
        try {
            price = json.getDouble(JSON_PRICE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_PRICE, price);
        return json;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
