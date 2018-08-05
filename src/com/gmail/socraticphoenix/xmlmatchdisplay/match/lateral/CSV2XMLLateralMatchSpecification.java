package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.function.BiPredicate;

public class CSV2XMLLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private int left;
    private String[] right;
    private BiPredicate<String, String> match;

    public CSV2XMLLateralMatchSpecification(int left, String[] right, BiPredicate<String, String> match) {
        this.left = left;
        this.right = right;
        this.match = match;
    }

    public CSV2XMLLateralMatchSpecification(int left, String[] right) {
        this(left, right, Objects::equals);
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        return this.match.test(a.getCsv(this.left), b.getXml(this.right));
    }

}
