package com.gmail.socraticphoenix.xmlmatchdisplay.match.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class XMLMatchSpecification implements MatchSpecification {
    private String[] path;
    private Predicate<String> matcher;
    private int xml;

    public XMLMatchSpecification(Predicate<String> matcher, int xml, String... path) {
        this.path = path;
        this.matcher = matcher;
        this.xml = xml;
    }

    public XMLMatchSpecification(String value, int xml, String... path) {
        this(s -> Objects.equals(s, value), xml, path);
    }

    @Override
    public boolean matches(Matchable matchable) {
        Optional<String> xml = matchable.getXml(this.xml, this.path);
        if (xml.isPresent()) {
            return this.matcher.test(xml.get());
        } else {
            return false;
        }
    }

}
