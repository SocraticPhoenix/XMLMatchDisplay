package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.Util;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.LateralMatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.CSV2XMLLateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.CSVLateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import org.json.JSONObject;

public class CSV2XMLLateralFactory implements LateralMatchSpecificationFactory {

    @Override
    public LateralMatchSpecification create(JSONObject object, Configuration configuration) {
        int left = object.getInt("left");
        String[] right = Util.stringArray(object.getJSONArray("right"));

        int xml = object.keySet().contains("rightIndex") ? object.getInt("rightIndex") : configuration.getXml();

        if (object.keySet().contains("regex")) {
            String r = object.getString("regex");
            return new CSV2XMLLateralMatchSpecification(left, right, xml, (a, b) -> (a == b && a == null) || (a != null && b != null && a.matches(r) && b.matches(r)));
        } else {
            return new CSV2XMLLateralMatchSpecification(left, right, xml);
        }
    }

}
