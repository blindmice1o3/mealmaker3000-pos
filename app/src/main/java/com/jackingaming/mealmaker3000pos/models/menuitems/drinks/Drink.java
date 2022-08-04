package com.jackingaming.mealmaker3000pos.models.menuitems.drinks;

import android.util.Log;

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
import java.util.Map;

public abstract class Drink extends MenuItem {
    //    public static final String JSON_CUSTOMIZATIONS = "customizations";
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
//    protected List<Customization> customizations;
    protected List<AddInCustomization> addInCustomizations;
    protected List<CupOptionCustomization> cupOptionCustomizations;
    protected List<EspressoShotCustomization> espressoShotCustomizations;
    protected List<FlavorCustomization> flavorCustomizations;
    protected List<MilkCustomization> milkCustomizations;
    protected List<SweetenerCustomization> sweetenerCustomizations;
    protected List<TeaCustomization> teaCustomizations;
    protected List<ToppingCustomization> toppingCustomizations;
    protected List<UnknownCustomization> unknownCustomizations;

    public Drink(String name, String description, double price) {
        super(name, description, price);
//        customizations = new ArrayList<Customization>();
        addInCustomizations = new ArrayList<>();
        cupOptionCustomizations = new ArrayList<>();
        espressoShotCustomizations = new ArrayList<>();
        flavorCustomizations = new ArrayList<>();
        milkCustomizations = new ArrayList<>();
        sweetenerCustomizations = new ArrayList<>();
        teaCustomizations = new ArrayList<>();
        toppingCustomizations = new ArrayList<>();
        unknownCustomizations = new ArrayList<>();
    }

    public Drink(JSONObject menuItemAsJSON)
            throws JSONException {
        super(menuItemAsJSON);

        addInCustomizations = new ArrayList<>();
        JSONArray addInCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_ADD_IN_CUSTOMIZATIONS);
        for (int i = 0; i < addInCustomizationsAsJSONArray.length(); i++) {
            JSONObject addInCustomizationAsJSON = (JSONObject) addInCustomizationsAsJSONArray.get(i);
            AddInCustomization addInCustomization = (AddInCustomization) Menu.parseToCustomization(addInCustomizationAsJSON);
            addInCustomizations.add(addInCustomization);
        }

        cupOptionCustomizations = new ArrayList<>();
        JSONArray cupOptionCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_CUP_OPTION_CUSTOMIZATIONS);
        for (int i = 0; i < cupOptionCustomizationsAsJSONArray.length(); i++) {
            JSONObject cupOptionCustomizationAsJSON = (JSONObject) cupOptionCustomizationsAsJSONArray.get(i);
            CupOptionCustomization cupOptionCustomization = (CupOptionCustomization) Menu.parseToCustomization(cupOptionCustomizationAsJSON);
            cupOptionCustomizations.add(cupOptionCustomization);
        }

        espressoShotCustomizations = new ArrayList<>();
        JSONArray espressoShotCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_ESPRESSO_SHOT_CUSTOMIZATIONS);
        for (int i = 0; i < espressoShotCustomizationsAsJSONArray.length(); i++) {
            JSONObject espressoShotCustomizationAsJSON = (JSONObject) espressoShotCustomizationsAsJSONArray.get(i);
            EspressoShotCustomization espressoShotCustomization = (EspressoShotCustomization) Menu.parseToCustomization(espressoShotCustomizationAsJSON);
            espressoShotCustomizations.add(espressoShotCustomization);
        }

        flavorCustomizations = new ArrayList<>();
        JSONArray flavorCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_FLAVOR_CUSTOMIZATIONS);
        for (int i = 0; i < flavorCustomizationsAsJSONArray.length(); i++) {
            JSONObject flavorCustomizationAsJSON = (JSONObject) flavorCustomizationsAsJSONArray.get(i);
            FlavorCustomization flavorCustomization = (FlavorCustomization) Menu.parseToCustomization(flavorCustomizationAsJSON);
            flavorCustomizations.add(flavorCustomization);
        }

        milkCustomizations = new ArrayList<>();
        JSONArray milkCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_MILK_CUSTOMIZATIONS);
        for (int i = 0; i < milkCustomizationsAsJSONArray.length(); i++) {
            JSONObject milkCustomizationAsJSON = (JSONObject) milkCustomizationsAsJSONArray.get(i);
            MilkCustomization milkCustomization = (MilkCustomization) Menu.parseToCustomization(milkCustomizationAsJSON);
            milkCustomizations.add(milkCustomization);
        }

        sweetenerCustomizations = new ArrayList<>();
        JSONArray sweetenerCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_SWEETENER_CUSTOMIZATIONS);
        for (int i = 0; i < sweetenerCustomizationsAsJSONArray.length(); i++) {
            JSONObject sweetenerCustomizationAsJSON = (JSONObject) sweetenerCustomizationsAsJSONArray.get(i);
            SweetenerCustomization sweetenerCustomization = (SweetenerCustomization) Menu.parseToCustomization(sweetenerCustomizationAsJSON);
            sweetenerCustomizations.add(sweetenerCustomization);
        }

        teaCustomizations = new ArrayList<>();
        JSONArray teaCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_TEA_CUSTOMIZATIONS);
        for (int i = 0; i < teaCustomizationsAsJSONArray.length(); i++) {
            JSONObject teaCustomizationAsJSON = (JSONObject) teaCustomizationsAsJSONArray.get(i);
            TeaCustomization teaCustomization = (TeaCustomization) Menu.parseToCustomization(teaCustomizationAsJSON);
            teaCustomizations.add(teaCustomization);
        }

        toppingCustomizations = new ArrayList<>();
        JSONArray toppingCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_TOPPING_CUSTOMIZATIONS);
        for (int i = 0; i < toppingCustomizationsAsJSONArray.length(); i++) {
            JSONObject toppingCustomizationAsJSON = (JSONObject) toppingCustomizationsAsJSONArray.get(i);
            ToppingCustomization toppingCustomization = (ToppingCustomization) Menu.parseToCustomization(toppingCustomizationAsJSON);
            toppingCustomizations.add(toppingCustomization);
        }

        unknownCustomizations = new ArrayList<>();
        JSONArray unknownCustomizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_UNKNOWN_CUSTOMIZATIONS);
        for (int i = 0; i < unknownCustomizationsAsJSONArray.length(); i++) {
            JSONObject unknownCustomizationAsJSON = (JSONObject) unknownCustomizationsAsJSONArray.get(i);
            UnknownCustomization unknownCustomization = (UnknownCustomization) Menu.parseToCustomization(unknownCustomizationAsJSON);
            unknownCustomizations.add(unknownCustomization);
        }

//        customizations = new ArrayList<Customization>();
//        JSONArray customizationsAsJSONArray = (JSONArray) menuItemAsJSON.get(JSON_CUSTOMIZATIONS);
//        for (int i = 0; i < customizationsAsJSONArray.length(); i++) {
//            JSONObject customizationAsJSON = (JSONObject) customizationsAsJSONArray.get(i);
//            Customization customization = Menu.parseToCustomization(customizationAsJSON);
//            customizations.add(customization);
//        }
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject menuItemAsJSON = super.toJSON();

        JSONArray addInCustomizationsAsJSONArray = new JSONArray();
        for (AddInCustomization addInCustomization : addInCustomizations) {
            JSONObject addInCustomizationAsJSON = addInCustomization.toJSON();
            addInCustomizationsAsJSONArray.put(addInCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_ADD_IN_CUSTOMIZATIONS, addInCustomizationsAsJSONArray);

        JSONArray cupOptionCustomizationsAsJSONArray = new JSONArray();
        for (CupOptionCustomization cupOptionCustomization : cupOptionCustomizations) {
            JSONObject cupOptionCustomizationAsJSON = cupOptionCustomization.toJSON();
            cupOptionCustomizationsAsJSONArray.put(cupOptionCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_CUP_OPTION_CUSTOMIZATIONS, cupOptionCustomizationsAsJSONArray);

        JSONArray espressoShotCustomizationsAsJSONArray = new JSONArray();
        for (EspressoShotCustomization espressoShotCustomization : espressoShotCustomizations) {
            JSONObject espressoShotCustomizationAsJSON = espressoShotCustomization.toJSON();
            espressoShotCustomizationsAsJSONArray.put(espressoShotCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_ESPRESSO_SHOT_CUSTOMIZATIONS, espressoShotCustomizationsAsJSONArray);

        JSONArray flavorCustomizationsAsJSONArray = new JSONArray();
        for (FlavorCustomization flavorCustomization : flavorCustomizations) {
            JSONObject flavorCustomizationAsJSON = flavorCustomization.toJSON();
            flavorCustomizationsAsJSONArray.put(flavorCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_FLAVOR_CUSTOMIZATIONS, flavorCustomizationsAsJSONArray);

        JSONArray milkCustomizationsAsJSONArray = new JSONArray();
        for (MilkCustomization milkCustomization : milkCustomizations) {
            JSONObject milkCustomizationAsJSON = milkCustomization.toJSON();
            milkCustomizationsAsJSONArray.put(milkCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_MILK_CUSTOMIZATIONS, milkCustomizationsAsJSONArray);

        JSONArray sweetenerCustomizationsAsJSONArray = new JSONArray();
        for (SweetenerCustomization sweetenerCustomization : sweetenerCustomizations) {
            JSONObject sweetenerCustomizationAsJSON = sweetenerCustomization.toJSON();
            sweetenerCustomizationsAsJSONArray.put(sweetenerCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_SWEETENER_CUSTOMIZATIONS, sweetenerCustomizationsAsJSONArray);

        JSONArray teaCustomizationsAsJSONArray = new JSONArray();
        for (TeaCustomization teaCustomization : teaCustomizations) {
            JSONObject teaCustomizationAsJSON = teaCustomization.toJSON();
            teaCustomizationsAsJSONArray.put(teaCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_TEA_CUSTOMIZATIONS, teaCustomizationsAsJSONArray);

        JSONArray toppingCustomizationsAsJSONArray = new JSONArray();
        for (ToppingCustomization toppingCustomization : toppingCustomizations) {
            JSONObject toppingCustomizationAsJSON = toppingCustomization.toJSON();
            toppingCustomizationsAsJSONArray.put(toppingCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_TOPPING_CUSTOMIZATIONS, toppingCustomizationsAsJSONArray);

        JSONArray unknownCustomizationsAsJSONArray = new JSONArray();
        for (UnknownCustomization unknownCustomization : unknownCustomizations) {
            JSONObject unknownCustomizationAsJSON = unknownCustomization.toJSON();
            unknownCustomizationsAsJSONArray.put(unknownCustomizationAsJSON);
        }
        menuItemAsJSON.put(JSON_UNKNOWN_CUSTOMIZATIONS, unknownCustomizationsAsJSONArray);

//        JSONArray customizationsAsJSONArray = new JSONArray();
//        for (Customization customization : customizations) {
//            JSONObject customizationAsJSON = customization.toJSON();
//            customizationsAsJSONArray.put(customizationAsJSON);
//        }
//        menuItemAsJSON.put(JSON_CUSTOMIZATIONS, customizationsAsJSONArray);

        return menuItemAsJSON;
    }

    public void addToCustomizations(Customization customization) {
        // TODO: Check if already contains CARAMEL (also, it may
        //  already have MOCHA... DON'T OVERWRITE IT WITHOUT MOCHA)

        String nameCustomizationToBeAdded = customization.getName();
        Log.d("Drink", nameCustomizationToBeAdded);
        if (AddInCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof AddInCustomization) {
                addInCustomizations.add((AddInCustomization) customization);
            }
        } else if (CupOptionCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof CupOptionCustomization) {
                cupOptionCustomizations.add((CupOptionCustomization) customization);
            }
        } else if (EspressoShotCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof EspressoShotCustomization) {
                espressoShotCustomizations.add((EspressoShotCustomization) customization);
            }
        } else if (FlavorCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof FlavorCustomization) {
                flavorCustomizations.add((FlavorCustomization) customization);
            }
        } else if (MilkCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof MilkCustomization) {
                milkCustomizations.add((MilkCustomization) customization);
            }
        } else if (SweetenerCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof SweetenerCustomization) {
                sweetenerCustomizations.add((SweetenerCustomization) customization);
            }
        } else if (TeaCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof TeaCustomization) {
                teaCustomizations.add((TeaCustomization) customization);
            }
        } else if (ToppingCustomization.NAME.equals(nameCustomizationToBeAdded)) {
            if (customization instanceof ToppingCustomization) {
                toppingCustomizations.add((ToppingCustomization) customization);
            }
        } else {
            if (customization instanceof UnknownCustomization) {
                unknownCustomizations.add((UnknownCustomization) customization);
            } else {
                Log.d("Drink", "addToCustomizations(Customization) else-clause customization is NOT instanceof UnknownCustomization");
            }
        }


//        for (int i = 0; i < customizations.size(); i++) {
//            Customization customizationInsideDrink = customizations.get(i);
//            String nameCustomizationInsideDrink = customizationInsideDrink.getName();
//
//            // is the customizationToBeAdded already inside inside Drink's customizations list?
//            if (nameCustomizationInsideDrink.equals(nameCustomizationToBeAdded)) {
//                Log.d("Drink", "addToCustomizations(Customization) nameCustomizationInsideDrink equals() nameCustomizationToBeAdded");
//                if (customizationInsideDrink.isMergeable(customization)) {
//                    Log.i("Drink", "addToCustomizations(Customization) customizationInsideDrink isMergeable() customization");
//                    // TODO:
//
//
//                } else {
//                    Log.i("Drink", "addToCustomizations(Customization) customizationInsideDrink NOT isMergeable() customization");
//                }
//            } else {
//                Log.i("Drink", "addToCustomizations(Customization) nameCustomizationInsideDrink NOT equals() nameCustomizationToBeAdded");
//            }
//        }
//
//        customizations.add(customization);
    }

    public boolean isCustomizationEmpty() {
        return addInCustomizations.isEmpty() &&
                cupOptionCustomizations.isEmpty() &&
                espressoShotCustomizations.isEmpty() &&
                flavorCustomizations.isEmpty() &&
                milkCustomizations.isEmpty() &&
                sweetenerCustomizations.isEmpty() &&
                teaCustomizations.isEmpty() &&
                toppingCustomizations.isEmpty() &&
                unknownCustomizations.isEmpty();
    }

    public List<AddInCustomization> getAddInCustomizations() {
        return addInCustomizations;
    }

    public List<CupOptionCustomization> getCupOptionCustomizations() {
        return cupOptionCustomizations;
    }

    public List<EspressoShotCustomization> getEspressoShotCustomizations() {
        return espressoShotCustomizations;
    }

    public List<FlavorCustomization> getFlavorCustomizations() {
        return flavorCustomizations;
    }

    public List<MilkCustomization> getMilkCustomizations() {
        return milkCustomizations;
    }

    public List<SweetenerCustomization> getSweetenerCustomizations() {
        return sweetenerCustomizations;
    }

    public List<TeaCustomization> getTeaCustomizations() {
        return teaCustomizations;
    }

    public List<ToppingCustomization> getToppingCustomizations() {
        return toppingCustomizations;
    }

    public List<UnknownCustomization> getUnknownCustomizations() {
        return unknownCustomizations;
    }

    public List<Customization> getCustomizations() {
        List<Customization> customizations = new ArrayList<>();

        for (AddInCustomization addInCustomization : addInCustomizations) {
            customizations.add(addInCustomization);
        }

        for (CupOptionCustomization cupOptionCustomization : cupOptionCustomizations) {
            customizations.add(cupOptionCustomization);
        }

        for (EspressoShotCustomization espressoShotCustomization : espressoShotCustomizations) {
            customizations.add(espressoShotCustomization);
        }

        for (FlavorCustomization flavorCustomization : flavorCustomizations) {
            customizations.add(flavorCustomization);
        }

        for (MilkCustomization milkCustomization : milkCustomizations) {
            customizations.add(milkCustomization);
        }

        for (SweetenerCustomization sweetenerCustomization : sweetenerCustomizations) {
            customizations.add(sweetenerCustomization);
        }

        for (TeaCustomization teaCustomization : teaCustomizations) {
            customizations.add(teaCustomization);
        }

        for (ToppingCustomization toppingCustomization : toppingCustomizations) {
            customizations.add(toppingCustomization);
        }

        for (UnknownCustomization unknownCustomization : unknownCustomizations) {
            customizations.add(unknownCustomization);
        }

        return customizations;
    }

    @Override
    public double getPrice() {
        double priceOfCustomizations = 0;

        for (AddInCustomization addInCustomization : addInCustomizations) {
            priceOfCustomizations += addInCustomization.getPrice();
        }

        for (CupOptionCustomization cupOptionCustomization : cupOptionCustomizations) {
            priceOfCustomizations += cupOptionCustomization.getPrice();
        }

        for (EspressoShotCustomization espressoShotCustomization : espressoShotCustomizations) {
            priceOfCustomizations += espressoShotCustomization.getPrice();
        }

        for (FlavorCustomization flavorCustomization : flavorCustomizations) {
            priceOfCustomizations += flavorCustomization.getPrice();
        }

        for (MilkCustomization milkCustomization : milkCustomizations) {
            priceOfCustomizations += milkCustomization.getPrice();
        }

        for (SweetenerCustomization sweetenerCustomization : sweetenerCustomizations) {
            priceOfCustomizations += sweetenerCustomization.getPrice();
        }

        for (TeaCustomization teaCustomization : teaCustomizations) {
            priceOfCustomizations += teaCustomization.getPrice();
        }

        for (ToppingCustomization toppingCustomization : toppingCustomizations) {
            priceOfCustomizations += toppingCustomization.getPrice();
        }

        for (UnknownCustomization unknownCustomization : unknownCustomizations) {
            priceOfCustomizations += unknownCustomization.getPrice();
        }

//        for (Customization customization : customizations) {
//            priceOfCustomizations += customization.getPrice();
//        }

        return super.getPrice() + priceOfCustomizations;
    }
}