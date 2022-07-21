package com.jackingaming.mealmaker3000pos.models;

import android.util.Log;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.CustomizationDecorator;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.UnknownCustomizationDecorator;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup.LineTheCupWithCaramelCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup.LineTheCupWithMochaCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.linethecup.LineTheCupWithNothingCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.powders.ChocolateMaltPowderCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.addins.powders.VanillaBeanPowderCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.AddInCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.CupOptionCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.Customization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.EspressoShotCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.FlavorCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.MilkCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.SweetenerCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.TeaCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.ToppingCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte.UnknownCustomization;
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

    public static CustomizationDecorator parseToCustomizationDecorator(JSONObject customizationDecoratorAsJSON)
            throws JSONException {
        String nameOfCustomizationDecorator = customizationDecoratorAsJSON.getString(CustomizationDecorator.JSON_NAME);

        if (LineTheCupWithCaramelCustomization.NAME.equals(nameOfCustomizationDecorator)) {
            return new LineTheCupWithCaramelCustomization(customizationDecoratorAsJSON);
        } else if (LineTheCupWithMochaCustomization.NAME.equals(nameOfCustomizationDecorator)) {
            return new LineTheCupWithMochaCustomization(customizationDecoratorAsJSON);
        } else if (LineTheCupWithNothingCustomization.NAME.equals(nameOfCustomizationDecorator)) {
            return new LineTheCupWithNothingCustomization(customizationDecoratorAsJSON);
        } else if (ChocolateMaltPowderCustomization.NAME.equals(nameOfCustomizationDecorator)) {
            return new ChocolateMaltPowderCustomization(customizationDecoratorAsJSON);
        } else if (VanillaBeanPowderCustomization.NAME.equals(nameOfCustomizationDecorator)) {
            return new VanillaBeanPowderCustomization(customizationDecoratorAsJSON);
        }
        // TODO: insert new customization decorator.
        else {
            Log.d(TAG, "parseToCustomizationDecorator(JSONObject) else-clause for UnknownCustomizationDecorator.");
            return new UnknownCustomizationDecorator(customizationDecoratorAsJSON);
        }
    }
}
