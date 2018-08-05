package com.gmail.socraticphoenix.xmlmatchdisplay.match.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.function.Predicate;

public class XMLMatchSpecification implements MatchSpecification {
    private String[] path;
    private Predicate<String> matcher;

    public XMLMatchSpecification(Predicate<String> matcher, String... path) {
        this.path = path;
        this.matcher = matcher;
    }

    public XMLMatchSpecification(String value, String... path) {
        this(s -> Objects.equals(s, value), path);
    }

    @Override
    public boolean matches(Matchable matchable) {
        return this.matcher.test(matchable.getXml(this.path));
    }

}
