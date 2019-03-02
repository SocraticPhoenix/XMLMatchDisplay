package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.Util;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.XMLMatchSpecification;
import org.json.JSONObject;

public class XMLFactory implements MatchSpecificationFactory {

    @Override
    public MatchSpecification create(JSONObject object, Configuration configuration) {
        String[] path = Util.stringArray(object.getJSONArray("path"));
        int xml = object.keySet().contains("index") ? object.getInt("index") : configuration.getXml();

        if (object.keySet().contains("regex")) {
            return new XMLMatchSpecification(s -> s != null && s.matches(object.getString("regex")), xml, path);
        } else {
            return new XMLMatchSpecification(object.getString("literal"), xml, path);
        }
    }

}
