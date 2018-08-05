package com.gmail.socraticphoenix.xmlmatchdisplay.display.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.Util;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.CSVDisplay;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.Display;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.DisplayTarget;
import org.json.JSONObject;

public class CSVDisplayFactory implements DisplayFactory {

    @Override
    public Display create(JSONObject object) {
        DisplayTarget target = DisplayTarget.valueOf(object.getString("target").toUpperCase());
        int[] indices = Util.intArray(object.getJSONArray("indices"));
        return new CSVDisplay(indices, target);
    }

}
