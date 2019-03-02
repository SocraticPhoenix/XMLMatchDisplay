package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.Util;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.LateralMatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.CSVLateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.XML2CSVLateralMatchSpecification;
import org.json.JSONObject;

public class XML2CSVLateralFactory implements LateralMatchSpecificationFactory {

    @Override
    public LateralMatchSpecification create(JSONObject object, Configuration configuration) {
        String[] left = Util.stringArray(object.getJSONArray("left"));
        int right = object.getInt("right");

        int xml = object.keySet().contains("leftIndex") ? object.getInt("leftIndex") : configuration.getXml();

        if (object.keySet().contains("regex")) {
            String r = object.getString("regex");
            return new XML2CSVLateralMatchSpecification(left, right, xml, (a, b) -> (a == b && a == null) || (a != null && b != null && a.matches(r) && b.matches(r)));
        } else {
            return new XML2CSVLateralMatchSpecification(left, right, xml);
        }
    }

}
