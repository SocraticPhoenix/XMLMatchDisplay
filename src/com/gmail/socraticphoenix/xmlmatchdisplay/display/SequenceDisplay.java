package com.gmail.socraticphoenix.xmlmatchdisplay.display;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.ArrayList;
import java.util.List;

public class SequenceDisplay implements Display {
    private List<Display> components;

    public SequenceDisplay(List<Display> components) {
        this.components = components;
    }

    @Override
    public List<String> extract(Matchable a, Matchable b) {
        List<String> result = new ArrayList<>();
        for (Display display : components) {
            result.addAll(display.extract(a, b));
        }
        return result;
    }

}
