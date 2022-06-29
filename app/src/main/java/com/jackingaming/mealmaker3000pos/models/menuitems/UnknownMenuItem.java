package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public class UnknownMenuItem extends MenuItem {
    public static final String NAME = "unknown menu item";
    public static final String DESCRIPTION = "null subclass for MenuItem";

    public UnknownMenuItem() {
        super(NAME, DESCRIPTION, -0.002);
    }

    public UnknownMenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        // TODO: insert new member variables
    }
}