package parser;

import java.util.Map;

public class Item {

    private Map<String, Object> data;

    public Item(Map<String, Object> data) {
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("            [\n");
        int count = 0;
        int size = data.size();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            sb.append("                '").append(entry.getKey()).append("' => ");
            Object value = entry.getValue();
            if (value instanceof String) {
                sb.append("'").append(value).append("'");
            } else if (value == null) {
                sb.append("null");
            } else {
                sb.append(value);
            }
            if (++count < size) {
                sb.append(",\n");
            } else {
                sb.append("\n");
            }
        }
        sb.append("            ]");
        return sb.toString();
    }
}
