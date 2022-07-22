package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import org.json.JSONException;
import org.json.JSONObject;

public class TeaCustomization extends Customization {
    public static final String NAME = "TeaCustomization";
    public static final String JSON_ADD_CHAI = "add chai";

    public enum AddChai { NUMBER_OF_PUMPS; }

    private AddChai addChai;

    private TeaCustomization(Builder builder) {
        super(NAME);
        this.addChai = builder.addChai;
    }

    public TeaCustomization(JSONObject teaCustomizationAsJSON) throws JSONException {
        super(teaCustomizationAsJSON);
        addChai = (AddChai) teaCustomizationAsJSON.get(JSON_ADD_CHAI);
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject teaCustomizationAsJSON = super.toJSON();
        teaCustomizationAsJSON.put(JSON_ADD_CHAI, addChai);
        return teaCustomizationAsJSON;
    }

    public AddChai getAddChai() {
        return addChai;
    }

    public static class Builder {
        private AddChai addChai;

        public Builder() {
        }

        public Builder addChai(AddChai addChai) {
            this.addChai = addChai;
            return this;
        }

        public TeaCustomization build() {
            TeaCustomization teaCustomization = new TeaCustomization(this);
            validate(teaCustomization);
            return teaCustomization;
        }

        private void validate(TeaCustomization teaCustomization) {
            // TODO: validations to check that TeaCustomization object
            //  does not break any assumption of system
        }
    }
}