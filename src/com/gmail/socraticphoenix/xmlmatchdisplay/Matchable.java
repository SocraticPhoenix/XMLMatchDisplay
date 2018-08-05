package com.gmail.socraticphoenix.xmlmatchdisplay;

import org.w3c.dom.Element;

import java.util.List;

public class Matchable {
    private Element xml;
    private List<String> csv;

    public Matchable(Element xml, List<String> csv) {
        this.xml = xml;
        this.csv = csv;
    }

    public Element getRawXml() {
        return this.xml;
    }

    public List<String> getRawCsv() {
        return this.csv;
    }

    public String getXml(String... path) {
        Element element = xml;
        try {
            for (String k : path) {
                element = (Element) element.getElementsByTagName(k).item(0);
            }
            return element.getTextContent();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getCsv(int index) {
        return (index < 0 || index >= csv.size()) ? null : csv.get(index);
    }


}
