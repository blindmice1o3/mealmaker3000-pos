package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class EspressoShotCustomization extends Customization {
    public static final String NAME = "EspressoShotCustomization";
    public static final String JSON_ROAST = "roast";
    public static final String JSON_QUANTITY = "quantity";
    public static final String JSON_TYPE = "type";
    public static final String JSON_PREP = "prep";

    public enum Roast { BLONDE, STANDARD_SIGNATURE, DECAF, THIRD_DECAF, HALF_DECAF, TWO_THIRD_DECAF; }
    public enum Quantity { STANDARD_TWO; }
    public enum Type { STANDARD_NONE, LONG_SHOT, RISTRETTO; }
    public enum Prep { STANDARD_NONE, UPSIDE_DOWN; }

    private Roast roast;
    private Quantity quantity;
    private Type type;
    private Prep prep;

    private EspressoShotCustomization(Builder builder) {
        super(NAME);
        this.roast = builder.roast;
        this.quantity = builder.quantity;
        this.type = builder.type;
        this.prep = builder.prep;
    }

    @SuppressLint("LongLogTag")
    public EspressoShotCustomization(JSONObject espressoShotCustomizationAsJSON) throws JSONException {
        super(espressoShotCustomizationAsJSON);

        String roastAsString = espressoShotCustomizationAsJSON.get(JSON_ROAST).toString();
        for (int i = 0; i < Roast.values().length; i++) {
            if (Roast.values()[i].toString().equals(roastAsString)) {
                Log.i("EspressoShotCustomization", "EspressoShotCustomization(JSONObject) Roast." + Roast.values()[i].toString());
                roast = Roast.values()[i];
                break;
            }
        }

        String quantityAsString = espressoShotCustomizationAsJSON.get(JSON_QUANTITY).toString();
        for (int i = 0; i < Quantity.values().length; i++) {
            if (Quantity.values()[i].toString().equals(quantityAsString)) {
                Log.i("EspressoShotCustomization", "EspressoShotCustomization(JSONObject) Quantity." + Quantity.values()[i].toString());
                quantity = Quantity.values()[i];
                break;
            }
        }

        String typeAsString = espressoShotCustomizationAsJSON.get(JSON_TYPE).toString();
        for (int i = 0; i < Type.values().length; i++) {
            if (Type.values()[i].toString().equals(typeAsString)) {
                Log.i("EspressoShotCustomization", "EspressoShotCustomization(JSONObject) Type." + Type.values()[i].toString());
                type = Type.values()[i];
                break;
            }
        }

        String prepAsString = espressoShotCustomizationAsJSON.get(JSON_PREP).toString();
        for (int i = 0; i < Prep.values().length; i++) {
            if (Prep.values()[i].toString().equals(prepAsString)) {
                Log.i("EspressoShotCustomization", "EspressoShotCustomization(JSONObject) Prep." + Prep.values()[i].toString());
                prep = Prep.values()[i];
                break;
            }
        }
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject espressoShotCustomizationAsJSON = super.toJSON();
        espressoShotCustomizationAsJSON.put(JSON_ROAST, roast);
        espressoShotCustomizationAsJSON.put(JSON_QUANTITY, quantity);
        espressoShotCustomizationAsJSON.put(JSON_TYPE, type);
        espressoShotCustomizationAsJSON.put(JSON_PREP, prep);
        return espressoShotCustomizationAsJSON;
    }

    public Roast getRoast() {
        return roast;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Type getType() {
        return type;
    }

    public Prep getPrep() {
        return prep;
    }

    public static class Builder {
        private Roast roast;
        private Quantity quantity;
        private Type type;
        private Prep prep;

        public Builder() {
        }

        public Builder roast(Roast roast) {
            this.roast = roast;
            return this;
        }

        public Builder quantity(Quantity quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder prep(Prep prep) {
            this.prep = prep;
            return this;
        }

        public EspressoShotCustomization build() {
            EspressoShotCustomization espressoShotCustomization = new EspressoShotCustomization(this);
            validate(espressoShotCustomization);
            return espressoShotCustomization;
        }

        private void validate(EspressoShotCustomization espressoShotCustomization) {
            // TODO: validations to check that EspressoShotCustomization object
            //  does not break any assumption of system
        }
    }
}