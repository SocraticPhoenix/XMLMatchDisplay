package com.gmail.socraticphoenix.xmlmatchdisplay.match.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.function.Predicate;

public class CSVMatchSpecification implements MatchSpecification {
    private Predicate<String> matcher;
    private int index;

    public CSVMatchSpecification(Predicate<String> matcher, int index) {
        this.matcher = matcher;
        this.index = index;
    }

    public CSVMatchSpecification(String value, int index) {
        this(s -> Objects.equals(s, value), index);
    }

    @Override
    public boolean matches(Matchable matchable) {
        return this.matcher.test(matchable.getCsv(this.index));
    }

}
