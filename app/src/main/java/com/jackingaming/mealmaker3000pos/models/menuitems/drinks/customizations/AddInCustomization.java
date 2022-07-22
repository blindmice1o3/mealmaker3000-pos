package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import org.json.JSONException;
import org.json.JSONObject;

public class AddInCustomization extends Customization {
    public static final String NAME = "AddInCustomization";
    public static final String JSON_LINE_THE_CUP = "line the cup";
    public static final String JSON_POWDER = "powder";

    public enum LineTheCup {STANDARD_NO, CARAMEL, MOCHA;}
    public enum Powder {CHOCOLATE_MALT, VANILLA_BEAN;}

    private LineTheCup lineTheCup;
    private Powder powder;

    private AddInCustomization(Builder builder) {
        super(NAME);
        this.lineTheCup = builder.lineTheCup;
        this.powder = builder.powder;
    }

    public AddInCustomization(JSONObject addInCustomizationAsJSON) throws JSONException {
        super(addInCustomizationAsJSON);
        lineTheCup = (LineTheCup) addInCustomizationAsJSON.get(JSON_LINE_THE_CUP);
        powder = (Powder) addInCustomizationAsJSON.get(JSON_POWDER);
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject addInCustomizationAsJSON = super.toJSON();
        addInCustomizationAsJSON.put(JSON_LINE_THE_CUP, lineTheCup);
        addInCustomizationAsJSON.put(JSON_POWDER, powder);
        return addInCustomizationAsJSON;
    }

    public LineTheCup getLineTheCup() {
        return lineTheCup;
    }

    public Powder getPowder() {
        return powder;
    }

    public static class Builder {
        private LineTheCup lineTheCup;
        private Powder powder;

        public Builder() {
        }

        public Builder lineTheCup(LineTheCup lineTheCup) {
            this.lineTheCup = lineTheCup;
            return this;
        }

        public Builder powder(Powder powder) {
            this.powder = powder;
            return this;
        }

        public AddInCustomization build() {
            AddInCustomization addInCustomization = new AddInCustomization(this);
            validate(addInCustomization);
            return addInCustomization;
        }

        private void validate(AddInCustomization addInCustomization) {
            // TODO: validations to check that AddInCustomization object
            //  does not break any assumption of system
        }
    }
}