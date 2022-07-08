package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators;

import android.util.Log;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.Drink;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class CustomizationDecorator extends Drink {
    protected Drink drink;

    public CustomizationDecorator(Drink drink, String name, String description, double price) {
        super(name, description, price);
        this.drink = drink;
    }

    public CustomizationDecorator(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        // TODO: insert new member variables
    }

    public boolean isAlreadyWrapped(String targetName) {
        String[] names = getName().split(",");
        for (String name : names) {
            String trimmedName = name.trim();
            Log.i("CustomizationDecorator", "trimmedName: " + trimmedName + ", targetName: " + targetName);
            if (trimmedName.equals(targetName)) {
                Log.i("CustomizationDecorator", "TRUE trimmedName: " + trimmedName + ", targetName: " + targetName);
                return true;
            }
        }

        return false;
    }

    public void removeDecoratorViaDepth(int targetDepth) {
        if (targetDepth <= 0) {
            Log.i("CustomizationDecorator", "removeDecoratorViaDepth(int) targetDepth <= 0... this method does not handle the case where targetDepth is 0.");
            return;
        }

        CustomizationDecorator decoratorToBeRemoved = this;
        Drink drinkOfDecoratorToBeRemoved = drink;
        CustomizationDecorator decoratorOnePriorToDecoratorToBeRemoved = null;
        for (int i = 0; i < targetDepth; i++) {
            if (i == targetDepth - 1) {
                decoratorOnePriorToDecoratorToBeRemoved = decoratorToBeRemoved;
            }

            if (drinkOfDecoratorToBeRemoved instanceof CustomizationDecorator) {
                decoratorToBeRemoved = (CustomizationDecorator) drinkOfDecoratorToBeRemoved;
                drinkOfDecoratorToBeRemoved = decoratorToBeRemoved.getDrink();
            } else {
                Log.i("CustomizationDecorator", "drinkOfDecoratorToBeRemoved at depth:" + i + " is NOT a CustomizationDecorator.");
            }
        }

        if (decoratorOnePriorToDecoratorToBeRemoved != null) {
            decoratorOnePriorToDecoratorToBeRemoved.setDrink(drinkOfDecoratorToBeRemoved);
        } else {
            Log.i("CustomizationDecorator", "decoratorOnePriorToDecoratorToBeRemoved == null");
        }
    }

    public int findDepthOfCustomizationDecorator(String targetName) {
        String[] namesInReversedOrderAsArray = getName().split(",");
        List<String> namesAsList = Arrays.asList(namesInReversedOrderAsArray);
        Collections.reverse(namesAsList);

        for (int depth = 0; depth < namesAsList.size(); depth++) {
            String trimmedName = namesAsList.get(depth).trim();
            if (trimmedName.equals(targetName)) {
                Log.i("CustomizationDecorator", "findDepthOfCustomizationDecorator(String) trimmedName equals targetName at depth: " + depth);
                return depth;
            }
        }
        return -1;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public abstract String getName();

    public abstract double getPrice();
}