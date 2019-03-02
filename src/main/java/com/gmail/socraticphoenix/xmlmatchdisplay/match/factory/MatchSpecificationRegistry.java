package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.lateral.*;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.single.*;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class MatchSpecificationRegistry {
    public static final MatchSpecificationRegistry INSTANCE = new MatchSpecificationRegistry();

    private Map<String, MatchSpecificationFactory> matchSpecificationFactories = new LinkedHashMap<>();
    private Map<String, LateralMatchSpecificationFactory> lateralMatchSpecificationFactories = new LinkedHashMap<>();

    private MatchSpecificationRegistry() {
        registerDefaults();
    }

    public void register(String type, MatchSpecificationFactory factory) {
        if (factory instanceof LateralMatchSpecificationFactory) {
            if (lateralMatchSpecificationFactories.containsKey(type)) {
                throw new IllegalArgumentException("Duplicate key: " + type);
            }
            lateralMatchSpecificationFactories.put(type, (LateralMatchSpecificationFactory) factory);
        } else {
            if (matchSpecificationFactories.containsKey(type)) {
                throw new IllegalArgumentException("Duplicate key: " + type);
            }
            matchSpecificationFactories.put(type, factory);
        }
    }

    public MatchSpecificationFactory getFactory(String type) {
        return this.matchSpecificationFactories.get(type);
    }

    public LateralMatchSpecificationFactory getLateralFactory(String type) {
        return this.lateralMatchSpecificationFactories.get(type);
    }

    public MatchSpecification createSpecification(JSONObject object, Configuration configuration) {
        if (object.opt("type") instanceof String) {
            String type = object.getString("type");
            MatchSpecificationFactory factory = getFactory(type);
            if (factory != null) {
                return factory.create(object, configuration);
            } else {
                throw new IllegalArgumentException("No factory for '" + type + "' found in: " + object.toString());
            }
        } else {
            throw new IllegalArgumentException("No 'type' found in: " + object.toString());
        }
    }

    public LateralMatchSpecification createLateralSpecification(JSONObject object, Configuration configuration) {
        if (object.opt("type") instanceof String) {
            String type = object.getString("type");
            LateralMatchSpecificationFactory factory = getLateralFactory(type);
            if (factory != null) {
                return factory.create(object, configuration);
            } else {
                throw new IllegalArgumentException("No factory for '" + type + "' found in: " + object.toString());
            }
        } else {
            throw new IllegalArgumentException("No 'type' found in: " + object.toString());
        }
    }

    private void registerDefaults() {
        register("or", new OrFactory());
        register("and", new AndFactory());
        register("csv", new CSVFactory());
        register("xml", new XMLFactory());
        register("tag", new TagNameFactory());

        register("or", new OrLateralFactory());
        register("and", new AndLateralFactory());
        register("csv", new CSVLateralFactory());
        register("xml", new XMLLateralFactory());
        register("xml2csv", new XML2CSVLateralFactory());
        register("csv2xml", new CSV2XMLLateralFactory());
        register("dual", new DualLateralFactory());
    }
}
