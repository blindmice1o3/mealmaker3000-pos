package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public class Bread extends MenuItem {
    public static final String NAME = "bread";

    public Bread() {
        super();
        name = NAME;
        price = 0.25;
    }

    public Bread(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        // TODO: insert new member variables
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject json = super.toJSON();
        // TODO: insert new member variables.
        return json;
    }
}
