package com.application.controller;

import com.application.service.SqlService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/sql_parser")
public class SqlController {

    private final SqlService service;

    public SqlController(SqlService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<String> parseToSqlString(@RequestBody String varDumpString) {
        return ResponseEntity.ok().build();
    }
}
