package com.gmail.socraticphoenix.xmlmatchdisplay.match.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.List;

public class AndMatchSpecification implements MatchSpecification {
    private List<MatchSpecification> specs;

    public AndMatchSpecification(List<MatchSpecification> specs) {
        this.specs = specs;
    }

    @Override
    public boolean matches(Matchable matchable) {
        for(MatchSpecification specification : specs) {
            if (!specification.matches(matchable)) {
                return false;
            }
        }
        return true;
    }

}
