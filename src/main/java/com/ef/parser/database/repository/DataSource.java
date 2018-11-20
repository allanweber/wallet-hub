package com.ef.parser.database.repository;

import com.ef.helper.Logger;
import com.ef.parser.exception.LogParserException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataSource {

  protected Connection connection = null;

  protected Connection getConnection() {

    ConnectionData conn = ConnectionData.getInstance();
    String connectionString =
        String.format("jdbc:mysql://%s/%s", conn.getServer(), conn.getDatabase());

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      Logger.error(e);
    }
    try {
      connection =
          DriverManager.getConnection(connectionString, conn.getUser(), conn.getPassword());
      if (connection == null) {
        throw new LogParserException("Error to create mysql connection");
      }
      return connection;
    } catch (SQLException e) {
      throw new LogParserException(e.getMessage());
    }
  }
}
