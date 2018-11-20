package com.ef.parser.dto;

import com.ef.helper.Logger;
import com.ef.parser.helper.DateHelper;
import java.util.function.Function;

public class LogLoader {

  public static Function<String[], Log> load() {
    return line -> {
      try {

        return new Log(
            DateHelper.isValidString(line[0]),
            line[1],
            line[2].replace("\"", ""),
            Integer.parseInt(line[3]),
            line[4].replace("\"", ""));

      } catch (Exception ex) {
        Logger.error( "Error to parse variable for the line %s: %s", line, ex.getMessage());
        return null;
      }
    };
  }
}
