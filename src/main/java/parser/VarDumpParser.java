package parser;

import java.util.Arrays;
import java.util.List;

public class VarDumpParser {

    public String processVarDump(String input) {
        List<String> lines = Arrays.asList(input.split("\\r?\\n"));

        int itemBlockStart = -1;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).trim().startsWith("[\"item\"]=>")) {
                itemBlockStart = i;
                break;
            }
        }

        if (itemBlockStart == -1) {
            throw new RuntimeException("Could not locate the \"item\" block.");
        }

        int itemsArrayStart = itemBlockStart + 1;
        while (itemsArrayStart < lines.size() && !lines.get(itemsArrayStart).trim().startsWith("array(")) {
            itemsArrayStart++;
        }
        if (itemsArrayStart >= lines.size()) {
            throw new RuntimeException("Could not locate the items array.");
        }

        return null;
    }
}
