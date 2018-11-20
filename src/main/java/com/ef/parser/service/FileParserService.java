package com.ef.parser.service;

import com.ef.helper.Logger;
import com.ef.parser.dto.Criteria;
import com.ef.parser.dto.Log;
import com.ef.parser.dto.LogLoader;
import com.ef.parser.exception.LogParserException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileParserService {

  private Criteria criteria;

  public FileParserService(Criteria criteria) {
    this.criteria = Objects.requireNonNull(criteria, "Criteria must be informed.");
  }

  public List<Log> loadLog() {
    Logger.info("Loading logs");
    long startTime = System.currentTimeMillis();

    List<Log> logs =  loadFile().andThen(loadLines()).andThen(parseFile()).apply(criteria);

    long endTime = System.currentTimeMillis();
    Logger.info("%s logs read", logs.size());
    Logger.info("That took %s milliseconds", (endTime - startTime));

    return logs;
  }

  private Function<Criteria, List<String>> loadFile() {
    return where -> {
      List<String> lines;
      try {
        lines = Files.readAllLines(Paths.get(where.getLogFile()));
      } catch (IOException e) {
        throw new LogParserException("Erro to access the file.", e);
      }

      return lines;
    };
  }

  private Function<List<String>, List<String[]>> loadLines() {
    return line -> {
      List<String[]> result = new ArrayList<>();
      line.forEach(l -> result.add(l.split("\\|")));
      return result;
    };
  }

  private Function<List<String[]>, List<Log>> parseFile() {
    return filesLine ->
        filesLine.stream().map(parseline()).filter(notNull()).collect(Collectors.toList());
  }

  private Function<String[], Log> parseline() {
    return line -> LogLoader.load().apply(line);
  }

  private Predicate<Log> notNull() {
    return log -> !Objects.isNull(log);
  }
}
