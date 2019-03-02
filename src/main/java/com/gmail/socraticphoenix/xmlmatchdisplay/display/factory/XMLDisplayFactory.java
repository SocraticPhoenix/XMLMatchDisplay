package com.gmail.socraticphoenix.xmlmatchdisplay.display.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.Util;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.Display;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.DisplayTarget;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.XMLDisplay;
import org.json.JSONObject;

import java.util.List;

public class XMLDisplayFactory implements DisplayFactory {

    @Override
    public Display create(JSONObject object, Configuration configuration) {
        int xml = object.keySet().contains("index") ? object.getInt("index") : configuration.getXml();

        DisplayTarget target = DisplayTarget.valueOf(object.getString("target").toUpperCase());
        List<String[]> paths = Util.stringArrayList(object.getJSONArray("paths"));
        return new XMLDisplay(paths, target, xml);
    }

}
