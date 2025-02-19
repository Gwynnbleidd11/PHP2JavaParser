package parser;

import java.util.LinkedHashMap;
import java.util.Map;

public class PHPArray implements PHPValue {

    private final Map<Object, PHPValue> elements = new LinkedHashMap<>();

    public void put(Object key, PHPValue value) {
        elements.put(key, value);
    }

    @Override
    public String toPHPString(int indent) {
        StringBuilder sb = new StringBuilder();
        String indentStr = "    ".repeat(indent);
        if (elements.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append("[\n");
            int count = 0;
            int size = elements.size();
            for (Map.Entry<Object, PHPValue> entry : elements.entrySet()) {
                sb.append(indentStr).append("    ");
                if (entry.getKey() instanceof String) {
                    sb.append("'").append(entry.getKey()).append("' => ");
                }
                sb.append(entry.getValue().toPHPString(indent + 1));
                count++;
                if (count < size) {
                    sb.append(",\n");
                } else {
                    sb.append("\n");
                }
            }
            sb.append(indentStr).append("]");
        }
        return sb.toString();
    }
}
