package com.ef.parser.database.repository;

import com.ef.parser.exception.LogParserException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ConnectionData {

  private static final String SERVER = "server";
  private static final String DATABASE = "database";
  private static final String USER = "user";
  private static final String PASSWORD = "password";

  private String server;
  private String database;
  private String user;
  private String password;

  public static ConnectionData getInstance() {
    return new ConnectionData();
  }

  Properties properties;

  private ConnectionData() {

    try {
      InputStream input = new FileInputStream("config.properties");
      properties = new Properties();
      properties.load(input);

      server = getProperty(SERVER);
      database = getProperty(DATABASE);
      user = getProperty(USER);
      password = getProperty(PASSWORD);

    } catch (IOException e) {
      throw new LogParserException("Error to find config.properties file", e);
    }
  }

  private String getProperty(String name) {
    return properties.getProperty(name);
  }
}
