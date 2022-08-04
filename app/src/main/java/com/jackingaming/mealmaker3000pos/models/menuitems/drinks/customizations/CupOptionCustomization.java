package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class CupOptionCustomization extends Customization {
    public static final String NAME = "CupOptionCustomization";
    public static final String JSON_CUP_SIZE = "cup size";

    public enum CupSize { STANDARD_NO, TALL, GRANDE, VENTI; }

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

    @Override
    public double getPrice() {
        // TODO:
        return 0;
    }

    @Override
    public boolean isMergeable(Customization customizationToBeAdded) {
//        if (customizationToBeAdded instanceof AddInCustomization) {
//            Log.i("AddInCustomization", "isMergeable(Customization) customizationToBeAdded instanceof AddInCustomization");
//            AddInCustomization toBeAdded = (AddInCustomization) customizationToBeAdded;
//
//            // TODO: only CHECKING for mergeability... NOT merging in this method.
//            if (lineTheCup == null && toBeAdded.getLineTheCup() == null) {
//                return true;
//            } else if (lineTheCup == null && toBeAdded.getLineTheCup() != null) {
//                return true;
//            } else if (lineTheCup != null && toBeAdded.getLineTheCup() == null) {
//                return true;
//            } else if (lineTheCup != null && toBeAdded.getLineTheCup() != null){
//                Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup != null && toBeAdded.getLineTheCup() != null");
//                if (lineTheCup == toBeAdded.getLineTheCup()) {
//                    Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup: " + lineTheCup.toString());
//                    Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup == toBeAdded.getLineTheCup()");
//
//
//                    return false;
//                } else {
//                    Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup NOT == toBeAdded.getLineTheCup()");
//                    return true;
//                }
//            } else {
//                return false;
//            }
//        } else {
//            Log.i("AddInCustomization", "isMergeable(Customization) customizationToBeAdded is NOT instanceof AddInCustomization");
//            return false;
//        }
        return true;
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