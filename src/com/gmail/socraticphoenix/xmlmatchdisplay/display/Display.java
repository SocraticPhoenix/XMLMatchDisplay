package com.gmail.socraticphoenix.xmlmatchdisplay.display;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;

import java.util.List;

public interface Display {

    List<String> extract(Matchable a, Matchable b);

}
