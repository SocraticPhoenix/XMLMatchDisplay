package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Util;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.LateralMatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.XMLLateralMatchSpecification;
import org.json.JSONObject;

public class XMLLateralFactory implements LateralMatchSpecificationFactory {

    @Override
    public LateralMatchSpecification create(JSONObject object) {
        String[] left = Util.stringArray(object.getJSONArray("left"));
        String[] right = Util.stringArray(object.getJSONArray("right"));
        if (object.keySet().contains("regex")) {
            String r = object.getString("regex");
            return new XMLLateralMatchSpecification(left, right, (a, b) -> (a == b && a == null) || (a != null && b != null && a.matches(r) && b.matches(r)));
        } else {
            return new XMLLateralMatchSpecification(left, right);
        }
    }

}
