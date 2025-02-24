package com.application.parser.sql;

import com.application.parser.VarDumpParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SqlParserTest {

    @Test
    void shouldParseToSqlInsertQueryStringSingleKey() throws IOException {
        //Given
        String inputString = Files.readString(
                Path.of("src/test/resources/test_input_4_single_key"), StandardCharsets.UTF_8);
        String outputString = Files.readString(
                        Path.of("src/test/resources/test_sql_output_4_single_key"), StandardCharsets.UTF_8);
        SqlParser parser = new SqlParser(new VarDumpParser());

        //When
        String parsedToSql = parser.toSqlString(inputString);

        System.err.println(parsedToSql);
        System.err.println(outputString);

        //Then
        assertEquals(
                outputString.replaceAll("\\r\\n?", "\n"),
                parsedToSql.replaceAll("\\r\\n?", "\n")
        );
    }

    @Test
    void shouldParseToSqlInsertQueryStringMixedDataTypes() throws IOException {
        //Given
        String inputString = Files.readString(
                Path.of("src/test/resources/test_input_5_mixed_data_types"), StandardCharsets.UTF_8);
        String outputString = Files.readString(
                Path.of("src/test/resources/test_sql_output_5_mixed_data_types"), StandardCharsets.UTF_8);
        SqlParser parser = new SqlParser(new VarDumpParser());

        //When
        String parsedToSql = parser.toSqlString(inputString);

        System.err.println(parsedToSql);
        System.err.println(outputString);

        //Then
        assertEquals(
                outputString.replaceAll("\\r\\n?", "\n"),
                parsedToSql.replaceAll("\\r\\n?", "\n")
        );
    }

    @Test
    void shouldParseToSqlInsertQueryStringNumericIndexedArray() throws IOException {
        //Given
        String inputString = Files.readString(
                Path.of("src/test/resources/test_input_6_numeric_indexed_array"), StandardCharsets.UTF_8);
        String outputString = Files.readString(
                Path.of("src/test/resources/test_sql_output_6_numeric_indexed_array"), StandardCharsets.UTF_8);
        SqlParser parser = new SqlParser(new VarDumpParser());

        //When
        String parsedToSql = parser.toSqlString(inputString);

        System.err.println(parsedToSql);
        System.err.println(outputString);

        //Then
        assertEquals(
                outputString.replaceAll("\\r\\n?", "\n"),
                parsedToSql.replaceAll("\\r\\n?", "\n")
        );
    }

    @Test
    void shouldParseToSqlInsertQueryString() throws IOException {
        //Given
        String inputString = Files.readString(
                Path.of("src/test/resources/test_input_1"), StandardCharsets.UTF_8);
        String outputString = Files.readString(
                Path.of("src/test/resources/test_sql_output_1"), StandardCharsets.UTF_8);
        SqlParser parser = new SqlParser(new VarDumpParser());

        //When
        String parsedToSql = parser.toSqlString(inputString);

        System.err.println(parsedToSql);
        System.err.println(outputString);

        //Then
        assertEquals(
                outputString.replaceAll("\\r\\n?", "\n"),
                parsedToSql.replaceAll("\\r\\n?", "\n")
        );
    }
}