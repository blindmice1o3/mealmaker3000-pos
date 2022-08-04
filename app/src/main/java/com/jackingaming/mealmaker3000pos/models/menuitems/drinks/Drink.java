package com.jackingaming.mealmaker3000pos.models.menuitems.drinks;

import android.util.Log;

import com.jackingaming.mealmaker3000pos.models.Menu;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.AddInCustomization;
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
        String nameCustomizationToBeAdded = customization.getName();
        Log.d("Drink", nameCustomizationToBeAdded);

        // TODO: Check if already contains CARAMEL (also, it may
        //  already have MOCHA... DON'T OVERWRITE IT WITHOUT MOCHA)
        for (int i = 0; i < customizations.size(); i++) {
            Customization customizationInsideDrink = customizations.get(i);
            String nameCustomizationInsideDrink = customizationInsideDrink.getName();

            // is the customizationToBeAdded already inside inside Drink's customizations list?
            if (nameCustomizationInsideDrink.equals(nameCustomizationToBeAdded)) {
                Log.d("Drink", "addToCustomizations(Customization) nameCustomizationInsideDrink equals() nameCustomizationToBeAdded");
                if (customizationInsideDrink.isMergeable(customization)) {
                    Log.i("Drink", "addToCustomizations(Customization) customizationInsideDrink isMergeable() customization");
                    // TODO:


                } else {
                    Log.i("Drink", "addToCustomizations(Customization) customizationInsideDrink NOT isMergeable() customization");
                }
            } else {
                Log.i("Drink", "addToCustomizations(Customization) nameCustomizationInsideDrink NOT equals() nameCustomizationToBeAdded");
            }
        }

        customizations.add(customization);
    }

    public List<Customization> getCustomizations() {
        return customizations;
    }

    @Override
    public double getPrice() {
        double priceOfCustomizations = 0;
        for (Customization customization : customizations) {
            priceOfCustomizations += customization.getPrice();
        }
        return super.getPrice() + priceOfCustomizations;
    }
}