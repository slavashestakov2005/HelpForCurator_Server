package com.example.help;

import java.util.ArrayList;

public class JsonWriter {
    private static String[] escaped1, escaped2;
    private static int length;

    static {
        escaped1 = new String[]{    "\\",   "\"",   "/",    "\t",   "\n",   "\r",   "\b" };
        escaped2 = new String[]{    "\\\\", "\\\"", "\\/",  "\\\t", "\\\n", "\\\r", "\\\b"};
        length = escaped1.length;
    }

    public static String write(String tag, String value){
        return "\"" + tag + "\" : \"" + toJsonString(value) + "\"";
    }

    public static String writeIntegers(String tag, ArrayList<Integer> list){
        StringBuilder builder = new StringBuilder();
        builder.append("{\n\"").append(tag).append("\" : [\n");
        if (!list.isEmpty()){
            builder.append(list.get(0));
            for(int i = 1; i < list.size(); ++i) builder.append(",\n").append(list.get(i));
        }
        builder.append("\n]\n}");
        return builder.toString();
    }

    public static String write(String tag, ArrayList<? extends JsonWritable> list){
        StringBuilder builder = new StringBuilder();
        builder.append("{\n\"").append(tag).append("\" : [\n");
        if (!list.isEmpty()){
            builder.append(list.get(0).write());
            for(int i = 1; i < list.size(); ++i) builder.append(",\n").append(list.get(i).write());
        }
        builder.append("\n]\n}");
        return builder.toString();
    }

    public static String writeArray(String tag, ArrayList<? extends JsonWritable> list){
        StringBuilder builder = new StringBuilder();
        builder.append("\"").append(tag).append("\" : [\n");
        if (!list.isEmpty()){
            builder.append(list.get(0).write());
            for(int i = 1; i < list.size(); ++i) builder.append(",\n").append(list.get(i).write());
        }
        builder.append("\n]");
        return builder.toString();
    }

    public static String toJsonString(String text){
        for(int i = 0; i < length; ++i){
            text = text.replace(escaped1[i], escaped2[i]);
        }
        return text;
    }
}
