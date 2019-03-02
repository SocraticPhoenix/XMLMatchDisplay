package com.gmail.socraticphoenix.xmlmatchdisplay;

import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Matchable {
    private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    private List<String> csv;
    private Map<Integer, Element> xml;

    public Matchable(List<String> csv) {
        this.csv = csv;
        this.xml = new HashMap<>();
    }

    public Optional<Element> getRawXml(int index) {
        if (index < 0 || index >= this.csv.size()) {
            return Optional.empty();
        }

        return Optional.ofNullable(this.xml.computeIfAbsent(index, i -> {
            try {
                DocumentBuilder builder = dbFactory.newDocumentBuilder();
                Element element = builder.parse(new InputSource(new StringReader(this.csv.get(index)))).getDocumentElement();
                element.normalize();
                return element;
            } catch (ParserConfigurationException | SAXException | IOException e) {
                return null;
            }
        }));
    }

    public List<String> getRawCsv() {
        return this.csv;
    }

    public Optional<String> getXml(int index, String... path) {
        Optional<Element> elementOptional = getRawXml(index);
        if (elementOptional.isPresent()) {
            Element element = elementOptional.get();
            try {
                for (String k : path) {
                    element = (Element) element.getElementsByTagName(k).item(0);
                }
                return Optional.ofNullable(element.getTextContent());
            } catch (NullPointerException e) {

            }
        }

        return Optional.empty();
    }

    public Optional<String> getCsv(int index) {
        return (index < 0 || index >= csv.size()) ? Optional.empty() : Optional.ofNullable(csv.get(index));
    }


}
