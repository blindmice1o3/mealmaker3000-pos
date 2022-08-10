package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Customization {
    public static final String JSON_NAME = "name";

    protected String name;

    public Customization(String name) {
        this.name = name;
    }

    public Customization(JSONObject customizationAsJSON) throws JSONException {
        name = customizationAsJSON.getString(JSON_NAME);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject customizationAsJSON = new JSONObject();
        customizationAsJSON.put(JSON_NAME, name);
        return customizationAsJSON;
    }

    public abstract double getPrice();

    public String getName() {
        return name;
    }
}