package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;

public class DualLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private MatchSpecification left;
    private MatchSpecification right;

    public DualLateralMatchSpecification(MatchSpecification left, MatchSpecification right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        return this.left.matches(a) && this.right.matches(b);
    }

}
