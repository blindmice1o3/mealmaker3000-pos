package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import android.util.Log;

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

        if (teaCustomizationAsJSON.has(JSON_ADD_CHAI)) {
            String addChaiAsString = teaCustomizationAsJSON.get(JSON_ADD_CHAI).toString();
            for (int i = 0; i < AddChai.values().length; i++) {
                if (AddChai.values()[i].toString().equals(addChaiAsString)) {
                    Log.d("TeaCustomization", "TeaCustomization(JSONObject) AddChai." + AddChai.values()[i].toString());
                    addChai = AddChai.values()[i];
                    break;
                }
            }
        } else {
            Log.d("TeaCustomization", "TeaCustomization(JSONObject) teaCustomizationAsJSON does NOT has(JSON_ADD_CHAI)");
        }
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject teaCustomizationAsJSON = super.toJSON();
        teaCustomizationAsJSON.put(JSON_ADD_CHAI, addChai);
        return teaCustomizationAsJSON;
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