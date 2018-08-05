package com.gmail.socraticphoenix.xmlmatchdisplay;

import com.gmail.socraticphoenix.xmlmatchdisplay.display.factory.DisplayRegistry;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationRegistry;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class App {
    private static boolean showErrors = false;
    private static boolean showIncomplete = false;

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Expected args: <config> <target> [flags]");
            return;
        }

        File configFile = new File(args[0]);
        File targetFile = new File(args[1]);

        for (int i = 2; i < args.length; i++) {
            String k = args[i];
            if (k.equals("-errors")) {
                showErrors = true;
            } else if (k.equals("-incomplete")) {
                showIncomplete = true;
            }
        }

        if (!configFile.exists()) {
            System.out.println("Config does not exist!");
            return;
        }

        if (!targetFile.exists()) {
            System.out.println("Target does not exist!");
            return;
        }

        //Parse config
        JSONObject config = new JSONObject(new JSONTokener(new FileInputStream(configFile)));

        //Parse file
        CSVParser parser = CSVParser.parse(new FileInputStream(targetFile), Charset.defaultCharset(), CSVFormat.DEFAULT);
        List<List<String>> lines = new ArrayList<>();
        parser.getRecords().forEach(record -> {
            List<String> lineRecord = new ArrayList<>();
            record.iterator().forEachRemaining(lineRecord::add);
            lines.add(lineRecord);
        });

        //Get info
        int xml = config.getInt("xml");
        List<MatchUnit> units = createUnits(config);
        List<Matchable> matchables = createMatchables(lines, xml);

        Map<MatchUnit, List<List<Matchable>>> groupings = new LinkedHashMap<>();
        for (MatchUnit unit : units) {
            List<List<Matchable>> value = new ArrayList<>();
            value.add(new ArrayList<>());
            value.add(new ArrayList<>());
            groupings.put(unit, value);
        }

        for (Matchable matchable : matchables) {
            for (MatchUnit unit : units) {
                if (unit.getLeft().matches(matchable)) {
                    groupings.get(unit).get(0).add(matchable);
                } else if (unit.getRight().matches(matchable)) {
                    groupings.get(unit).get(1).add(matchable);
                }
            }
        }

        matchables = null;

        Map<MatchUnit, List<List<Matchable>>> finalResults = new LinkedHashMap<>();
        Map<MatchUnit, List<List<Matchable>>> unmatched = new LinkedHashMap<>();

        for (Map.Entry<MatchUnit, List<List<Matchable>>> entry : groupings.entrySet()) {
            MatchUnit spec = entry.getKey();
            List<List<Matchable>> matches = entry.getValue();

            List<Matchable> left = matches.get(0);
            List<Matchable> right = matches.get(1);

            for (int i = 0; i < left.size(); i++) {
                Matchable leftPart = left.get(i);
                for (int j = 0; j < right.size(); j++) {
                    Matchable rightPart = right.get(j);

                    if (spec.getMatch().matches(leftPart, rightPart)) {
                        finalResults.computeIfAbsent(spec, k -> new ArrayList<>()).add(Arrays.asList(leftPart, rightPart));
                        right.remove(j);
                        left.remove(i);
                        i--;
                        break;
                    }
                }
            }

            for (Matchable remaining : left) {
                unmatched.computeIfAbsent(spec, k -> new ArrayList<>()).add(Arrays.asList(remaining, new NullMatchable()));
            }

            for (Matchable remaining : right) {
                unmatched.computeIfAbsent(spec, k -> new ArrayList<>()).add(Arrays.asList(new NullMatchable(), remaining));
            }
        }

        System.out.println("**Completed Matches**");
        for (Map.Entry<MatchUnit, List<List<Matchable>>> entry : finalResults.entrySet()) {
            MatchUnit spec = entry.getKey();
            System.out.println("[" + spec.getLeftTitle() + ", " + spec.getRightTitle() + "]");
            for (List<Matchable> matches : entry.getValue()) {
                Matchable left = matches.get(0);
                Matchable right = matches.get(1);
                System.out.println(toCsv(spec.getDisplay().extract(left, right)));
            }
            System.out.println("-------------------------------------------------------------");
        }
        if(showIncomplete) {
            System.out.println("**Incomplete Matches**");
            for (Map.Entry<MatchUnit, List<List<Matchable>>> entry : unmatched.entrySet()) {
                MatchUnit spec = entry.getKey();
                System.out.println("[" + spec.getLeftTitle() + ", " + spec.getRightTitle() + "]");
                for (List<Matchable> matches : entry.getValue()) {
                    Matchable left = matches.get(0);
                    Matchable right = matches.get(1);
                    System.out.println(toCsv(spec.getDisplay().extract(left, right)));
                }
                System.out.println("-------------------------------------------------------------");
            }
        }
    }

    private static String toCsv(List<String> values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            sb.append("\"").append(values.get(i).replace("\"", "\\\"")).append("\"");
            if (i != values.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private static List<Matchable> createMatchables(List<List<String>> lines, int xmlIndex) {
        List<Matchable> matchables = new ArrayList<>();

        int counter = 0;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        for (List<String> row : lines) {
            counter++;

            String xml = row.get(xmlIndex);
            DocumentBuilder builder = null;
            Document document = null;
            try {
                builder = dbFactory.newDocumentBuilder();
                document = builder.parse(new InputSource(new StringReader(xml)));
            } catch (ParserConfigurationException | IOException | SAXException e) {
                if (showErrors) {
                    System.out.println("Skipping line " + counter + " due to parsing errors");
                    e.printStackTrace(System.out);
                    System.out.println("-------------------------------------------------");
                }
                continue;
            }
            Element root = document.getDocumentElement();
            root.normalize();

            matchables.add(new Matchable(root, row));
        }
        return matchables;
    }

    private static List<MatchUnit> createUnits(JSONObject config) {
        JSONArray unitsArray = config.getJSONArray("match");
        List<MatchUnit> units = new ArrayList<>();
        for (int i = 0; i < unitsArray.length(); i++) {
            JSONObject object = unitsArray.getJSONObject(i);
            JSONObject entries = object.getJSONObject("entries");
            JSONObject match = object.getJSONObject("match");
            JSONObject display = object.getJSONObject("display");

            JSONObject leftEntry = entries.getJSONObject("left");
            JSONObject rightEntry = entries.getJSONObject("right");

            units.add(new MatchUnit(
                    leftEntry.getString("title"),
                    rightEntry.getString("title"),
                    MatchSpecificationRegistry.INSTANCE.createSpecification(leftEntry.getJSONObject("match")),
                    MatchSpecificationRegistry.INSTANCE.createSpecification(rightEntry.getJSONObject("match")),
                    MatchSpecificationRegistry.INSTANCE.createLateralSpecification(match),
                    DisplayRegistry.INSTANCE.createDisplay(display)
            ));
        }
        return units;
    }

}
