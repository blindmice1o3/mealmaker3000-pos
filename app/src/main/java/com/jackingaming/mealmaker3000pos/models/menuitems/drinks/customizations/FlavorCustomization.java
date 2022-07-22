package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import org.json.JSONException;
import org.json.JSONObject;

public class FlavorCustomization extends Customization {
    public static final String NAME = "FlavorCustomization";
    public static final String JSON_SAUCE = "sauce";
    public static final String JSON_SYRUP = "syrup";

    public enum Sauce { DARK_CARAMEL, MOCHA, WHITE_CHOCOLATE_MOCHA; }
    public enum Syrup { BROWN_SUGAR, CARAMEL, CINNAMON_DOLCE, HAZELNUT, PEPPERMINT, RASPBERRY,
        SF_VANILLA, TOASTED_VANILLA, TOFFEE_NUT, VANILLA; }

    private Sauce sauce;
    private Syrup syrup;

    private FlavorCustomization(Builder builder) {
        super(NAME);
        this.sauce = builder.sauce;
        this.syrup = builder.syrup;
    }

    public FlavorCustomization(JSONObject flavorCustomizationAsJSON) throws JSONException {
        super(flavorCustomizationAsJSON);
        sauce = (Sauce) flavorCustomizationAsJSON.get(JSON_SAUCE);
        syrup = (Syrup) flavorCustomizationAsJSON.get(JSON_SYRUP);
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject flavorCustomizationAsJSON = super.toJSON();
        flavorCustomizationAsJSON.put(JSON_SAUCE, sauce);
        flavorCustomizationAsJSON.put(JSON_SYRUP, syrup);
        return flavorCustomizationAsJSON;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public Syrup getSyrup() {
        return syrup;
    }

    public static class Builder {
        private Sauce sauce;
        private Syrup syrup;

        public Builder() {
        }

        public Builder sauce(Sauce sauce) {
            this.sauce = sauce;
            return this;
        }

        public Builder syrup(Syrup syrup) {
            this.syrup = syrup;
            return this;
        }

        public FlavorCustomization build() {
            FlavorCustomization flavorCustomization = new FlavorCustomization(this);
            validate(flavorCustomization);
            return flavorCustomization;
        }

        private void validate(FlavorCustomization flavorCustomization) {
            // TODO: validations to check that FlavorCustomization object
            //  does not break any assumption of system
        }
    }
}