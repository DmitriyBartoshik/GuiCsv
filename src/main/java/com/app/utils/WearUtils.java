package com.app.utils;


import com.app.utils.readers.CsvReaderImpl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.app.utils.NumberConstants.CSV_ID_COLUMN;

public class WearUtils {
    public static List<String> getAllIndexCategory() {
        return Arrays.asList("0101", "0102", "0103", "0201", "0202", "0203", "0204", "0301", "0302", "0303", "0304", "0307", "0401", "0402", "0403", "0404", "0501");
    }

    public static List<String> getAllMaskNumber() {
        return Arrays.asList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09",
                "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
                "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
                "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
                "60", "61", "62", "63", "64", "65", "66", "67", "68", "69",
                "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
                "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
                "90", "91", "92", "93", "94", "95", "96", "97", "98", "99");
    }

    public static List<String> getAllSize() {
        return Arrays.asList("s", "m", "l", "xl");
    }

    public static List<String> getIdFromCsv(String csvPath) {
        List<String[]> csv = new ArrayList<>();
        try {
            csv = CsvReaderImpl.readAll(new FileReader(csvPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> listId = new ArrayList<>();

        for (String[] line : csv) {
            listId.add(line[CSV_ID_COLUMN]);
        }
        Collections.sort(listId);
        return listId;
    }
}

