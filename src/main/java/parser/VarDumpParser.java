package parser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VarDumpParser {

    private static class Index {
        int i = 0;
    }

    public PHPValue parseVarDump(String input) {
        List<String> lines = preprocessInput(input);
        Index index = new Index();
        return parseValue(lines, index);
    }

    private List<String> preprocessInput(String input) {
        String[] rawLines = input.split("\n");
        if (rawLines.length == 1) {
            input = input
                    .replaceAll("(?<=\\{)", "\n")
                    .replaceAll("(?=\\})", "\n")
                    .replaceAll("(?<=\\})", "\n")
                    .replaceAll("(?<==>)", "\n")
                    .replaceAll("(?=\\[)", "\n")
                    .replaceAll("\\n+", "\n");
        }
        return Arrays.asList(input.split("\n"));
    }

    private PHPValue parseValue(List<String> lines, Index index) {
        String line = lines.get(index.i).trim();
        if (line.startsWith("array(")) {
            return parseArray(lines, index);
        } else if (line.startsWith("string(")) {
            Pattern pattern = Pattern.compile("string\\(\\d+\\) \"(.*?)\"");
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                index.i++;
                return new PHPObject(m.group(1));
            }
        } else if (line.startsWith("int(")) {
            Pattern pattern = Pattern.compile("int\\((\\d+)\\)");
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                index.i++;
                return new PHPObject(Integer.parseInt(m.group(1)));
            }
        } else if (line.startsWith("bool(")) {
            Pattern pattern = Pattern.compile("bool\\((true|false)\\)");
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                index.i++;
                return new PHPObject(Boolean.parseBoolean(m.group(1)));
            }
        } else if (line.startsWith("float(")) {
            Pattern pattern = Pattern.compile("float\\((\\d+\\.\\d+)\\)");
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                index.i++;
                return new PHPObject(Double.parseDouble(m.group(1)));
            }
        } else if (line.startsWith("NULL")) {
                index.i++;
            return new PHPObject(null);
        }
        index.i++;
        return new PHPObject(null);
    }

    private PHPArray parseArray(List<String> lines, Index index) {
        index.i++;
        PHPArray array = new PHPArray();

        while (index.i < lines.size() && lines.get(index.i).trim().isEmpty()) {
            index.i++;
        }

        while (index.i < lines.size()) {
            String line = lines.get(index.i).trim();
            if (line.equals("}")) {
                index.i++;
                return array;
            }
            Pattern keyPattern = Pattern.compile("\\[(\"(.+?)\"|(\\d+))]=>");
            Matcher m = keyPattern.matcher(line);
            if (m.find()) {
                Object key;
                if (m.group(2) != null) {
                    key = m.group(2);
                } else {
                    key = Integer.parseInt(m.group(3));
                }
                index.i++;
                PHPValue value = parseValue(lines, index);
                array.put(key, value);
            } else {
                index.i++;
            }
        }
        return array;
    }

    public String toPHPCode(PHPValue value) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?php\n\n");
        sb.append("$array = ");
        sb.append(value.toPHPString(0));
        sb.append(";\n\n");
        sb.append("var_dump($array);");
        return sb.toString();
    }
}
