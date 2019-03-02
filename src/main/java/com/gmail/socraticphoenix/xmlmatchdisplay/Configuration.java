package com.gmail.socraticphoenix.xmlmatchdisplay;

import java.util.List;

public class Configuration {
    private int xml;
    private boolean showErrors;
    private boolean showHeaders;
    private boolean showIncomplete;

    public Configuration(int xml, boolean showErrors, boolean showHeaders, boolean showIncomplete) {
        this.xml = xml;
        this.showErrors = showErrors;
        this.showHeaders = showHeaders;
        this.showIncomplete = showIncomplete;
    }

    public int getXml() {
        return xml;
    }

    public boolean isShowErrors() {
        return showErrors;
    }

    public boolean isShowHeaders() {
        return showHeaders;
    }

    public boolean isShowIncomplete() {
        return showIncomplete;
    }

}
