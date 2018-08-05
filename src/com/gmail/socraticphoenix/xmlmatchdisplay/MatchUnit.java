package com.gmail.socraticphoenix.xmlmatchdisplay;

import com.gmail.socraticphoenix.xmlmatchdisplay.display.Display;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.lateral.LateralMatchSpecification;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.single.MatchSpecification;

public class MatchUnit {
    private String leftTitle;
    private String rightTitle;
    private MatchSpecification left;
    private MatchSpecification right;
    private LateralMatchSpecification match;
    private Display display;

    public MatchUnit(String leftTitle, String rightTitle, MatchSpecification left, MatchSpecification right, LateralMatchSpecification match, Display display) {
        this.leftTitle = leftTitle;
        this.rightTitle = rightTitle;
        this.left = left;
        this.right = right;
        this.match = match;
        this.display = display;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public String getRightTitle() {
        return rightTitle;
    }

    public MatchSpecification getLeft() {
        return left;
    }

    public MatchSpecification getRight() {
        return right;
    }

    public LateralMatchSpecification getMatch() {
        return match;
    }

    public Display getDisplay() {
        return display;
    }

}
