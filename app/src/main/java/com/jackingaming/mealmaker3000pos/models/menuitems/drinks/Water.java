package com.jackingaming.mealmaker3000pos.models.menuitems.drinks;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup.LineTheCupWithCaramelCustomization;

import org.json.JSONException;
import org.json.JSONObject;

public class Water extends Drink {
    public static final String NAME = "water";
    public static final String DESCRIPTION = "At least 3 times more hydrating than bread!";


    public Water() {
        super(NAME, DESCRIPTION, 0.05);
        customizationDecorators.add(new LineTheCupWithCaramelCustomization());
        customizationDecorators.add(new LineTheCupWithCaramelCustomization());
    }

    public Water(JSONObject menuItemAsJSON)
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
