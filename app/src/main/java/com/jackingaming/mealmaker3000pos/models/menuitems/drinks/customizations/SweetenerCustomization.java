package com.jackingaming.mealmaker3000pos.models.menuitems.drinks.customizations;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SweetenerCustomization extends Customization {
    public static final String NAME = "SweetenerCustomization";
    public static final String JSON_LIQUID = "liquid";
    public static final String JSON_PACKET = "packet";

    public enum Liquid { CLASSIC, HONEY_BLEND, LIQUID_CANE; }
    public enum Packet { HONEY, SPLENDA, STEVIA_IN_THE_RAW, SUGAR, SUGAR_IN_THE_RAW; }

    private Liquid liquid;
    private Packet packet;

    private SweetenerCustomization(Builder builder) {
        super(NAME);
        this.liquid = builder.liquid;
        this.packet = builder.packet;
    }

    public SweetenerCustomization(JSONObject sweetenerCustomizationAsJSON) throws JSONException {
        super(sweetenerCustomizationAsJSON);

        if (sweetenerCustomizationAsJSON.has(JSON_LIQUID)) {
            String liquidAsString = sweetenerCustomizationAsJSON.get(JSON_LIQUID).toString();
            for (int i = 0; i < Liquid.values().length; i++) {
                if (Liquid.values()[i].toString().equals(liquidAsString)) {
                    Log.d("SweetenerCustomization", "SweetenerCustomization(JSONObject) Liquid." + Liquid.values()[i].toString());
                    liquid = Liquid.values()[i];
                    break;
                }
            }
        } else {
            Log.d("SweetenerCustomization", "SweetenerCustomization(JSONObject) sweetenerCustomizationAsJSON does NOT has(JSON_LIQUID)");
        }

        if (sweetenerCustomizationAsJSON.has(JSON_PACKET)) {
            String packetAsString = sweetenerCustomizationAsJSON.get(JSON_PACKET).toString();
            for (int i = 0; i < Packet.values().length; i++) {
                if (Packet.values()[i].toString().equals(packetAsString)) {
                    Log.d("SweetenerCustomization", "SweetenerCustomization(JSONObject) Packet." + Packet.values()[i].toString());
                    packet = Packet.values()[i];
                    break;
                }
            }
        } else {
            Log.d("SweetenerCustomization", "SweetenerCustomization(JSONObject) sweetenerCustomizationAsJSON does NOT has(JSON_PACKET)");
        }
    }

    @Override
    public JSONObject toJSON()
            throws JSONException {
        JSONObject sweetenerCustomizationAsJSON = super.toJSON();
        sweetenerCustomizationAsJSON.put(JSON_LIQUID, liquid);
        sweetenerCustomizationAsJSON.put(JSON_PACKET, packet);
        return sweetenerCustomizationAsJSON;
    }

    @Override
    public double getPrice() {
        // TODO:
        return 0;
    }

    public Liquid getLiquid() {
        return liquid;
    }

    public Packet getPacket() {
        return packet;
    }

    public static class Builder {
        private Liquid liquid;
        private Packet packet;

        public Builder() {
        }

        public Builder liquid(Liquid liquid) {
            this.liquid = liquid;
            return this;
        }

        public Builder packet(Packet packet) {
            this.packet = packet;
            return this;
        }

        public SweetenerCustomization build() {
            SweetenerCustomization sweetenerCustomization = new SweetenerCustomization(this);
            validate(sweetenerCustomization);
            return sweetenerCustomization;
        }

        private void validate(SweetenerCustomization sweetenerCustomization) {
            // TODO: validations to check that SweetenerCustomization object
            //  does not break any assumption of system
        }
    }
}