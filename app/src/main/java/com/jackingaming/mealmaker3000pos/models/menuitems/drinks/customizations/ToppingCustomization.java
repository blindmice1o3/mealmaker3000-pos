package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ToppingCustomization extends Customization {
    public static final String NAME = "ToppingCustomization";
    public static final String JSON_COLD_FOAM = "cold foam";
    public static final String JSON_CINNAMON_POWDER = "cinnamon powder";
    public static final String JSON_DRIZZLE = "drizzle";
    public static final String JSON_CINNAMON_DOLCE_SPRINKLES = "cinnamon dolce sprinkles";
    public static final String JSON_WHIPPED_CREAM = "whipped cream";

    public enum ColdFoam { CHOCOLATE_CREAM, SALTED_CARAMEL_CREAM, VANILLA_SWEET_CREAM; }
    public enum CinnamonPowder { STANDARD_NO, LIGHT, MEDIUM, EXTRA; }
    public enum Drizzle { CARAMEL, MOCHA; }
    public enum CinnamonDolceSprinkles { STANDARD_NO, LIGHT, MEDIUM, EXTRA; }
    public enum WhippedCream { STANDARD_NO, LIGHT, MEDIUM, EXTRA; }

    private ColdFoam coldFoam;
    private CinnamonPowder cinnamonPowder;
    private Drizzle drizzle;
    private CinnamonDolceSprinkles cinnamonDolceSprinkles;
    private WhippedCream whippedCream;

    private ToppingCustomization(Builder builder) {
        super(NAME);
        this.coldFoam = builder.coldFoam;
        this.cinnamonPowder = builder.cinnamonPowder;
        this.drizzle = builder.drizzle;
        this.cinnamonDolceSprinkles = builder.cinnamonDolceSprinkles;
        this.whippedCream = builder.whippedCream;
    }

    public ToppingCustomization(JSONObject toppingCustomizationAsJSON) throws JSONException {
        super(toppingCustomizationAsJSON);

        String coldFoamAsString = toppingCustomizationAsJSON.get(JSON_COLD_FOAM).toString();
        for (int i = 0; i < ColdFoam.values().length; i++) {
            if (ColdFoam.values()[i].toString().equals(coldFoamAsString)) {
                Log.i("ToppingCustomization", "ToppingCustomization(JSONObject) ColdFoam." + ColdFoam.values()[i].toString());
                coldFoam = ColdFoam.values()[i];
                break;
            }
        }

        String cinnamonPowderAsString = toppingCustomizationAsJSON.get(JSON_CINNAMON_POWDER).toString();
        for (int i = 0; i < CinnamonPowder.values().length; i++) {
            if (CinnamonPowder.values()[i].toString().equals(cinnamonPowderAsString)) {
                Log.i("ToppingCustomization", "ToppingCustomization(JSONObject) CinnamonPowder." + CinnamonPowder.values()[i].toString());
                cinnamonPowder = CinnamonPowder.values()[i];
                break;
            }
        }

        String drizzleAsString = toppingCustomizationAsJSON.get(JSON_DRIZZLE).toString();
        for (int i = 0; i < Drizzle.values().length; i++) {
            if (Drizzle.values()[i].toString().equals(drizzleAsString)) {
                Log.i("ToppingCustomization", "ToppingCustomization(JSONObject) Drizzle." + Drizzle.values()[i].toString());
                drizzle = Drizzle.values()[i];
                break;
            }
        }

        String cinnamonDolceSprinklesAsString = toppingCustomizationAsJSON.get(JSON_CINNAMON_DOLCE_SPRINKLES).toString();
        for (int i = 0; i < CinnamonDolceSprinkles.values().length; i++) {
            if (CinnamonDolceSprinkles.values()[i].toString().equals(cinnamonDolceSprinklesAsString)) {
                Log.i("ToppingCustomization", "ToppingCustomization(JSONObject) CinnamonDolceSprinkles." + CinnamonDolceSprinkles.values()[i].toString());
                cinnamonDolceSprinkles = CinnamonDolceSprinkles.values()[i];
                break;
            }
        }

        String whippedCreamAsString = toppingCustomizationAsJSON.get(JSON_WHIPPED_CREAM).toString();
        for (int i = 0; i < WhippedCream.values().length; i++) {
            if (WhippedCream.values()[i].toString().equals(whippedCreamAsString)) {
                Log.i("ToppingCustomization", "ToppingCustomization(JSONObject) WhippedCream." + WhippedCream.values()[i].toString());
                whippedCream = WhippedCream.values()[i];
                break;
            }
        }
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject toppingCustomizationAsJSON = super.toJSON();
        toppingCustomizationAsJSON.put(JSON_COLD_FOAM, coldFoam);
        toppingCustomizationAsJSON.put(JSON_CINNAMON_POWDER, cinnamonPowder);
        toppingCustomizationAsJSON.put(JSON_DRIZZLE, drizzle);
        toppingCustomizationAsJSON.put(JSON_CINNAMON_DOLCE_SPRINKLES, cinnamonDolceSprinkles);
        toppingCustomizationAsJSON.put(JSON_WHIPPED_CREAM, whippedCream);
        return toppingCustomizationAsJSON;
    }

    public ColdFoam getColdFoam() {
        return coldFoam;
    }

    public CinnamonPowder getCinnamonPowder() {
        return cinnamonPowder;
    }

    public Drizzle getDrizzle() {
        return drizzle;
    }

    public CinnamonDolceSprinkles getCinnamonDolceSprinkles() {
        return cinnamonDolceSprinkles;
    }

    public WhippedCream getWhippedCream() {
        return whippedCream;
    }

    public static class Builder {
        private ColdFoam coldFoam;
        private CinnamonPowder cinnamonPowder;
        private Drizzle drizzle;
        private CinnamonDolceSprinkles cinnamonDolceSprinkles;
        private WhippedCream whippedCream;

        public Builder() {
        }

        public Builder coldFoam(ColdFoam coldFoam) {
            this.coldFoam = coldFoam;
            return this;
        }

        public Builder cinnamonPowder(CinnamonPowder cinnamonPowder) {
            this.cinnamonPowder = cinnamonPowder;
            return this;
        }

        public Builder drizzle(Drizzle drizzle) {
            this.drizzle = drizzle;
            return this;
        }

        public Builder cinnamonDolceSprinkles(CinnamonDolceSprinkles cinnamonDolceSprinkles) {
            this.cinnamonDolceSprinkles = cinnamonDolceSprinkles;
            return this;
        }

        public Builder whippedCream(WhippedCream whippedCream) {
            this.whippedCream = whippedCream;
            return this;
        }

        public ToppingCustomization build() {
            ToppingCustomization toppingCustomization = new ToppingCustomization(this);
            validate(toppingCustomization);
            return toppingCustomization;
        }

        private void validate(ToppingCustomization toppingCustomization) {
            // TODO: validations to check that ToppingCustomization object
            //  does not break any assumption of system
        }
    }
}