package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public class UnknownMenuItem extends MenuItem {
    public static final String NAME = "class-unknown-menu-item";

    public UnknownMenuItem() {
        super();
        name = NAME;
        price = -0.002;
    }

    public UnknownMenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        // TODO: insert new member variables
    }
}