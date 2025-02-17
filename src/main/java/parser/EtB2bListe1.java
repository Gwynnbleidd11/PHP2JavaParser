package parser;

import java.util.List;

public class EtB2bListe1 {

    private List<Item> items;

    public EtB2bListe1(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        sb.append("        'item' => [\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).toString());
            if (i < items.size() - 1) {
                sb.append(",\n");
            }
            sb.append("\n");
        }
        sb.append("        ]\n");
        sb.append("    ]");
        return sb.toString();
    }
}
