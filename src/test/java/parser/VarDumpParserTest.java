package parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
class VarDumpParserTest {

    @Test
    void shouldParsePHPOutputToPHPCode() throws IOException {
        //Given
        String inputString = Files.readString(Path.of("src/test/resources/test_input_1"),
                StandardCharsets.UTF_8);
        String outputString = Files.readString(Path.of("src/test/resources/test_output_1"),
                StandardCharsets.UTF_8);
        VarDumpParser parser = new VarDumpParser();

        //When
        PHPValue parsedArray = parser.parseVarDump(inputString);
        String phpCode = parser.toPHPCode(parsedArray);

        //Then
        assertEquals(outputString, phpCode);
    }

    @Test
    void shouldParseSingleLineComplexString() throws IOException {
        //Given
        String inputString = Files.readString(Path.of("src/test/resources/test_input_2_single_line_complex"),
                StandardCharsets.UTF_8);
        String outputString = Files.readString(Path.of("src/test/resources/test_output_2_single_line_complex"),
                StandardCharsets.UTF_8);
        VarDumpParser parser = new VarDumpParser();

        //When
        PHPValue parsedArray = parser.parseVarDump(inputString);
        String phpCode = parser.toPHPCode(parsedArray);

        //Then
        assertEquals(outputString, phpCode);
    }

    @Test
    void shouldParseEmptyArray() throws IOException {
        //Given
        String inputString = Files.readString(Path.of("src/test/resources/test_input_3_empty_array"),
                StandardCharsets.UTF_8);
        String outputString = Files.readString(Path.of("src/test/resources/test_output_3_empty_array"),
                StandardCharsets.UTF_8);
        VarDumpParser parser = new VarDumpParser();

        //When
        PHPValue parsedArray = parser.parseVarDump(inputString);
        String phpCode = parser.toPHPCode(parsedArray);

        //Then
        assertEquals(outputString, phpCode);
    }

    @Test
    void shouldParseSingleKey() throws IOException {
        //Given
        String inputString = Files.readString(Path.of("src/test/resources/test_input_4_single_key"),
                StandardCharsets.UTF_8);
        String outputString = Files.readString(Path.of("src/test/resources/test_output_4_single_key"),
                StandardCharsets.UTF_8);
        VarDumpParser parser = new VarDumpParser();

        //When
        PHPValue parsedArray = parser.parseVarDump(inputString);
        String phpCode = parser.toPHPCode(parsedArray);

        //Then
        assertEquals(phpCode, outputString);
    }

    @Test
    void shouldParseArrayWithMixedDataTypes() throws IOException {
        //Given
        String inputString = Files.readString(Path.of("src/test/resources/test_input_5_mixed_data_types"),
                StandardCharsets.UTF_8);
        String outputString = Files.readString(Path.of("src/test/resources/test_output_5_mixed_data_types"),
                StandardCharsets.UTF_8);
        VarDumpParser parser = new VarDumpParser();

        //When
        PHPValue parsedArray = parser.parseVarDump(inputString);
        String phpCode = parser.toPHPCode(parsedArray);

        //Then
        assertEquals(outputString, phpCode);
    }

    @Test
    void shouldParseArrayWithNumericIndexes() throws IOException {
        //Given
        String inputString = Files.readString(Path.of("src/test/resources/test_input_6_numeric_indexed_array"),
                StandardCharsets.UTF_8);
        String outputString = Files.readString(Path.of("src/test/resources/test_output_6_numeric_indexed_array"),
                StandardCharsets.UTF_8);
        VarDumpParser parser = new VarDumpParser();

        //When
        PHPValue parsedArray = parser.parseVarDump(inputString);
        String phpCode = parser.toPHPCode(parsedArray);

        //Then
        assertEquals(outputString, phpCode);
    }

    @Test
    void shouldParseSingleLine() throws IOException {
        //Given
        String inputString = Files.readString(Path.of("src/test/resources/test_input_7_single_line_simple"),
                StandardCharsets.UTF_8);
        String outputString = Files.readString(Path.of("src/test/resources/test_output_7_single_line_simple"),
                StandardCharsets.UTF_8);
        VarDumpParser parser = new VarDumpParser();

        //When
        PHPValue parsedArray = parser.parseVarDump(inputString);
        String phpCode = parser.toPHPCode(parsedArray);

        //Then
        assertEquals(outputString, phpCode);
    }
}