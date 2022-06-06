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

    private long key;
    private String value;
    private long timestamp;
    private String topic;
    private int partition;
    private long offset;

    public RecordOfMeal(long key, String value, long timestamp, String topic, int partition, long offset) {
        this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.topic = topic;
        this.partition = partition;
        this.offset = offset;
    }

    public RecordOfMeal(JSONObject recordOfMealAsJSON) {
        try {
            key = recordOfMealAsJSON.getLong(JSON_KEY);
            value = recordOfMealAsJSON.getString(JSON_VALUE);
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
            recordOfMealAsJSON.put(JSON_KEY, key);
            recordOfMealAsJSON.put(JSON_VALUE, value);
            recordOfMealAsJSON.put(JSON_TIMESTAMP, timestamp);
            recordOfMealAsJSON.put(JSON_TOPIC, topic);
            recordOfMealAsJSON.put(JSON_PARTITION, partition);
            recordOfMealAsJSON.put(JSON_OFFSET, offset);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recordOfMealAsJSON;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
