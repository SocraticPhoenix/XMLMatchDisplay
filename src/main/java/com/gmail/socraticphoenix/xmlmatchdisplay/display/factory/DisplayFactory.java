package com.gmail.socraticphoenix.xmlmatchdisplay.display.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.Configuration;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.Display;
import org.json.JSONObject;

public interface DisplayFactory {

    Display create(JSONObject object, Configuration configuration);

}
