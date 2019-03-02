package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationRegistry;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.AndMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.OrMatchSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AndFactory implements MatchSpecificationFactory {

    @Override
    public MatchSpecification create(JSONObject object, Configuration configuration) {
        JSONArray array = object.getJSONArray("conditions");
        List<MatchSpecification> specs = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject sub = array.getJSONObject(i);
            specs.add(MatchSpecificationRegistry.INSTANCE.createSpecification(sub,  configuration));
        }
        return new AndMatchSpecification(specs);
    }

}
