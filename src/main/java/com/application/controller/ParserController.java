package com.application.controller;

import com.application.service.ParserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/php_parser")
public class ParserController {

    private final ParserService parserService;

    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> parseVarDumpString(@RequestBody String inputString) {
        return ResponseEntity.ok(parserService.processVarDumpString(inputString));
    }
}
