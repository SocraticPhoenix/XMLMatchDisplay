package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.List;

public class OrLateralMatchSpecification extends AbstractLateralMatchSpecification {
    private List<LateralMatchSpecification> specs;

    public OrLateralMatchSpecification(List<LateralMatchSpecification> specs) {
        this.specs = specs;
    }

    @Override
    protected boolean testMatch(Matchable a, Matchable b) {
        for (LateralMatchSpecification spec : specs) {
            if (spec.matches(a, b)) {
                return true;
            }
        }
        return false;
    }

}
