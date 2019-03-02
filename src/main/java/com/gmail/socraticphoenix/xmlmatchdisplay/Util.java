package com.gmail.socraticphoenix.xmlmatchdisplay;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static String[] stringArray(JSONArray array) {
        return array.toList().stream().map(String::valueOf).toArray(String[]::new);
    }

    public static int[] intArray(JSONArray array) {
        return array.toList().stream().mapToInt(o -> (int) o).toArray();
    }

    public static List<String[]> stringArrayList(JSONArray array) {
        return toList(array).stream().map(o -> stringArray((JSONArray) o)).collect(Collectors.toList());
    }

    public static List<Object> toList(JSONArray array) {
        List<Object> list = new ArrayList<>();
        array.forEach(list::add);
        return list;
    }

}
