package com.jackingaming.mealmaker3000pos.models;

import android.util.Log;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.AddInCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.CupOptionCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.Customization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.EspressoShotCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.FlavorCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.MilkCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.SweetenerCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.TeaCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.ToppingCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.UnknownCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.foods.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.UnknownMenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Water;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final static String TAG = "Menu";

    private Menu() {
    }

    public static JSONArray convertToJSONArray(List<MenuItem> menuItems)
            throws JSONException {
        JSONArray menuItemsAsJSONArray = new JSONArray();
        for (MenuItem menuItem : menuItems) {
            JSONObject menuItemAsJSON = menuItem.toJSON();
            menuItemsAsJSONArray.put(menuItemAsJSON);
        }
        return menuItemsAsJSONArray;
    }

    public static List<MenuItem> convertToListOfMenuItem(JSONArray menuItemsAsJSONArray)
            throws JSONException {
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        for (int i = 0; i < menuItemsAsJSONArray.length(); i++) {
            JSONObject menuItemAsJSON = (JSONObject) menuItemsAsJSONArray.get(i);
            MenuItem menuItem = parseToMenuItem(menuItemAsJSON);
            menuItems.add(menuItem);
        }
        return menuItems;
    }

    private static MenuItem parseToMenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        String nameOfMenuItem = menuItemAsJSON.getString(MenuItem.JSON_NAME);

        if (Bread.NAME.equals(nameOfMenuItem)) {
            return new Bread(menuItemAsJSON);
        } else if (Water.NAME.equals(nameOfMenuItem)) {
            return new Water(menuItemAsJSON);
        }
        // TODO: insert new menu item.
        else {
            Log.d(TAG, "parseToMenuItem(JSONObject) else-clause for UnknownMenuItem.");
            return new UnknownMenuItem(menuItemAsJSON);
        }
    }

    public static Customization parseToCustomization(JSONObject customizationAsJSON)
            throws JSONException {
        String nameOfCustomization = customizationAsJSON.getString(Customization.JSON_NAME);

        if (AddInCustomization.NAME.equals(nameOfCustomization)) {
            return new AddInCustomization(customizationAsJSON);
        } else if (CupOptionCustomization.NAME.equals(nameOfCustomization)) {
            return new CupOptionCustomization(customizationAsJSON);
        } else if (EspressoShotCustomization.NAME.equals(nameOfCustomization)) {
            return new EspressoShotCustomization(customizationAsJSON);
        } else if (FlavorCustomization.NAME.equals(nameOfCustomization)) {
            return new FlavorCustomization(customizationAsJSON);
        } else if (MilkCustomization.NAME.equals(nameOfCustomization)) {
            return new MilkCustomization(customizationAsJSON);
        } else if (SweetenerCustomization.NAME.equals(nameOfCustomization)) {
            return new SweetenerCustomization(customizationAsJSON);
        } else if (TeaCustomization.NAME.equals(nameOfCustomization)) {
            return new TeaCustomization(customizationAsJSON);
        } else if (ToppingCustomization.NAME.equals(nameOfCustomization)) {
            return new ToppingCustomization(customizationAsJSON);
        }
        // TODO: insert new Customization subclasses.
        else {
            Log.d(TAG, "parseToCustomization(JSONObject) else-clause for UnknownCustomization.");
            return new UnknownCustomization(customizationAsJSON);
        }
    }
}