package com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;

public interface LateralMatchSpecification extends MatchSpecification {

    LateralMatchSpecification provideReference(Matchable matchable);

    default boolean matches(Matchable a, Matchable b) {
        provideReference(a);
        return matches(b);
    }

}
