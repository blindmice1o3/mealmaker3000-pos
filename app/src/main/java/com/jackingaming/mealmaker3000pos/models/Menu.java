package com.jackingaming.mealmaker3000pos.models;

import android.util.Log;

import com.jackingaming.mealmaker3000pos.models.menuitems.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.UnknownMenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.Water;

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

        if (nameOfMenuItem.equals(Bread.NAME)) {
            return new Bread(menuItemAsJSON);
        } else if (nameOfMenuItem.equals(Water.NAME)) {
            return new Water(menuItemAsJSON);
        }
        // TODO: insert new menu item.
        else {
            Log.d(TAG, "parseToMenuItem(JSONObject) else-clause for UnknownMenuItem.");
            return new UnknownMenuItem(menuItemAsJSON);
        }
    }
}
