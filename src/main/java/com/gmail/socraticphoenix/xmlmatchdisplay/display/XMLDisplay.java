package com.gmail.socraticphoenix.xmlmatchdisplay.display;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class XMLDisplay implements Display {
    private List<String[]> paths;
    private DisplayTarget target;
    private int xml;

    public XMLDisplay(List<String[]> paths, DisplayTarget target, int xml) {
        this.paths = paths;
        this.target = target;
        this.xml = xml;
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
                Optional<String> val = matchable.getXml(this.xml, path);
                result.add(val.orElse(""));
            }
        }
        return result;
    }

}
