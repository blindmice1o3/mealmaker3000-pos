package com.jackingaming.mealmaker3000pos.models.menuitems.foods;

import org.json.JSONException;
import org.json.JSONObject;

public class Bread extends Food {
    public static final String NAME = "bread";
    public static final String DESCRIPTION = "Brought to you by multiplying yeast!";

    public Bread() {
        super(NAME, DESCRIPTION, 0.25);
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
