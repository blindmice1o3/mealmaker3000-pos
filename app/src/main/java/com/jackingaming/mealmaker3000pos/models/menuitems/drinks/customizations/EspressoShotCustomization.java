package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

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

    public EspressoShotCustomization(JSONObject espressoShotCustomizationAsJSON) throws JSONException {
        super(espressoShotCustomizationAsJSON);
        roast = (Roast) espressoShotCustomizationAsJSON.get(JSON_ROAST);
        quantity = (Quantity) espressoShotCustomizationAsJSON.get(JSON_QUANTITY);
        type = (Type) espressoShotCustomizationAsJSON.get(JSON_TYPE);
        prep = (Prep) espressoShotCustomizationAsJSON.get(JSON_PREP);
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