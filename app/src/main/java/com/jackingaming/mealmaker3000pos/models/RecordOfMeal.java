package com.jackingaming.mealmaker3000pos.models;

import org.json.JSONException;
import org.json.JSONObject;

public class RecordOfMeal {
    public static final String JSON_KEY = "key";
    public static final String JSON_VALUE = "value";
    public static final String JSON_TIMESTAMP = "timestamp";
    public static final String JSON_TOPIC = "topic";
    public static final String JSON_PARTITION = "partition";
    public static final String JSON_OFFSET = "offset";

    private long keyNumberOfMealServed;
    private String valueMealAsJSONString;
    private long timestamp;
    private String topic;
    private int partition;
    private long offset;

    public RecordOfMeal(long keyNumberOfMealServed, String valueMealAsJSONString, long timestamp, String topic, int partition, long offset) {
        this.keyNumberOfMealServed = keyNumberOfMealServed;
        this.valueMealAsJSONString = valueMealAsJSONString;
        this.timestamp = timestamp;
        this.topic = topic;
        this.partition = partition;
        this.offset = offset;
    }

    public RecordOfMeal(JSONObject recordOfMealAsJSON) {
        try {
            keyNumberOfMealServed = recordOfMealAsJSON.getLong(JSON_KEY);
            valueMealAsJSONString = recordOfMealAsJSON.getString(JSON_VALUE);
            timestamp = recordOfMealAsJSON.getLong(JSON_TIMESTAMP);
            topic = recordOfMealAsJSON.getString(JSON_TOPIC);
            partition = recordOfMealAsJSON.getInt(JSON_PARTITION);
            offset = recordOfMealAsJSON.getLong(JSON_OFFSET);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject toJSON() {
        JSONObject recordOfMealAsJSON = new JSONObject();

        try {
            recordOfMealAsJSON.put(JSON_KEY, keyNumberOfMealServed);
            recordOfMealAsJSON.put(JSON_VALUE, valueMealAsJSONString);
            recordOfMealAsJSON.put(JSON_TIMESTAMP, timestamp);
            recordOfMealAsJSON.put(JSON_TOPIC, topic);
            recordOfMealAsJSON.put(JSON_PARTITION, partition);
            recordOfMealAsJSON.put(JSON_OFFSET, offset);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recordOfMealAsJSON;
    }

    public long getKeyNumberOfMealServed() {
        return keyNumberOfMealServed;
    }

    public void setKeyNumberOfMealServed(long keyNumberOfMealServed) {
        this.keyNumberOfMealServed = keyNumberOfMealServed;
    }

    public String getValueMealAsJSONString() {
        return valueMealAsJSONString;
    }

    public void setValueMealAsJSONString(String valueMealAsJSONString) {
        this.valueMealAsJSONString = valueMealAsJSONString;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}
