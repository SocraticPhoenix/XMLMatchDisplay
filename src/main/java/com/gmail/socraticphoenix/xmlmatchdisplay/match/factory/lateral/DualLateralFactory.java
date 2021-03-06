package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.LateralMatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationRegistry;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.DualLateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import org.json.JSONObject;

public class DualLateralFactory implements LateralMatchSpecificationFactory {

    @Override
    public LateralMatchSpecification create(JSONObject object, Configuration configuration) {
        return new DualLateralMatchSpecification(MatchSpecificationRegistry.INSTANCE.createSpecification(object.getJSONObject("left"), configuration),
                MatchSpecificationRegistry.INSTANCE.createSpecification(object.getJSONObject("right"), configuration));
    }

}
