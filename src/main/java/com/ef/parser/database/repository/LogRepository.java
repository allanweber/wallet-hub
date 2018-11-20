package com.ef.parser.database.repository;

import com.ef.helper.Logger;
import com.ef.parser.dto.Log;
import com.ef.parser.exception.LogParserException;
import com.ef.parser.helper.DateHelper;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LogRepository extends DataSource {

  public LogRepository() {
    super.getConnection();
  }

  public void insert(List<Log> logs) {

    Objects.requireNonNull(logs, "Log must not be null to insert.");
    if (logs.isEmpty()) throw new LogParserException("Must have logs in collection");

    PreparedStatement preparedStatement = null;
    try {

      for (Log log : logs) {
        preparedStatement =
            connection.prepareStatement(
                String.format(
                    "INSERT INTO log (date,ip,status,userAgent) VALUES ('%s','%s',%s,'%s')",
                    DateHelper.format(log.getDate()),
                    log.getIp(),
                    log.getStatus(),
                    log.getUserAgent()));
        preparedStatement.execute();
      }
    } catch (SQLException e) {
      Logger.error(e);
    } finally {
      try {
        if (preparedStatement != null) preparedStatement.close();
      } catch (SQLException e) {
        Logger.error(e);
      }
    }
  }
}
