package parser;

public class PhpArray {

    private EtB2bListe1 etB2bListe1;

    public PhpArray(EtB2bListe1 etB2bListe1) {
        this.etB2bListe1 = etB2bListe1;
    }

    public EtB2bListe1 getEtB2bListe1() {
        return etB2bListe1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?php\n\n");
        sb.append("$array = [\n\n");
        sb.append("    'ET_B2B_LISTE1' => ");
        sb.append(etB2bListe1.toString());
        sb.append("\n];\n\n");
        sb.append("var_dump($array);");
        return sb.toString();
    }
}
