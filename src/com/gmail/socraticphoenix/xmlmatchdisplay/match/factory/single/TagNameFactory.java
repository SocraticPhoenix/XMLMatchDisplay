package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.TagNameMatchSpecification;
import org.json.JSONObject;

public class TagNameFactory implements MatchSpecificationFactory {

    @Override
    public MatchSpecification create(JSONObject object) {
        if (object.keySet().contains("regex")) {
            return new TagNameMatchSpecification(s -> s != null && s.matches(object.getString("regex")));
        } else {
            return new TagNameMatchSpecification(object.getString("literal"));
        }
    }

}
