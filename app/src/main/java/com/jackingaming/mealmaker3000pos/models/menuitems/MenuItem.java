package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class MenuItem {
    public static final String JSON_NAME = "name";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_PRICE = "price";
    public static final String JSON_HANDED_OFF = "handed-off";

    protected String name;
    protected String description;
    protected double price;
    protected boolean handedOff;

    public MenuItem(String name, String description, double price, boolean handedOff) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.handedOff = handedOff;
    }

    public MenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        name = menuItemAsJSON.getString(JSON_NAME);
        description = menuItemAsJSON.getString(JSON_DESCRIPTION);
        price = menuItemAsJSON.getDouble(JSON_PRICE);
        handedOff = menuItemAsJSON.getBoolean(JSON_HANDED_OFF);
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject menuItemAsJSON = new JSONObject();
        menuItemAsJSON.put(JSON_NAME, name);
        menuItemAsJSON.put(JSON_DESCRIPTION, description);
        menuItemAsJSON.put(JSON_PRICE, price);
        menuItemAsJSON.put(JSON_HANDED_OFF, handedOff);
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

    public boolean isHandedOff() {
        return handedOff;
    }

    public void setHandedOff(boolean handedOff) {
        this.handedOff = handedOff;
    }
}