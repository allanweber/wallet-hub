package com.ef.parser.service;

import com.ef.helper.Logger;
import com.ef.parser.database.repository.LogRepository;
import com.ef.parser.dto.Criteria;
import com.ef.parser.dto.Log;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class LogService {

  private List<Log> logs;

  public LogService(List<Log> logs) {
    this.logs = Objects.requireNonNull(logs, "Logs must be informed.");
  }

  public List<Log> applyCriteria(Criteria criteria) {
    Objects.requireNonNull(criteria, "Criteria must be informed.");
    criteria.calcEndDate(criteria.getDuration());

    Logger.info("Apply log criteria");
    long startTime = System.currentTimeMillis();

    List<Log> filtered =
        logs.stream()
            .filter(filterByCriteria(criteria))
            .collect(groupingBy(Log::getIp))
            .entrySet()
            .stream()
            .filter(x -> x.getValue().size() >= criteria.getThreshold())
            .map(Map.Entry::getValue)
            .flatMap(List::stream)
            .collect(toList());

    long endTime = System.currentTimeMillis();
    Logger.info("%s logs filtered", logs.size());
    Logger.info("That took %s milliseconds", (endTime - startTime));

    return filtered;
  }

  public void print(List<Log> logs) {
    logs.forEach(Logger::simple);
  }

  public void save(List<Log> logs) {
    Logger.info("Inserting into database");
    LogRepository repository = new LogRepository();
    repository.insert(logs);
  }

  private Predicate<Log> filterByCriteria(Criteria criteria) {
    return log ->
        (log.getDate().equals(criteria.getStartDate())
                || log.getDate().after(criteria.getStartDate()))
            && (log.getDate().equals(criteria.getEndDate())
                || log.getDate().before(criteria.getEndDate()));
  }
}
