package com.jackingaming.mealmaker3000pos.models.menuitems.drinks;

import com.jackingaming.mealmaker3000pos.models.Menu;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.Customization;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Drink extends MenuItem {
    public static final String JSON_CUSTOMIZATIONS = "customizations";

    // size, hot/cold (hot/iced),
    protected List<Customization> customizations;

    public Drink(String name, String description, double price) {
        super(name, description, price);
        customizations = new ArrayList<Customization>();
    }

    public Drink(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        customizations = new ArrayList<Customization>();
        JSONArray customizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_CUSTOMIZATIONS);
        for (int i = 0; i < customizationsAsJSONArray.length(); i++) {
            JSONObject customizationAsJSON = (JSONObject) customizationsAsJSONArray.get(i);
            Customization customization = Menu.parseToCustomization(customizationAsJSON);
            customizations.add(customization);
        }
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject menuItemAsJSON = super.toJSON();

        JSONArray customizationsAsJSONArray = new JSONArray();
        for (Customization customization : customizations) {
            JSONObject customizationAsJSON = customization.toJSON();
            customizationsAsJSONArray.put(customizationAsJSON);
        }
        menuItemAsJSON.put(JSON_CUSTOMIZATIONS, customizationsAsJSONArray);

        return menuItemAsJSON;
    }

    public void addToCustomizations(Customization customization) {
        customizations.add(customization);
    }

    public List<Customization> getCustomizations() {
        return customizations;
    }

    // TODO: override getPrice() to include price of customizations.
}