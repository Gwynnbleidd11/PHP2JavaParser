package com.application.parser.sql;

import com.application.parser.PhpArray;
import com.application.parser.PhpObject;
import com.application.parser.PhpValue;
import com.application.parser.VarDumpParser;
import org.springframework.stereotype.Component;

import java.util.*;

    @Component
    public class SqlParser implements SqlValue {

        private final VarDumpParser parser;

        public SqlParser(VarDumpParser parser) {
        this.parser = parser;
    }

    @Override
    public String toSqlString(String inputString) {
        StringBuilder sqlString = new StringBuilder();
        PhpValue rootValue = parser.parseVarDump(inputString);
        PhpArray rootArray = (PhpArray) rootValue;

        if (rootArray.getElements().isEmpty()) {
            return "No data in var_dump() output to create SQL QUERY";
        }

        for (Map.Entry<Object, PhpValue> tableEntry : rootArray.getElements().entrySet()) {
            String tableName = tableEntry.getKey().toString();
            PhpArray tableData = (PhpArray) tableEntry;

            List<Map<String, PhpValue>> rowList = extractRows(tableData);

            List<String> allColumns = new ArrayList<>();
            for (Map<String, PhpValue> row : rowList) {
                allColumns.addAll(row.keySet());
            }

            sqlString.append("INSERT INTO ")
                    .append(tableName)
                    .append(" (")
                    .append(String.join(", ", allColumns))
                    .append(") VALUES ");
            List<String> rowStrings = new ArrayList<>();
            for (Map<String, PhpValue> row : rowList) {
                List<String> values = new ArrayList<>();
                for (String column : allColumns) {
                    values.add(extractSqlValue(row.get(column)));
                }
                rowStrings.add("(" + String.join(", ", values) + ")");
            }
            sqlString.append(String.join(",\n", rowStrings))
                    .append(";\n");
        }
        return sqlString.toString();
    }

    private PhpArray toArray(PhpValue value) {
        if (value instanceof PhpArray array) {
            return array;
        } else if (value instanceof PhpObject) {
            PhpArray array = new PhpArray();
            array.put("0", value);
            return array;
        }
        return null;
    }

    private List<Map<String, PhpValue>> extractRows(PhpArray tableData) {
        Map<Object, PhpValue> dataElements = tableData.getElements();
        boolean isRowCollection = isRowCollection(dataElements);
        List<Map<String, PhpValue>> rows = new ArrayList<>();

        if (!isRowCollection) {
            Map<String, PhpValue> row = new LinkedHashMap<>();
            for (Map.Entry<Object, PhpValue> entry : dataElements.entrySet()) {
                if (entry.getKey() instanceof String) {
                    row.put((String) entry.getKey(), entry.getValue());
                }
            }
            rows.add(row);
        } else {
            for (PhpValue rowValue : dataElements.values()) {
                PhpArray rowArray = toArray(rowValue);
                Map<String, PhpValue> row = new LinkedHashMap<>();
                for (Map.Entry<Object, PhpValue> entry : rowArray.getElements().entrySet()) {
                    if (entry.getKey() instanceof String) {
                        row.put((String) entry.getKey(), entry.getValue());
                    }
                }
                rows.add(row);
            }
        }
        return rows;
    }

    private static boolean isRowCollection(Map<Object, PhpValue> dataElements) {
        for (Object key : dataElements.keySet()) {
            if (!(key instanceof Number)) {
                return false;
            }
        }
        return true;
    }

    private String extractSqlValue(PhpValue value) {
        if (value == null) {
            return "NULL";
        }
        if (value instanceof PhpArray array) {
            if (array.getElements().size() == 1 && array.getElements().containsKey("0")) {
                return extractSqlValue(array.getElements().get("0"));
            } else {
                String arrayString = array.toString().replace("'", "''");
                return "'" + arrayString + "'";
            }
        }
        if (value instanceof PhpObject) {
            Object object = ((PhpObject) value).getValue();
            if (object == null) {
                return "NULL";
            } else if (object instanceof Boolean) {
                return (Boolean) object ? "TRUE" : "FALSE";
            } else if (object instanceof Number) {
                return object.toString();
            } else {
                String string = object.toString().replace("'", "''");
                return "'" + string + "'";
            }
        }
        String fallback = value.toString().replace("'", "''");
        return "'" + fallback + "'";
    }
}
