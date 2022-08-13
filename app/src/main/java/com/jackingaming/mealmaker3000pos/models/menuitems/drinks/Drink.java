package com.jackingaming.mealmaker3000pos.models.menuitems.drinks;

import android.util.Log;
import android.widget.Toast;

import com.jackingaming.mealmaker3000pos.models.Menu;
import com.jackingaming.mealmaker3000pos.models.menuitems.MenuItem;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.AddInCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.CupOptionCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.Customization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.EspressoShotCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.FlavorCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.MilkCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.SweetenerCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.TeaCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.ToppingCustomization;
import com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations.UnknownCustomization;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Drink extends MenuItem {
    public static final String JSON_ADD_IN_CUSTOMIZATIONS = "add in customizations";
    public static final String JSON_CUP_OPTION_CUSTOMIZATIONS = "cup option customizations";
    public static final String JSON_ESPRESSO_SHOT_CUSTOMIZATIONS = "espresso shot customizations";
    public static final String JSON_FLAVOR_CUSTOMIZATIONS = "flavor customizations";
    public static final String JSON_MILK_CUSTOMIZATIONS = "milk customizations";
    public static final String JSON_SWEETENER_CUSTOMIZATIONS = "sweetener customizations";
    public static final String JSON_TEA_CUSTOMIZATIONS = "tea customizations";
    public static final String JSON_TOPPING_CUSTOMIZATIONS = "topping customizations";
    public static final String JSON_UNKNOWN_CUSTOMIZATIONS = "unknown customizations";


    // size, hot/cold (hot/iced),
    protected HashMap<String, List<Customization>> customizations;

    public Drink(String name, String description, double price, boolean handedOff) {
        super(name, description, price, handedOff);
        initCustomizations();
    }

    private void initCustomizations() {
        customizations = new HashMap<>();

        customizations.put(AddInCustomization.NAME, new ArrayList<>());
        customizations.put(CupOptionCustomization.NAME, new ArrayList<>());
        customizations.put(EspressoShotCustomization.NAME, new ArrayList<>());
        customizations.put(FlavorCustomization.NAME, new ArrayList<>());
        customizations.put(MilkCustomization.NAME, new ArrayList<>());
        customizations.put(SweetenerCustomization.NAME, new ArrayList<>());
        customizations.put(TeaCustomization.NAME, new ArrayList<>());
        customizations.put(ToppingCustomization.NAME, new ArrayList<>());
        customizations.put(UnknownCustomization.NAME, new ArrayList<>());
    }

    public Drink(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);

        customizations = new HashMap<>();

        List<Customization> addInCustomizations = new ArrayList<>();
        JSONArray addInCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_ADD_IN_CUSTOMIZATIONS);
        for (int i = 0; i < addInCustomizationsAsJSONArray.length(); i++) {
            JSONObject addInCustomizationAsJSON = (JSONObject) addInCustomizationsAsJSONArray.get(i);
            AddInCustomization addInCustomization = (AddInCustomization) Menu.parseToCustomization(addInCustomizationAsJSON);
            addInCustomizations.add(addInCustomization);
        }
        customizations.put(AddInCustomization.NAME, addInCustomizations);

        List<Customization> cupOptionCustomizations = new ArrayList<>();
        JSONArray cupOptionCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_CUP_OPTION_CUSTOMIZATIONS);
        for (int i = 0; i < cupOptionCustomizationsAsJSONArray.length(); i++) {
            JSONObject cupOptionCustomizationAsJSON = (JSONObject) cupOptionCustomizationsAsJSONArray.get(i);
            CupOptionCustomization cupOptionCustomization = (CupOptionCustomization) Menu.parseToCustomization(cupOptionCustomizationAsJSON);
            cupOptionCustomizations.add(cupOptionCustomization);
        }
        customizations.put(CupOptionCustomization.NAME, cupOptionCustomizations);

        List<Customization> espressoShotCustomizations = new ArrayList<>();
        JSONArray espressoShotCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_ESPRESSO_SHOT_CUSTOMIZATIONS);
        for (int i = 0; i < espressoShotCustomizationsAsJSONArray.length(); i++) {
            JSONObject espressoShotCustomizationAsJSON = (JSONObject) espressoShotCustomizationsAsJSONArray.get(i);
            EspressoShotCustomization espressoShotCustomization = (EspressoShotCustomization) Menu.parseToCustomization(espressoShotCustomizationAsJSON);
            espressoShotCustomizations.add(espressoShotCustomization);
        }
        customizations.put(EspressoShotCustomization.NAME, espressoShotCustomizations);

        List<Customization> flavorCustomizations = new ArrayList<>();
        JSONArray flavorCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_FLAVOR_CUSTOMIZATIONS);
        for (int i = 0; i < flavorCustomizationsAsJSONArray.length(); i++) {
            JSONObject flavorCustomizationAsJSON = (JSONObject) flavorCustomizationsAsJSONArray.get(i);
            FlavorCustomization flavorCustomization = (FlavorCustomization) Menu.parseToCustomization(flavorCustomizationAsJSON);
            flavorCustomizations.add(flavorCustomization);
        }
        customizations.put(FlavorCustomization.NAME, flavorCustomizations);

        List<Customization> milkCustomizations = new ArrayList<>();
        JSONArray milkCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_MILK_CUSTOMIZATIONS);
        for (int i = 0; i < milkCustomizationsAsJSONArray.length(); i++) {
            JSONObject milkCustomizationAsJSON = (JSONObject) milkCustomizationsAsJSONArray.get(i);
            MilkCustomization milkCustomization = (MilkCustomization) Menu.parseToCustomization(milkCustomizationAsJSON);
            milkCustomizations.add(milkCustomization);
        }
        customizations.put(MilkCustomization.NAME, milkCustomizations);

        List<Customization> sweetenerCustomizations = new ArrayList<>();
        JSONArray sweetenerCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_SWEETENER_CUSTOMIZATIONS);
        for (int i = 0; i < sweetenerCustomizationsAsJSONArray.length(); i++) {
            JSONObject sweetenerCustomizationAsJSON = (JSONObject) sweetenerCustomizationsAsJSONArray.get(i);
            SweetenerCustomization sweetenerCustomization = (SweetenerCustomization) Menu.parseToCustomization(sweetenerCustomizationAsJSON);
            sweetenerCustomizations.add(sweetenerCustomization);
        }
        customizations.put(SweetenerCustomization.NAME, sweetenerCustomizations);

        List<Customization> teaCustomizations = new ArrayList<>();
        JSONArray teaCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_TEA_CUSTOMIZATIONS);
        for (int i = 0; i < teaCustomizationsAsJSONArray.length(); i++) {
            JSONObject teaCustomizationAsJSON = (JSONObject) teaCustomizationsAsJSONArray.get(i);
            TeaCustomization teaCustomization = (TeaCustomization) Menu.parseToCustomization(teaCustomizationAsJSON);
            teaCustomizations.add(teaCustomization);
        }
        customizations.put(TeaCustomization.NAME, teaCustomizations);

        List<Customization> toppingCustomizations = new ArrayList<>();
        JSONArray toppingCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_TOPPING_CUSTOMIZATIONS);
        for (int i = 0; i < toppingCustomizationsAsJSONArray.length(); i++) {
            JSONObject toppingCustomizationAsJSON = (JSONObject) toppingCustomizationsAsJSONArray.get(i);
            ToppingCustomization toppingCustomization = (ToppingCustomization) Menu.parseToCustomization(toppingCustomizationAsJSON);
            toppingCustomizations.add(toppingCustomization);
        }
        customizations.put(ToppingCustomization.NAME, toppingCustomizations);

        List<Customization> unknownCustomizations = new ArrayList<>();
        JSONArray unknownCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_UNKNOWN_CUSTOMIZATIONS);
        for (int i = 0; i < unknownCustomizationsAsJSONArray.length(); i++) {
            JSONObject unknownCustomizationAsJSON = (JSONObject) unknownCustomizationsAsJSONArray.get(i);
            UnknownCustomization unknownCustomization = (UnknownCustomization) Menu.parseToCustomization(unknownCustomizationAsJSON);
            unknownCustomizations.add(unknownCustomization);
        }
        customizations.put(UnknownCustomization.NAME, unknownCustomizations);
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject menuItemAsJSON = super.toJSON();

        List<Customization> addInCustomizations = customizations.get(AddInCustomization.NAME);
        JSONArray addInCustomizationsAsJSONArray = new JSONArray();
        for (Customization addInCustomization : addInCustomizations) {
            JSONObject addInCustomizationAsJSON = addInCustomization.toJSON();
            addInCustomizationsAsJSONArray.put(addInCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_ADD_IN_CUSTOMIZATIONS, addInCustomizationsAsJSONArray);

        List<Customization> cupOptionCustomizations = customizations.get(CupOptionCustomization.NAME);
        JSONArray cupOptionCustomizationsAsJSONArray = new JSONArray();
        for (Customization cupOptionCustomization : cupOptionCustomizations) {
            JSONObject cupOptionCustomizationAsJSON = cupOptionCustomization.toJSON();
            cupOptionCustomizationsAsJSONArray.put(cupOptionCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_CUP_OPTION_CUSTOMIZATIONS, cupOptionCustomizationsAsJSONArray);

        List<Customization> espressoShotCustomizations = customizations.get(EspressoShotCustomization.NAME);
        JSONArray espressoShotCustomizationsAsJSONArray = new JSONArray();
        for (Customization espressoShotCustomization : espressoShotCustomizations) {
            JSONObject espressoShotCustomizationAsJSON = espressoShotCustomization.toJSON();
            espressoShotCustomizationsAsJSONArray.put(espressoShotCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_ESPRESSO_SHOT_CUSTOMIZATIONS, espressoShotCustomizationsAsJSONArray);

        List<Customization> flavorCustomizations = customizations.get(FlavorCustomization.NAME);
        JSONArray flavorCustomizationsAsJSONArray = new JSONArray();
        for (Customization flavorCustomization : flavorCustomizations) {
            JSONObject flavorCustomizationAsJSON = flavorCustomization.toJSON();
            flavorCustomizationsAsJSONArray.put(flavorCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_FLAVOR_CUSTOMIZATIONS, flavorCustomizationsAsJSONArray);

        List<Customization> milkCustomizations = customizations.get(MilkCustomization.NAME);
        JSONArray milkCustomizationsAsJSONArray = new JSONArray();
        for (Customization milkCustomization : milkCustomizations) {
            JSONObject milkCustomizationAsJSON = milkCustomization.toJSON();
            milkCustomizationsAsJSONArray.put(milkCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_MILK_CUSTOMIZATIONS, milkCustomizationsAsJSONArray);

        List<Customization> sweetenerCustomizations = customizations.get(SweetenerCustomization.NAME);
        JSONArray sweetenerCustomizationsAsJSONArray = new JSONArray();
        for (Customization sweetenerCustomization : sweetenerCustomizations) {
            JSONObject sweetenerCustomizationAsJSON = sweetenerCustomization.toJSON();
            sweetenerCustomizationsAsJSONArray.put(sweetenerCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_SWEETENER_CUSTOMIZATIONS, sweetenerCustomizationsAsJSONArray);

        List<Customization> teaCustomizations = customizations.get(TeaCustomization.NAME);
        JSONArray teaCustomizationsAsJSONArray = new JSONArray();
        for (Customization teaCustomization : teaCustomizations) {
            JSONObject teaCustomizationAsJSON = teaCustomization.toJSON();
            teaCustomizationsAsJSONArray.put(teaCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_TEA_CUSTOMIZATIONS, teaCustomizationsAsJSONArray);

        List<Customization> toppingCustomizations = customizations.get(ToppingCustomization.NAME);
        JSONArray toppingCustomizationsAsJSONArray = new JSONArray();
        for (Customization toppingCustomization : toppingCustomizations) {
            JSONObject toppingCustomizationAsJSON = toppingCustomization.toJSON();
            toppingCustomizationsAsJSONArray.put(toppingCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_TOPPING_CUSTOMIZATIONS, toppingCustomizationsAsJSONArray);

        List<Customization> unknownCustomizations = customizations.get(UnknownCustomization.NAME);
        JSONArray unknownCustomizationsAsJSONArray = new JSONArray();
        for (Customization unknownCustomization : unknownCustomizations) {
            JSONObject unknownCustomizationAsJSON = unknownCustomization.toJSON();
            unknownCustomizationsAsJSONArray.put(unknownCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_UNKNOWN_CUSTOMIZATIONS, unknownCustomizationsAsJSONArray);

        return menuItemAsJSON;
    }

    private boolean hasDuplicateLineTheCup(AddInCustomization customizationToBeAdded) {
        for (Customization customization : customizations.get(AddInCustomization.NAME)) {
            AddInCustomization customizationAlreadyInDrink = (AddInCustomization) customization;
            if (customizationAlreadyInDrink.getLineTheCup() == customizationToBeAdded.getLineTheCup()) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDuplicatePowder(AddInCustomization customizationToBeAdded) {
        for (Customization customization : customizations.get(AddInCustomization.NAME)) {
            AddInCustomization customizationAlreadyInDrink = (AddInCustomization) customization;
            if (customizationAlreadyInDrink.getPowder() == customizationToBeAdded.getPowder()) {
                return true;
            }
        }
        return false;
    }

    private void addAddInCustomizationToCustomization(AddInCustomization customizationToBeAdded) {
        if (customizationToBeAdded.getLineTheCup() != null) {
            Log.i("Drink", "customizationToBeAdded.getLineTheCup() != null");
            if (!hasDuplicateLineTheCup(customizationToBeAdded)) {
                Log.i("Drink", "NOT hasDuplicateLineTheCup() | adding to customizations.");
                customizations.get(AddInCustomization.NAME).add(customizationToBeAdded);
            } else {
                Log.i("Drink", "hasDuplicateLineTheCup() | NOT adding to customizations.");
            }
        } else if (customizationToBeAdded.getPowder() != null) {
            Log.i("Drink", "customizationToBeAdded.getPowder() != null");
            if (!hasDuplicatePowder(customizationToBeAdded)) {
                Log.i("Drink", "NOT hasDuplicatePowder() | adding to customizations.");
                customizations.get(AddInCustomization.NAME).add(customizationToBeAdded);
            } else {
                Log.i("Drink", "hasDuplicatePowder() | NOT adding to customizations.");
            }
        } else {
            Log.i("Drink", "customizationToBeAdded.getLineTheCup() == null && customizationToBeAdded.getPowder() == null");
        }
    }

    public void addToCustomizations(Customization customization) {
        Log.i("Drink", "addToCustomizations(Customization)");
        String nameCustomizationToBeAdded = customization.getName();
        Log.i("Drink", "nameCustomizationToBeAdded: " + nameCustomizationToBeAdded);
        if (AddInCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            AddInCustomization customizationToBeAdded = (AddInCustomization) customization;
            addAddInCustomizationToCustomization(customizationToBeAdded);
        }
        // TODO: Check if already contains
        else if (CupOptionCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof CupOptionCustomization) {
                customizations.get(CupOptionCustomization.NAME).add(customization);
            }
        } else if (EspressoShotCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof EspressoShotCustomization) {
                customizations.get(EspressoShotCustomization.NAME).add(customization);
            }
        } else if (FlavorCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof FlavorCustomization) {
                customizations.get(FlavorCustomization.NAME).add(customization);
            }
        } else if (MilkCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof MilkCustomization) {
                customizations.get(MilkCustomization.NAME).add(customization);
            }
        } else if (SweetenerCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof SweetenerCustomization) {
                customizations.get(SweetenerCustomization.NAME).add(customization);
            }
        } else if (TeaCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof TeaCustomization) {
                customizations.get(TeaCustomization.NAME).add(customization);
            }
        } else if (ToppingCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof ToppingCustomization) {
                customizations.get(ToppingCustomization.NAME).add(customization);
            }
        } else {
            if (customization instanceof UnknownCustomization) {
                customizations.get(UnknownCustomization.NAME).add(customization);
            } else {
                Log.d("Drink", "addToCustomizations(Customization) else-clause customization is NOT instanceof UnknownCustomization");
            }
        }
    }

    public boolean isCustomizationEmpty() {
        return customizations.get(AddInCustomization.NAME).isEmpty() &&
                customizations.get(CupOptionCustomization.NAME).isEmpty() &&
                customizations.get(EspressoShotCustomization.NAME).isEmpty() &&
                customizations.get(FlavorCustomization.NAME).isEmpty() &&
                customizations.get(MilkCustomization.NAME).isEmpty() &&
                customizations.get(SweetenerCustomization.NAME).isEmpty() &&
                customizations.get(TeaCustomization.NAME).isEmpty() &&
                customizations.get(ToppingCustomization.NAME).isEmpty() &&
                customizations.get(UnknownCustomization.NAME).isEmpty();
    }

    public void removeCustomization(Customization customizationToBeRemoved) {
        if (customizationToBeRemoved instanceof AddInCustomization) {
            customizations.get(AddInCustomization.NAME).remove(customizationToBeRemoved);
        } else if (customizationToBeRemoved instanceof CupOptionCustomization) {
            customizations.get(CupOptionCustomization.NAME).remove(customizationToBeRemoved);
        } else if (customizationToBeRemoved instanceof EspressoShotCustomization) {
            customizations.get(EspressoShotCustomization.NAME).remove(customizationToBeRemoved);
        } else if (customizationToBeRemoved instanceof FlavorCustomization) {
            customizations.get(FlavorCustomization.NAME).remove(customizationToBeRemoved);
        } else if (customizationToBeRemoved instanceof MilkCustomization) {
            customizations.get(MilkCustomization.NAME).remove(customizationToBeRemoved);
        } else if (customizationToBeRemoved instanceof SweetenerCustomization) {
            customizations.get(SweetenerCustomization.NAME).remove(customizationToBeRemoved);
        } else if (customizationToBeRemoved instanceof TeaCustomization) {
            customizations.get(TeaCustomization.NAME).remove(customizationToBeRemoved);
        } else if (customizationToBeRemoved instanceof ToppingCustomization) {
            customizations.get(ToppingCustomization.NAME).remove(customizationToBeRemoved);
        } else {
            customizations.get(UnknownCustomization.NAME).remove(customizationToBeRemoved);
        }
    }

    public List<Customization> getCustomizations() {
        List<Customization> customizationsAllInOne = new ArrayList<>();

        List<Customization> addInCustomizations = customizations.get(AddInCustomization.NAME);
        for (Customization addInCustomization : addInCustomizations) {
            customizationsAllInOne.add(addInCustomization);
        }

        List<Customization> cupOptionCustomizations = customizations.get(CupOptionCustomization.NAME);
        for (Customization cupOptionCustomization : cupOptionCustomizations) {
            customizationsAllInOne.add(cupOptionCustomization);
        }

        List<Customization> espressoShotCustomizations = customizations.get(EspressoShotCustomization.NAME);
        for (Customization espressoShotCustomization : espressoShotCustomizations) {
            customizationsAllInOne.add(espressoShotCustomization);
        }

        List<Customization> flavorCustomizations = customizations.get(FlavorCustomization.NAME);
        for (Customization flavorCustomization : flavorCustomizations) {
            customizationsAllInOne.add(flavorCustomization);
        }

        List<Customization> milkCustomizations = customizations.get(MilkCustomization.NAME);
        for (Customization milkCustomization : milkCustomizations) {
            customizationsAllInOne.add(milkCustomization);
        }

        List<Customization> sweetenerCustomizations = customizations.get(SweetenerCustomization.NAME);
        for (Customization sweetenerCustomization : sweetenerCustomizations) {
            customizationsAllInOne.add(sweetenerCustomization);
        }

        List<Customization> teaCustomizations = customizations.get(TeaCustomization.NAME);
        for (Customization teaCustomization : teaCustomizations) {
            customizationsAllInOne.add(teaCustomization);
        }

        List<Customization> toppingCustomizations = customizations.get(ToppingCustomization.NAME);
        for (Customization toppingCustomization : toppingCustomizations) {
            customizationsAllInOne.add(toppingCustomization);
        }

        List<Customization> unknownCustomizations = customizations.get(UnknownCustomization.NAME);
        for (Customization unknownCustomization : unknownCustomizations) {
            customizationsAllInOne.add(unknownCustomization);
        }

        return customizationsAllInOne;
    }

    @Override
    public double getPrice() {
        double priceOfCustomizations = 0;

        List<Customization> addInCustomizations = customizations.get(AddInCustomization.NAME);
        for (Customization addInCustomization : addInCustomizations) {
            priceOfCustomizations += addInCustomization.getPrice();
        }

        List<Customization> cupOptionCustomizations = customizations.get(CupOptionCustomization.NAME);
        for (Customization cupOptionCustomization : cupOptionCustomizations) {
            priceOfCustomizations += cupOptionCustomization.getPrice();
        }

        List<Customization> espressoShotCustomizations = customizations.get(EspressoShotCustomization.NAME);
        for (Customization espressoShotCustomization : espressoShotCustomizations) {
            priceOfCustomizations += espressoShotCustomization.getPrice();
        }

        List<Customization> flavorCustomizations = customizations.get(FlavorCustomization.NAME);
        for (Customization flavorCustomization : flavorCustomizations) {
            priceOfCustomizations += flavorCustomization.getPrice();
        }

        List<Customization> milkCustomizations = customizations.get(MilkCustomization.NAME);
        for (Customization milkCustomization : milkCustomizations) {
            priceOfCustomizations += milkCustomization.getPrice();
        }

        List<Customization> sweetenerCustomizations = customizations.get(SweetenerCustomization.NAME);
        for (Customization sweetenerCustomization : sweetenerCustomizations) {
            priceOfCustomizations += sweetenerCustomization.getPrice();
        }

        List<Customization> teaCustomizations = customizations.get(TeaCustomization.NAME);
        for (Customization teaCustomization : teaCustomizations) {
            priceOfCustomizations += teaCustomization.getPrice();
        }

        List<Customization> toppingCustomizations = customizations.get(ToppingCustomization.NAME);
        for (Customization toppingCustomization : toppingCustomizations) {
            priceOfCustomizations += toppingCustomization.getPrice();
        }

        List<Customization> unknownCustomizations = customizations.get(UnknownCustomization.NAME);
        for (Customization unknownCustomization : unknownCustomizations) {
            priceOfCustomizations += unknownCustomization.getPrice();
        }

        return super.getPrice() + priceOfCustomizations;
    }
}