package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators;

import org.json.JSONException;
import org.json.JSONObject;

public class UnknownCustomizationDecorator extends CustomizationDecorator {
    public static final String NAME = "unknown customization decorator";
    public static final String DESCRIPTION = "null subclass for CustomizationDecorator";

    public UnknownCustomizationDecorator(String name, String description, double price) {
        super(NAME, DESCRIPTION, -0.003);
    }

    public UnknownCustomizationDecorator(JSONObject customizationDecoratorAsJSON)
            throws JSONException {
        super(customizationDecoratorAsJSON);
        // TODO: insert new member variables
    }
}