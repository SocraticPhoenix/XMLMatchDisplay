package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

public class CSV2XMLLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private int left;
    private String[] right;
    private int xml;
    private BiPredicate<String, String> match;

    public CSV2XMLLateralMatchSpecification(int left, String[] right, int xml, BiPredicate<String, String> match) {
        this.left = left;
        this.right = right;
        this.match = match;
        this.xml = xml;
    }

    public CSV2XMLLateralMatchSpecification(int left, String[] right, int xml) {
        this(left, right, xml, Objects::equals);
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        Optional<String> xml = b.getXml(this.xml, this.right);
        Optional<String> csv = a.getCsv(this.left);
        if (xml.isPresent() && csv.isPresent()) {
            return this.match.test(csv.get(), xml.get());
        } else {
            return false;
        }
    }

}
