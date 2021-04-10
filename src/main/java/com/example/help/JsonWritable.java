package com.example.help;

public interface JsonWritable {
    StringBuilder jsonStringBuilder = new StringBuilder();
    String write();
    default void start(){
        jsonStringBuilder.setLength(0);
        jsonStringBuilder.append("{");
    }

    default void finish(){
        jsonStringBuilder.append("}");
    }

    default void addInt(String tag, int value, boolean comma){
        jsonStringBuilder.append("\"").append(tag).append("\": ").append(value).append(comma ? ", " : "");
    }

    default void addInt(String tag, int value){
        addInt(tag, value, true);
    }

    default void addString(String tag, String value, boolean comma){
        jsonStringBuilder.append("\"").append(tag).append("\": \"").append(JsonWriter.toJsonString(value)).append("\"").append(comma ? ", " : "");
    }

    default void addString(String tag, String value){
        addString(tag, value, true);
    }
}
