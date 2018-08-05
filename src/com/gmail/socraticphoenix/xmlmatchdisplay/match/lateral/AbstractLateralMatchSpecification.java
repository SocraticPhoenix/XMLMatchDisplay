package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

public abstract class AbstractLateralMatchSpecification implements LateralMatchSpecification {
    private Matchable reference;

    @Override
    public LateralMatchSpecification provideReference(Matchable matchable) {
        this.reference = matchable;
        return this;
    }

    @Override
    public boolean matches(Matchable matchable) {
        if (this.reference == null) {
            throw new IllegalStateException("No reference matchable provided");
        }

        return this.testMatch(this.reference, matchable);
    }

    protected abstract boolean testMatch(Matchable a, Matchable b);
}
