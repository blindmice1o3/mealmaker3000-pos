package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class AddInCustomization extends Customization {
    public static final String NAME = "AddInCustomization";
    public static final String JSON_LINE_THE_CUP = "line the cup";
    public static final String JSON_POWDER = "powder";

    public enum LineTheCup {
        STANDARD_NO(0.00),
        CARAMEL(0.60),
        MOCHA(0.35);

        private final double price;
        LineTheCup(double price) {
            this.price = price;
        }
        double getPrice() { return price; }
    }

    public enum Powder {
        CHOCOLATE_MALT(0.25),
        VANILLA_BEAN(0.20);

        private final double price;
        Powder(double price) {
            this.price = price;
        }
        double getPrice() { return price; }
    }

    private LineTheCup lineTheCup;
    private Powder powder;

    private AddInCustomization(Builder builder) {
        super(NAME);
        this.lineTheCup = builder.lineTheCup;
        this.powder = builder.powder;
    }

    public AddInCustomization(JSONObject addInCustomizationAsJSON) throws JSONException {
        super(addInCustomizationAsJSON);

        if (addInCustomizationAsJSON.has(JSON_LINE_THE_CUP)) {
            String lineTheCupAsString = addInCustomizationAsJSON.get(JSON_LINE_THE_CUP).toString();
            for (int i = 0; i < LineTheCup.values().length; i++) {
                if (LineTheCup.values()[i].toString().equals(lineTheCupAsString)) {
                    Log.d("AddInCustomization", "AddInCustomization(JSONObject) LineTheCup." + LineTheCup.values()[i].toString());
                    lineTheCup = LineTheCup.values()[i];
                    break;
                }
            }
        } else {
            Log.d("AddInCustomization", "AddInCustomization(JSONObject) addInCustomizationAsJSON does NOT has(JSON_LINE_THE_CUP)");
        }

        if (addInCustomizationAsJSON.has(JSON_POWDER)) {
            String powderAsString = addInCustomizationAsJSON.get(JSON_POWDER).toString();
            for (int i = 0; i < Powder.values().length; i++) {
                if (Powder.values()[i].toString().equals(powderAsString)) {
                    Log.d("AddInCustomization", "AddInCustomization(JSONObject) Powder." + Powder.values()[i].toString());
                    powder = Powder.values()[i];
                    break;
                }
            }
        } else {
            Log.d("AddInCustomization", "AddInCustomization(JSONObject) addInCustomizationAsJSON does NOT has(JSON_POWDER)");
        }
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject addInCustomizationAsJSON = super.toJSON();
        addInCustomizationAsJSON.put(JSON_LINE_THE_CUP, lineTheCup);
        addInCustomizationAsJSON.put(JSON_POWDER, powder);
        return addInCustomizationAsJSON;
    }

    @Override
    public double getPrice() {
        // TODO:
        if (lineTheCup != null && powder != null) {
            return lineTheCup.getPrice() + powder.getPrice();
        } else if (lineTheCup != null && powder == null) {
            return lineTheCup.getPrice();
        } else if (lineTheCup == null && powder != null) {
            return powder.getPrice();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isMergeable(Customization customizationToBeAdded) {
        boolean mergeable = false;

        if (customizationToBeAdded instanceof AddInCustomization) {
            Log.i("AddInCustomization", "isMergeable(Customization) customizationToBeAdded instanceof AddInCustomization");
            AddInCustomization toBeAdded = (AddInCustomization) customizationToBeAdded;
            // TODO: only CHECKING for mergeability... NOT merging in this method.
            if ( (lineTheCup == null && toBeAdded.getLineTheCup() == null) ||
                    (lineTheCup == null && toBeAdded.getLineTheCup() != null) ||
                    (lineTheCup != null && toBeAdded.getLineTheCup() == null) ) {
                Log.i("AddInCustomization", "isMergeable(Customization)\n" +
                        "(lineTheCup == null && toBeAdded.getLineTheCup() == null) ||\n" +
                        "(lineTheCup == null && toBeAdded.getLineTheCup() != null) ||\n" +
                        "(lineTheCup != null && toBeAdded.getLineTheCup() == null)");
                mergeable = true;
            } else if (lineTheCup != null && toBeAdded.getLineTheCup() != null) {
                Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup != null && toBeAdded.getLineTheCup() != null");
                if (lineTheCup != toBeAdded.getLineTheCup()) {
                    Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup != toBeAdded.getLineTheCup()");
                    Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup: " + lineTheCup.toString() + " | toBeAdded.getLineTheCup(): " + toBeAdded.getLineTheCup().toString());
                    mergeable =  true;
                } else {
                    Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup == toBeAdded.getLineTheCup()");
                    Log.i("AddInCustomization", "isMergeable(Customization) lineTheCup: " + lineTheCup.toString() + " | toBeAdded.getLineTheCup(): " + toBeAdded.getLineTheCup().toString());
                }
            } else {
                Log.i("AddInCustomization", "isMergeable(Customization)\n" +
                        "(lineTheCup == null && toBeAdded.getLineTheCup() == null) ||\n" +
                        "(lineTheCup == null && toBeAdded.getLineTheCup() != null) ||\n" +
                        "(lineTheCup != null && toBeAdded.getLineTheCup() == null) ||\n" +
                        "(lineTheCup != null && toBeAdded.getLineTheCup() != null)");
            }
        } else {
            Log.i("AddInCustomization", "isMergeable(Customization) customizationToBeAdded is NOT instanceof AddInCustomization");
        }

        return mergeable;
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