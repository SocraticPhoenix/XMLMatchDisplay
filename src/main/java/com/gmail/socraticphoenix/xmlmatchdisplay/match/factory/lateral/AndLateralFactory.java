package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.LateralMatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationRegistry;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.AndLateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.OrLateralMatchSpecification;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AndLateralFactory implements LateralMatchSpecificationFactory {

    @Override
    public LateralMatchSpecification create(JSONObject object, Configuration configuration) {
        JSONArray array = object.getJSONArray("conditions");
        List<LateralMatchSpecification> specs = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject sub = array.getJSONObject(i);
            specs.add(MatchSpecificationRegistry.INSTANCE.createLateralSpecification(sub, configuration));
        }
        return new AndLateralMatchSpecification(specs);
    }

}
