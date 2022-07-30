package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import android.util.Log;

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

        if (milkCustomizationAsJSON.has(JSON_FOAM)) {
            String foamAsString = milkCustomizationAsJSON.get(JSON_FOAM).toString();
            for (int i = 0; i < Foam.values().length; i++) {
                if (Foam.values()[i].toString().equals(foamAsString)) {
                    Log.d("MilkCustomization", "MilkCustomization(JSONObject) Foam." + Foam.values()[i].toString());
                    foam = Foam.values()[i];
                    break;
                }
            }
        } else {
            Log.d("MilkCustomization", "MilkCustomization(JSONObject) does NOT has(JSON_FOAM)");
        }

        if (milkCustomizationAsJSON.has(JSON_TYPE)) {
            String typeAsString = milkCustomizationAsJSON.get(JSON_TYPE).toString();
            for (int i = 0; i < Type.values().length; i++) {
                if (Type.values()[i].toString().equals(typeAsString)) {
                    Log.d("MilkCustomization", "MilkCustomization(JSONObject) Type." + Type.values()[i].toString());
                    type = Type.values()[i];
                    break;
                }
            }
        } else {
            Log.d("MilkCustomization", "MilkCustomization(JSONObject) milkCustomizationAsJSON does NOT has(JSON_TYPE)");
        }

        if (milkCustomizationAsJSON.has(JSON_TEMPERATURE)) {
            String temperatureAsString = milkCustomizationAsJSON.get(JSON_TEMPERATURE).toString();
            for (int i = 0; i < Temperature.values().length; i++) {
                if (Temperature.values()[i].toString().equals(temperatureAsString)) {
                    Log.d("MilkCustomization", "MilkCustomization(JSONObject) Temperature." + Temperature.values()[i].toString());
                    temperature = Temperature.values()[i];
                    break;
                }
            }
        } else {
            Log.d("MilkCustomization", "MilkCustomization(JSONObject) milkCustomizationAsJSON does NOT has(JSON_TEMPERATURE)");
        }
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

    public Foam getFoam() {
        return foam;
    }

    public Type getType() {
        return type;
    }

    public Temperature getTemperature() {
        return temperature;
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