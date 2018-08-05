package com.gmail.socraticphoenix.xmlmatchdisplay.display;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.ArrayList;
import java.util.List;

public class XMLDisplay implements Display {
    private List<String[]> paths;
    private DisplayTarget target;

    public XMLDisplay(List<String[]> paths, DisplayTarget target) {
        this.paths = paths;
        this.target = target;
    }

    @Override
    public List<String> extract(Matchable a, Matchable b) {
        List<Matchable> targets = new ArrayList<>();
        if (this.target == DisplayTarget.LEFT) {
            targets.add(a);
        } else if (this.target == DisplayTarget.RIGHT) {
            targets.add(b);
        } else if (this.target == DisplayTarget.BOTH) {
            targets.add(a);
            targets.add(b);
        }


        List<String> result = new ArrayList<>();
        for (Matchable matchable : targets) {
            for (String[] path : paths) {
                String val = matchable.getXml(path);
                result.add(val == null ? "" : val);
            }
        }
        return result;
    }

}
