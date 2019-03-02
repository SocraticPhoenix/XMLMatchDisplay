package com.gmail.socraticphoenix.xmlmatchdisplay;

import com.gmail.socraticphoenix.collect.Items;
import org.w3c.dom.Element;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NullMatchable extends Matchable {
    public static final NullMatchable INSTANCE = new NullMatchable();

    public NullMatchable() {
        super(null);
    }

    @Override
    public Optional<Element> getRawXml(int index) {
        return Optional.empty();
    }

    @Override
    public List<String> getRawCsv() {
        return Collections.emptyList();
    }

    @Override
    public Optional<String> getXml(int index, String... path) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getCsv(int index) {
        return Optional.empty();
    }
}
