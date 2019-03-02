package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;
import org.json.JSONObject;

public interface MatchSpecificationFactory {

    MatchSpecification create(JSONObject object, Configuration configuration);

}
