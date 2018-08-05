package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import org.json.JSONObject;

public interface LateralMatchSpecificationFactory extends MatchSpecificationFactory {

    LateralMatchSpecification create(JSONObject object);

}
