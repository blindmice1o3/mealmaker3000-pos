package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public class Water extends MenuItem {
    public static final String NAME = "water";

    public Water() {
        super();
        name = NAME;
        price = 0.05;
    }

    public Water(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        // TODO: insert new member variables
    }
}
