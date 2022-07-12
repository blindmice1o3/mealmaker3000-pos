package com.jackingaming.mealmaker3000pos.models.menuitems;

import com.jackingaming.mealmaker3000pos.models.Menu;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuItem {
    public static final String JSON_NAME = "name";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_PRICE = "price";
    public static final String JSON_CUSTOMIZATION_DECORATORS = "customization decorators";

    protected String name;
    protected String description;
    protected double price;
    protected List<CustomizationDecorator> customizationDecorators;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        customizationDecorators = new ArrayList<>();
    }

    public MenuItem(JSONObject menuItemAsJSON)
            throws JSONException {
        name = menuItemAsJSON.getString(JSON_NAME);
        description = menuItemAsJSON.getString(JSON_DESCRIPTION);
        price = menuItemAsJSON.getDouble(JSON_PRICE);

        customizationDecorators = new ArrayList<>();
        JSONArray customizationDecoratorsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_CUSTOMIZATION_DECORATORS);
        for (int i = 0; i < customizationDecoratorsAsJSONArray.length(); i++) {
            JSONObject customizationDecoratorAsJSON = (JSONObject) customizationDecoratorsAsJSONArray.get(i);
            CustomizationDecorator customizationDecorator = Menu.parseToCustomizationDecorator(customizationDecoratorAsJSON);
            customizationDecorators.add(customizationDecorator);
        }
    }

    public JSONObject toJSON()
            throws JSONException {
        JSONObject menuItemAsJSON = new JSONObject();
        menuItemAsJSON.put(JSON_NAME, name);
        menuItemAsJSON.put(JSON_DESCRIPTION, description);
        menuItemAsJSON.put(JSON_PRICE, price);

        JSONArray customizationDecoratorsAsJSONArray = new JSONArray();
        for (CustomizationDecorator customizationDecorator : customizationDecorators) {
            JSONObject customizationDecoratorAsJSON = customizationDecorator.toJSON();
            customizationDecoratorsAsJSONArray.put(customizationDecoratorAsJSON);
        }
        menuItemAsJSON.put(JSON_CUSTOMIZATION_DECORATORS, customizationDecoratorsAsJSONArray);

        return menuItemAsJSON;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<CustomizationDecorator> getCustomizationDecorators() {
        return customizationDecorators;
    }

    public boolean hasCustomizationDecorators() {
        return !customizationDecorators.isEmpty();
    }
}
