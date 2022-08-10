package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import org.json.JSONException;
import org.json.JSONObject;

public class UnknownCustomization extends Customization {
    public static final String NAME = "UnknownCustomization";

    private UnknownCustomization(Builder builder) {
        super(NAME);
    }

    public UnknownCustomization(JSONObject unknownCustomizationAsJSON) throws JSONException {
        super(unknownCustomizationAsJSON);
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject unknownCustomizationAsJSON = super.toJSON();
        return unknownCustomizationAsJSON;
    }

    @Override
    public double getPrice() {
        // TODO:
        return 0;
    }

    public static class Builder {
        public Builder() {
        }

        public UnknownCustomization build() {
            UnknownCustomization unknownCustomization = new UnknownCustomization(this);
            validate(unknownCustomization);
            return unknownCustomization;
        }

        private void validate(UnknownCustomization unknownCustomization) {
            // TODO: validations to check that UnknownCustomization object
            //  does not break any assumption of system
        }
    }
}
