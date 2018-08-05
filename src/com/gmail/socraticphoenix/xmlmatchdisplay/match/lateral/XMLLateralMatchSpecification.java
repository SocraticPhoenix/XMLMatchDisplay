package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.function.BiPredicate;

public class XMLLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private String[] left;
    private String[] right;
    private BiPredicate<String, String> matcher;

    public XMLLateralMatchSpecification(String[] left, String[] right, BiPredicate<String, String> matcher) {
        this.left = left;
        this.right = right;
        this.matcher = matcher;
    }

    public XMLLateralMatchSpecification(String[] left, String[] right) {
        this(left, right, Objects::equals);
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        return this.matcher.test(a.getXml(this.left), b.getXml(this.right));
    }
}
