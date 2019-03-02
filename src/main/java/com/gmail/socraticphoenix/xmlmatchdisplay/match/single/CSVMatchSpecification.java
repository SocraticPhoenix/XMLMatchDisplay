package com.gmail.socraticphoenix.xmlmatchdisplay.match.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.Optional;
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
        Optional<String> csv = matchable.getCsv(this.index);
        if (csv.isPresent()) {
            return this.matcher.test(csv.get());
        } else {
            return false;
        }
    }

}
