package com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.LateralMatchSpecificationFactory;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.CSVLateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import org.json.JSONObject;

public class CSVLateralFactory implements LateralMatchSpecificationFactory {

    @Override
    public LateralMatchSpecification create(JSONObject object) {
        int left = object.getInt("left");
        int right = object.getInt("right");
        if (object.keySet().contains("regex")) {
            String r = object.getString("regex");
            return new CSVLateralMatchSpecification(left, right, (a, b) -> (a == b && a == null) || (a != null && b != null && a.matches(r) && b.matches(r)));
        } else {
            return new CSVLateralMatchSpecification(left, right);
        }
    }

}
