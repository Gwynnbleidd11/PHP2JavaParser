import parser.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Object> data1 = new LinkedHashMap<>();
        data1.put("KUNNR", "0100999790");
        data1.put("AUART", "ZTA");
        data1.put("VBELN", "0240554397");
        data1.put("POSNR", "000010");
        data1.put("BOOL", true);
        data1.put("INT", 13);
        data1.put("FLOAT", 13.2);
        data1.put("NULL", null);
        Item item1 = new Item(data1);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("KUNNR", "0100999790");
        data2.put("AUART", "ZTA");
        data2.put("VBELN", "0240554397");
        data2.put("POSNR", "000020");
        Item item2 = new Item(data2);

        List<Item> items = Arrays.asList(item1, item2);
        EtB2bListe1 etB2bListe1 = new EtB2bListe1(items);
        PhpArray phpArray = new PhpArray(etB2bListe1);

        System.out.println(phpArray);
    }
}
