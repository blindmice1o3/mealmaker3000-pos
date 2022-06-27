package com.jackingaming.mealmaker3000pos.models;

import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    public static final String JSON_ID = "id";
    public static final String JSON_MENU_ITEMS = "menuItems";

    private long id;
    private List<MenuItem> menuItems;

    public Meal() {
        menuItems = new ArrayList<MenuItem>();
    }

    public Meal(JSONObject mealAsJSON) {
        this();

        try {
            id = mealAsJSON.optLong(JSON_ID);

            JSONArray menuItemsAsJSONArray = (JSONArray) mealAsJSON.get(JSON_MENU_ITEMS);
            menuItems = Menu.convertToListOfMenuItem(menuItemsAsJSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MenuItem getMenuItem(int index) {
        return menuItems.get(index);
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void removeMenuItem(int index) {
        menuItems.remove(index);
    }

    public void clearMenuItems() {
        menuItems.clear();
    }

    public int sizeOfMenuItems() {
        return menuItems.size();
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put(JSON_ID, id);
            json.put(JSON_MENU_ITEMS, Menu.convertToJSONArray(menuItems));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
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
