package com.gmail.socraticphoenix.xmlmatchdisplay.display.factory;

import com.gmail.socraticphoenix.xmlmatchdisplay.display.Display;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.SequenceDisplay;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SequenceDisplayFactory implements DisplayFactory {

    @Override
    public Display create(JSONObject object) {
        JSONArray array = object.getJSONArray("components");
        List<Display> components = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            components.add(DisplayRegistry.INSTANCE.createDisplay(array.getJSONObject(i)));
        }
        return new SequenceDisplay(components);
    }

}
