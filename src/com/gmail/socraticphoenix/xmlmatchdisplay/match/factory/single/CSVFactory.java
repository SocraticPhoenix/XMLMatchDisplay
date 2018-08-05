package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.CSVMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;
import org.json.JSONObject;

public class CSVFactory implements MatchSpecificationFactory {

    @Override
    public MatchSpecification create(JSONObject object) {
        int ind = object.getInt("index");
        if (object.keySet().contains("regex")) {
            return new CSVMatchSpecification(s -> s != null && s.matches(object.getString("regex")), ind);
        } else {
            return new CSVMatchSpecification(object.getString("literal"), ind);
        }
    }

}
