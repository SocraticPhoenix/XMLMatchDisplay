package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CSVLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private int left;
    private int right;
    private BiPredicate<String, String> matcher;

    public CSVLateralMatchSpecification(int left, int right, BiPredicate<String, String> matcher) {
        this.left = left;
        this.right = right;
        this.matcher = matcher;
    }

    public CSVLateralMatchSpecification(int left, int right) {
        this(left, right, Objects::equals);
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        return this.matcher.test(a.getCsv(this.left), b.getCsv(this.right));
    }
}
