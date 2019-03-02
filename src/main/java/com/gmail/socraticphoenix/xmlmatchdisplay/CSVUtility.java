package com.gmail.socraticphoenix.xmlmatchdisplay;

import com.gmail.socraticphoenix.parse.CharacterStream;
import com.gmail.socraticphoenix.parse.ParserData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CSVUtility {

    public static List<String> parseLine(String line) {
        try {
            List<String> result = new ArrayList<>();
            CSVParser.parse(line, CSVFormat.DEFAULT).getRecords().get(0).iterator().forEachRemaining(result::add);
            return result;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
