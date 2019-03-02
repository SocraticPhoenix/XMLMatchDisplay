package com.gmail.socraticphoenix.xmlmatchdisplay.display;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CSVDisplay implements Display {
    private int[] indices;
    private DisplayTarget target;

    public CSVDisplay(int[] indices, DisplayTarget target) {
        this.indices = indices;
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
            for (int i : indices) {
                Optional<String> val = matchable.getCsv(i);
                result.add(val.orElse(""));
            }
        }
        return result;
    }

}
