package com.gmail.socraticphoenix.xmlmatchdisplay.match.single;

import com.gmail.socraticphoenix.xmlmatchdisplay.Matchable;
import org.w3c.dom.Element;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class TagNameMatchSpecification implements MatchSpecification {
    private Predicate<String> matcher;
    private int xml;

    public TagNameMatchSpecification(int xml, Predicate<String> matcher) {
        this.matcher = matcher;
        this.xml = xml;
    }

    public TagNameMatchSpecification(int xml, String tag) {
        this(xml, s -> Objects.equals(s, tag));
    }

    @Override
    public boolean matches(Matchable matchable) {
        Optional<Element> xml = matchable.getRawXml(this.xml);
        if (xml.isPresent()) {
            return this.matcher.test(xml.get().getTagName());
        } else {
            return false;
        }
    }

}
