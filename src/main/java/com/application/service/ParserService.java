package com.application.service;

import com.application.parser.PhpValue;
import com.application.parser.VarDumpParser;
import org.springframework.stereotype.Service;

@Service
public class ParserService {

    private final VarDumpParser parser;

    public ParserService(VarDumpParser parser) {
        this.parser = parser;
    }

    public String processVarDumpString(final String inputString) {
        PhpValue parsedArray = parser.parseVarDump(inputString);
        return parser.toPhpCode(parsedArray);
    }
}
