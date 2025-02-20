package com.application.service;

import com.application.parser.VarDumpParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ParserServiceTest {

    @Test
    void shouldProcessVarDumpString() throws IOException {
        //Given
        String inputString = Files.readString(Path.of("src/test/resources/test_input_1"),
                StandardCharsets.UTF_8);
        String outputString = Files.readString(Path.of("src/test/resources/test_output_1"),
                StandardCharsets.UTF_8);
        VarDumpParser parser = new VarDumpParser();
        ParserService service = new ParserService(parser);

        //When
        String testOutput = service.processVarDumpString(inputString);

        //Then
        assertEquals(
                outputString.replaceAll("\\r\\n?", "\n"),
                testOutput.replaceAll("\\r\\n?", "\n")
        );
    }
}