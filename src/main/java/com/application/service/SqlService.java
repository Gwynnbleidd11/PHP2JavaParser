package com.application.service;

import com.application.parser.sql.SqlParser;
import org.springframework.stereotype.Service;

@Service
public class SqlService {

    private final SqlParser sqlParser;

    public SqlService(SqlParser sqlParser) {
        this.sqlParser = sqlParser;
    }

    public String parseToSqlString(final String inputString) {
        return null;
    }
}
