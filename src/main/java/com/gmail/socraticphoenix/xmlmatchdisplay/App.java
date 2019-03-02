package com.gmail.socraticphoenix.xmlmatchdisplay;

import com.gmail.socraticphoenix.collect.coupling.Pair;
import com.gmail.socraticphoenix.xmlmatchdisplay.display.factory.DisplayRegistry;
import com.gmail.socraticphoenix.xmlmatchdisplay.match.factory.MatchSpecificationRegistry;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    private static boolean showErrors = false;
    private static boolean showIncomplete = false;
    private static boolean showHeaders = false;

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
            } else if (k.equals("-headers")) {
                showHeaders = true;
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

        //Get info
        int xml = config.getInt("xml");
        Configuration configuration = new Configuration(xml, showErrors, showHeaders, showIncomplete);
        List<MatchUnit> units = createUnits(config, configuration);

        List<Matcher> matchers = new ArrayList<>();
        for (MatchUnit unit : units) {
            FileReader reader = new FileReader(targetFile);
            matchers.add(new Matcher(unit, reader));
            reader.close();
        }

        matchers.forEach(Matcher::match);

        List<Matchable> matched = new ArrayList<>();
        if (showHeaders) System.out.println("**Completed Matches**");
        for (Matcher matcher : matchers) {
            MatchUnit spec = matcher.getSpec();
            if (showHeaders) System.out.println("[" + spec.getLeftTitle() + ", " + spec.getRightTitle() + "]");
            for (Pair<Matchable, Matchable> matches : matcher.match()) {
                Matchable left = matches.getA();
                Matchable right = matches.getB();
                matched.add(left);
                matched.add(right);
                System.out.println(toCsv(spec.getDisplay().extract(left, right)));
            }
            if (showHeaders) System.out.println("-------------------------------------------------------------");
        }
        if(showIncomplete) {
            if (showHeaders) System.out.println("**Incomplete Matches**");
            for (Matcher matcher : matchers) {
                MatchUnit spec = matcher.getSpec();
                if (showHeaders) System.out.println("[" + spec.getLeftTitle() + ", " + spec.getRightTitle() + "]");
                for (Matchable left : matcher.getLeft()) {
                    if (!matched.contains(left)) {
                        System.out.println(toCsv(spec.getDisplay().extract(left, NullMatchable.INSTANCE)));
                    }
                }
                for (Matchable right : matcher.getRight()) {
                    if (!matched.contains(right)) {
                        System.out.println(toCsv(spec.getDisplay().extract(NullMatchable.INSTANCE, right)));
                    }
                }
                if (showHeaders) System.out.println("-------------------------------------------------------------");
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


    private static List<MatchUnit> createUnits(JSONObject config, Configuration configuration) {
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
                    MatchSpecificationRegistry.INSTANCE.createSpecification(leftEntry.getJSONObject("match"), configuration),
                    MatchSpecificationRegistry.INSTANCE.createSpecification(rightEntry.getJSONObject("match"), configuration),
                    MatchSpecificationRegistry.INSTANCE.createLateralSpecification(match, configuration),
                    DisplayRegistry.INSTANCE.createDisplay(display, configuration)
            ));
        }
        return units;
    }

}
