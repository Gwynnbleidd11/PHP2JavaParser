package parser;

public class PHPObject implements PHPValue {

    private final Object value;

    public PHPObject(Object value) {
        this.value = value;
    }

    @Override
    public String toPHPString(int indent) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}
