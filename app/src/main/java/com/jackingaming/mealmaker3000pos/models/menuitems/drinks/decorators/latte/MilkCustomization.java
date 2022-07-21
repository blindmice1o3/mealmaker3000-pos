package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.decorators.latte;

import org.json.JSONException;
import org.json.JSONObject;

public class MilkCustomization extends Customization {
    public static final String NAME = "MilkCustomization";
    public static final String JSON_FOAM = "foam";
    public static final String JSON_TYPE = "type";
    public static final String JSON_TEMPERATURE = "temperature";

    public enum Foam { NO, LIGHT, MEDIUM, EXTRA; }
    public enum Type { TWOPERCENT, WHOLE, NONFAT, BREVE, HEAVYCREAM, COCONUT, OAT, SOY, ALMOND; }
    public enum Temperature { WARM, MEDIUM, EXTRAHOT; }

    // TODO: move standards (aka defaults) to Latte class.
    private static final Foam STANDARD_MILK_FOAM = Foam.MEDIUM;
    private static final Type STANDARD_MILK_OPTION = Type.TWOPERCENT;
    private static final Temperature STANDARD_MILK_TEMPERATURE = Temperature.MEDIUM;

    private Foam foam;
    private Type type;
    private Temperature temperature;

    private MilkCustomization(Builder builder) {
        super(NAME);
        this.foam = builder.foam;
        this.type = builder.type;
        this.temperature = builder.temperature;
    }

    public MilkCustomization(JSONObject milkCustomizationAsJSON) throws JSONException {
        super(milkCustomizationAsJSON);
        foam = (Foam) milkCustomizationAsJSON.get(JSON_FOAM);
        type = (Type) milkCustomizationAsJSON.get(JSON_TYPE);
        temperature = (Temperature) milkCustomizationAsJSON.get(JSON_TEMPERATURE);
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject milkCustomizationAsJSON = super.toJSON();
        milkCustomizationAsJSON.put(JSON_FOAM, foam);
        milkCustomizationAsJSON.put(JSON_TYPE, type);
        milkCustomizationAsJSON.put(JSON_TEMPERATURE, temperature);
        return milkCustomizationAsJSON;
    }

    public static class Builder {
        private Foam foam;
        private Type type;
        private Temperature temperature;

        public Builder() {
        }

        public Builder foam(Foam foam) {
            this.foam = foam;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder temperature(Temperature temperature) {
            this.temperature = temperature;
            return this;
        }

        public MilkCustomization build() {
            MilkCustomization milkCustomization = new MilkCustomization(this);
            validate(milkCustomization);
            return milkCustomization;
        }

        private void validate(MilkCustomization milkCustomization) {
            // TODO: validations to check that MilkCustomization object
            //  does not break any assumption of system
        }
    }
}