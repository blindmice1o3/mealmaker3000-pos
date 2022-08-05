package com.jackingaming.mealmaker3000pos.models.menuitems.drinks;

import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.AddInCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.FlavorCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.ToppingCustomization;

import org.json.JSONException;
import org.json.JSONObject;

public class Water extends Drink {
    public static final String NAME = "water";
    public static final String DESCRIPTION = "At least 3 times more hydrating than bread!";

    public Water() {
        super(NAME, DESCRIPTION, 0.05);
        customizations.get(AddInCustomization.NAME).add(
                new AddInCustomization.Builder()
                        .powder(AddInCustomization.Powder.VANILLA_BEAN)
                        .lineTheCup(AddInCustomization.LineTheCup.MOCHA)
                        .build());
        customizations.get(FlavorCustomization.NAME).add(
                new FlavorCustomization.Builder()
                        .sauce(FlavorCustomization.Sauce.WHITE_CHOCOLATE_MOCHA)
                        .syrup(FlavorCustomization.Syrup.TOFFEE_NUT)
                        .build());
        customizations.get(ToppingCustomization.NAME).add(
                new ToppingCustomization.Builder()
                        .coldFoam(ToppingCustomization.ColdFoam.SALTED_CARAMEL_CREAM)
                        .cinnamonPowder(ToppingCustomization.CinnamonPowder.LIGHT)
                        .drizzle(ToppingCustomization.Drizzle.CARAMEL)
                        .cinnamonDolceSprinkles(ToppingCustomization.CinnamonDolceSprinkles.MEDIUM)
                        .whippedCream(ToppingCustomization.WhippedCream.EXTRA)
                        .build());
    }

    public Water(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);
        // TODO: insert new member variables
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject json = super.toJSON();
        // TODO: insert new member variables.
        return json;
    }
}
