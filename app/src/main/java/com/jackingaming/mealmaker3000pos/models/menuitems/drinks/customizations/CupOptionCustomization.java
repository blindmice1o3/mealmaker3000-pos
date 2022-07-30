package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class CupOptionCustomization extends Customization {
    public static final String NAME = "CupOptionCustomization";
    public static final String JSON_CUP_SIZE = "cup size";

    public enum CupSize {STANDARD_NO, TALL, GRANDE, VENTI;}

    private CupSize cupSize;

    private CupOptionCustomization(Builder builder) {
        super(NAME);
        this.cupSize = builder.cupSize;
    }

    public CupOptionCustomization(JSONObject cupOptionCustomizationAsJSON) throws JSONException {
        super(cupOptionCustomizationAsJSON);

        if (cupOptionCustomizationAsJSON.has(JSON_CUP_SIZE)) {
            String cupSizeAsString = cupOptionCustomizationAsJSON.get(JSON_CUP_SIZE).toString();
            for (int i = 0; i < CupSize.values().length; i++) {
                if (CupSize.values()[i].toString().equals(cupSizeAsString)) {
                    Log.d("CupOptionCustomization", "CupOptionCustomization(JSONObject) CupSize." + CupSize.values()[i].toString());
                    cupSize = CupSize.values()[i];
                    break;
                }
            }
        } else {
            Log.d("CupOptionCustomization", "CupOptionCustomization(JSONObject) cupOptionCustomizationAsJSON does NOT has(JSON_CUP_SIZE)");
        }
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject cupOptionCustomizationAsJSON = super.toJSON();
        cupOptionCustomizationAsJSON.put(JSON_CUP_SIZE, cupSize);
        return cupOptionCustomizationAsJSON;
    }

    public CupSize getCupSize() {
        return cupSize;
    }

    public static class Builder {
        private CupSize cupSize;

        public Builder() {
        }

        public Builder cupSize(CupSize cupSize) {
            this.cupSize = cupSize;
            return this;
        }

        public CupOptionCustomization build() {
            CupOptionCustomization cupOptionCustomization = new CupOptionCustomization(this);
            validate(cupOptionCustomization);
            return cupOptionCustomization;
        }

        private void validate(CupOptionCustomization cupOptionCustomization) {
            // TODO: validations to check that CupOptionCustomization object
            //  does not break any assumption of system
        }
    }
}