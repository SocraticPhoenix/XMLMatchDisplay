package com.gmail.socraticphoenix.xmlmatchdisplay.display.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.Util;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.Display;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.LiteralDisplay;
import org.json.JSONObject;

import java.util.Arrays;

public class LiteralDisplayFactory implements DisplayFactory {

    @Override
    public Display create(JSONObject object, Configuration configuration) {
        return new LiteralDisplay(Arrays.asList(Util.stringArray(object.getJSONArray("display"))));
    }

}
