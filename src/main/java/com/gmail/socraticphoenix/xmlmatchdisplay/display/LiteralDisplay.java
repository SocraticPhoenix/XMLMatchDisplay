package com.gmail.socraticphoenix.xmlmatchdisplay.display;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.List;

public class LiteralDisplay implements Display {
    private List<String> display;

    public LiteralDisplay(List<String> display) {
        this.display = display;
    }

    @Override
    public List<String> extract(Matchable a, Matchable b) {
        return this.display;
    }

}
