package com.jackingaming.mealmaker3000pos.models;

import android.util.Log;

import com.jackingaming.mealmaker3000pos.models.menuitems.Bread;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.Water;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private static final String TAG = "Meal";
    public static final String JSON_MENU_ITEMS = "menu_items";

    private List<MenuItem> menuItems;

    public Meal() {
        menuItems = new ArrayList<MenuItem>();
    }

    public Meal(JSONObject json) {
        menuItems = new ArrayList<MenuItem>();
        try {
            JSONArray jsonArray = json.optJSONArray(JSON_MENU_ITEMS);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject menuItemAsJSON = jsonArray.getJSONObject(i);
                    String nameOfMenuItem = menuItemAsJSON.getString(MenuItem.JSON_NAME);
                    if (nameOfMenuItem.equals("bread")) {
                        menuItems.add(new Bread(menuItemAsJSON));
                    } else if (nameOfMenuItem.equals("water")) {
                        menuItems.add(new Water(menuItemAsJSON));
                    } else {
                        Log.i(TAG, "nameOfMenuItem does NOT equals() \"bread\" nor \"water\".");
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfMenuItemInMeal() {
        return menuItems.size();
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public List<String> getNameOfMenuItems() {
        List<String> nameOfMenuItems = new ArrayList<String>();
        for (MenuItem menuItem : menuItems) {
            nameOfMenuItems.add(menuItem.getName());
        }
        return nameOfMenuItems;
    }

    public void clearMenuItems() {
        menuItems.clear();
    }

    public JSONObject toJSON() {
        JSONArray jsonArray = new JSONArray();
        for (MenuItem menuItem : menuItems) {
            jsonArray.put(menuItem.toJSON());
        }

        JSONObject json = new JSONObject();
        try {
            json.put(JSON_MENU_ITEMS, jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String getMenuItemsAsJSONStringSeparatedBySpace() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem menuItem : menuItems) {
            String menuItemAsJSONString = menuItem.toJSON().toString();
            sb.append(menuItemAsJSONString + " ");
        }
        return sb.toString();
    }
}
