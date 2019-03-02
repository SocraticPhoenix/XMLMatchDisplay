package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

public class XMLLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private String[] left;
    private String[] right;
    private int leftXml;
    private int rightXml;
    private BiPredicate<String, String> matcher;

    public XMLLateralMatchSpecification(String[] left, String[] right, int leftXml, int rightXml, BiPredicate<String, String> matcher) {
        this.left = left;
        this.right = right;
        this.leftXml = leftXml;
        this.rightXml = rightXml;
        this.matcher = matcher;
    }

    public XMLLateralMatchSpecification(String[] left, String[] right, int leftXml, int rightXml) {
        this(left, right, leftXml, rightXml, Objects::equals);
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        Optional<String> leftXml = a.getXml(this.leftXml, this.left);
        Optional<String> rightXml = b.getXml(this.rightXml, this.right);
        if (leftXml.isPresent() && rightXml.isPresent()) {
            return this.matcher.test(leftXml.get(), rightXml.get());
        } else {
            return false;
        }
    }
}
