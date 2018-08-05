package com.gmail.socraticphoenix.xmlmatchdisplay.match.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.function.Predicate;

public class TagNameMatchSpecification implements MatchSpecification {
    private Predicate<String> matcher;

    public TagNameMatchSpecification(Predicate<String> matcher) {
        this.matcher = matcher;
    }

    public TagNameMatchSpecification(String tag) {
        this(s -> Objects.equals(s, tag));
    }

    @Override
    public boolean matches(Matchable matchable) {
        return this.matcher.test(matchable.getRawXml().getTagName());
    }

}
