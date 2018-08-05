package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.function.BiPredicate;

public class XML2CSVLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private String[] left;
    private int right;
    private BiPredicate<String, String> matcher;

    public XML2CSVLateralMatchSpecification(String[] left, int right, BiPredicate<String, String> matcher) {
        this.left = left;
        this.right = right;
        this.matcher = matcher;
    }

    public XML2CSVLateralMatchSpecification(String[] left, int right) {
        this(left, right, Objects::equals);
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        return this.matcher.test(a.getXml(this.left), b.getCsv(this.right));
    }

}
