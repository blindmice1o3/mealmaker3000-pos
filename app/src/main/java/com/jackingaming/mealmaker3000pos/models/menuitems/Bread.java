package com.jackingaming.mealmaker3000pos.models.menuitems;

import org.json.JSONException;
import org.json.JSONObject;

public class Bread extends MenuItem {
    public Bread() {
        super();
        price = 0.25;
    }

    public Bread(JSONObject response) {
        super(response);
    }
}
