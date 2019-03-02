package com.gmail.socraticphoenix.xmlmatchdisplay;

import com.gmail.socraticphoenix.collect.coupling.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Matcher {
    private List<Matchable> left;
    private List<Matchable> right;

    private MatchUnit spec;

    private List<Pair<Matchable, Matchable>> matches = new ArrayList<>();

    public Matcher(MatchUnit spec, InputStream in) throws IOException {
        this(spec, new InputStreamReader(in));
    }

    public Matcher(MatchUnit spec, String in) throws IOException {
        this(spec, new StringReader(in));
    }

    public Matcher(MatchUnit spec, List<Matchable> left, List<Matchable> right) {
        this.spec = spec;
        this.left = left;
        this.right = right;
    }

    public Matcher(MatchUnit spec, Reader in) throws IOException {
        this.spec = spec;
        this.left = new ArrayList<>();
        this.right = new ArrayList<>();

        BufferedReader reader = new BufferedReader(in);

        String line;
        while ((line = reader.readLine()) != null) {
            Matchable matchable = new Matchable(CSVUtility.parseLine(line));

            if (spec.getLeft().matches(matchable)) {
                left.add(matchable);
            }

            if (spec.getRight().matches(matchable)) {
                right.add(matchable);
            }
        }
    }

    public List<Pair<Matchable, Matchable>> match() {
        if (matches.isEmpty()) {
            ListIterator<Matchable> left = this.left.listIterator();

            while (left.hasNext()) {
                Matchable leftPiece = left.next();
                ListIterator<Matchable> right = this.right.listIterator();
                while (right.hasNext()) {
                    Matchable rightPiece = right.next();

                    if (this.spec.getMatch().matches(leftPiece, rightPiece)) {
                        matches.add(Pair.of(leftPiece, rightPiece));
                    }
                }
            }
        }

        return matches;
    }

    public List<Matchable> getLeft() {
        return left;
    }

    public List<Matchable> getRight() {
        return right;
    }

    public MatchUnit getSpec() {
        return spec;
    }

}
