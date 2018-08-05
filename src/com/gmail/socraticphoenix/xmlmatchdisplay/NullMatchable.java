package com.gmail.socraticphoenix.xmlmatchdisplay;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class NullMatchable extends Matchable {

    public NullMatchable() {
        super(null, null);
    }

    @Override
    public Element getRawXml() {
        return null;
    }

    @Override
    public List<String> getRawCsv() {
        return new ArrayList<>();
    }

    @Override
    public String getXml(String... path) {
        return null;
    }

    @Override
    public String getCsv(int index) {
        return null;
    }

}
