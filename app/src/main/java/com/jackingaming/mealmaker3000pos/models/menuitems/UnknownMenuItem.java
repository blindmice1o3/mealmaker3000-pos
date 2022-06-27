package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public class UnknownMenuItem extends MenuItem {
    public static final String NAME = "unknown-menu-item";

    public UnknownMenuItem() {
        name = NAME;
        price = 0.42;
    }

    public UnknownMenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        fromJSON(menuItemAsJSON);
    }
}