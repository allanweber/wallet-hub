package com.ef.parser.service;

import com.ef.dto.Arguments;
import com.ef.dto.ArgumentsLoader;
import com.ef.helper.Logger;
import com.ef.parser.dto.Criteria;
import com.ef.parser.dto.CriteriaLoader;
import com.ef.parser.dto.Log;
import java.util.List;

public class ParserExecuterService {

  public static void execute(String[] args) {

    List<Arguments> arguments = ArgumentsLoader.getArguments(args);

    Criteria criteria = CriteriaLoader.loadFromArguments().apply(arguments);

    List<Log> logs = new FileParserService(criteria).loadLog();

    LogService logService = new LogService(logs);
    logs = logService.applyCriteria(criteria);
    logService.print(logs);
    logService.save(logs);

    Logger.info("End process");
  }
}
