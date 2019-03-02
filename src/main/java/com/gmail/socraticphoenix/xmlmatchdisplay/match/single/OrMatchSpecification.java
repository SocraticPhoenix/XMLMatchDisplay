package com.gmail.socraticphoenix.xmlmatchdisplay.match.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.List;

public class OrMatchSpecification implements MatchSpecification {
    private List<MatchSpecification> specs;

    public OrMatchSpecification(List<MatchSpecification> specs) {
        this.specs = specs;
    }

    @Override
    public boolean matches(Matchable matchable) {
        for(MatchSpecification spec : specs) {
            if (spec.matches(matchable)) {
                return true;
            }
        }
        return false;
    }

}
