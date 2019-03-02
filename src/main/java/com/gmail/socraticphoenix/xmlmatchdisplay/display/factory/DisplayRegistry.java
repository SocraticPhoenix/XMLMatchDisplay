package com.gmail.socraticphoenix.xmlmatchdisplay.display.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.Display;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class DisplayRegistry {
    public static final DisplayRegistry INSTANCE = new DisplayRegistry();

    private Map<String, DisplayFactory> displayFactories = new LinkedHashMap<>();

    private DisplayRegistry() {
        registerDefault();
    }

    public void register(String type, DisplayFactory factory) {
        if (displayFactories.containsKey(type)) {
            throw new IllegalArgumentException("Duplicate key: " + type);
        }
        displayFactories.put(type, factory);
    }

    public DisplayFactory getFactory(String type) {
        return displayFactories.get(type);
    }

    public Display createDisplay(JSONObject object, Configuration configuration) {
        if (object.opt("type") instanceof String) {
            String type = object.getString("type");
            DisplayFactory factory = getFactory(type);
            if (factory != null) {
                return factory.create(object, configuration);
            } else {
                throw new IllegalArgumentException("No factory for '" + type + "' found in: " + object.toString());
            }
        } else {
            throw new IllegalArgumentException("No 'type' found in: " + object.toString());
        }

    }

    private void registerDefault() {
        register("xml", new XMLDisplayFactory());
        register("csv", new CSVDisplayFactory());
        register("literal", new LiteralDisplayFactory());
        register("sequence", new SequenceDisplayFactory());
    }

}
