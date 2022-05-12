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
    public static final String JSON_ID = "id";
    public static final String JSON_MENU_ITEMS = "menuItems";

    private long id;
    private List<MenuItem> menuItems;

    public Meal() {
        menuItems = new ArrayList<MenuItem>();
    }

    public Meal(JSONObject json) {
        this();

        try {
            id = json.optLong(JSON_ID);
            Log.i(TAG, "Meal(JSONObject) constructor id: " + id);

            JSONArray menuItemsAsJSONArray = (JSONArray) json.get(JSON_MENU_ITEMS);
            Log.i(TAG, "Meal(JSONObject) constructor menuItemsAsJSONArray: " + menuItemsAsJSONArray);
            for (int i = 0; i < menuItemsAsJSONArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) menuItemsAsJSONArray.get(i);
                MenuItem menuItem = new MenuItem(jsonObject);
                Log.i(TAG, "Meal(JSONObject) constructor menuItem.price: " + menuItem + "-" + menuItem.getPrice());
                menuItems.add(menuItem);
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
        JSONObject json = new JSONObject();
        try {
            json.put(JSON_ID, id);
            json.put(JSON_MENU_ITEMS, convertToJSONArray(menuItems));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private JSONArray convertToJSONArray(List<MenuItem> menuItems) {
        JSONArray jsonArray = new JSONArray();
        for (MenuItem menuItem : menuItems) {
            JSONObject menuItemAsJSON = menuItem.toJSON();
            jsonArray.put(menuItemAsJSON);
        }
        return jsonArray;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
