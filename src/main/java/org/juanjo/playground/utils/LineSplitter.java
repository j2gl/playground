package org.juanjo.playground.utils;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class LineSplitter {

    public static List<String> splitToLines(int maxLineSize, String value) {
        if (value == null) {
            return new ArrayList<>();
        }

        var lines = new ArrayList<String>();
        while (!value.isEmpty()) {
            String line = value.length() > maxLineSize ? value.substring(0, maxLineSize) : value;
            value = value.substring(line.length());
            lines.add(line);
        }

        return lines;
    }

}
