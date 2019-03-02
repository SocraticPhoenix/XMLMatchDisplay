package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

public class XML2CSVLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private String[] left;
    private int right;
    private int xml;
    private BiPredicate<String, String> matcher;

    public XML2CSVLateralMatchSpecification(String[] left, int right, int xml, BiPredicate<String, String> matcher) {
        this.left = left;
        this.right = right;
        this.xml = xml;
        this.matcher = matcher;
    }

    public XML2CSVLateralMatchSpecification(String[] left, int right, int xml) {
        this(left, right, xml, Objects::equals);
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        Optional<String> xml = a.getXml(this.xml, this.left);
        Optional<String> csv = b.getCsv(this.right);
        if (xml.isPresent() && csv.isPresent()) {
            return this.matcher.test(xml.get(), csv.get());
        } else {
            return false;
        }
    }

}
