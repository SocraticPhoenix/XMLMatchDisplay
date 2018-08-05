package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Util;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.XMLMatchSpecification;
import org.json.JSONObject;

public class XMLFactory implements MatchSpecificationFactory {

    @Override
    public MatchSpecification create(JSONObject object) {
        String[] path = Util.stringArray(object.getJSONArray("path"));
        if (object.keySet().contains("regex")) {
            return new XMLMatchSpecification(s -> s != null && s.matches(object.getString("regex")), path);
        } else {
            return new XMLMatchSpecification(object.getString("literal"), path);
        }
    }

}
