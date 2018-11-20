package com.ef.parser.dto;

import com.ef.dto.Arguments;
import com.ef.helper.Logger;
import com.ef.parser.helper.DateHelper;

import java.util.List;
import java.util.function.Function;

public class CriteriaLoader {

  private static final String startDate = "startDate";
  private static final String duration = "duration";
  private static final String threshold = "threshold";
  private static final String accesslog = "accesslog";
  private static final String defaultFile = "access.log";

  public static Function<List<Arguments>, Criteria> loadFromArguments() {
    return args -> {
      Logger.info("Preparing criteria");
      Arguments argument = getArgument(args, startDate);
      Criteria criteria = new Criteria();
      criteria.setStartDate(DateHelper.isValidString(argument.getValue()));

      argument = getArgument(args, duration);
      criteria.setDuration(DurationEnum.valueOf(argument.getValue()));

      argument = getArgument(args, threshold);
      criteria.setThreshold(Integer.parseInt(argument.getValue()));

      argument = getFileArgument(args);
      criteria.setLogFile(argument.getValue());

      return criteria;
    };
  }

  private static Arguments getArgument(List<Arguments> args, String name) {
    return args.stream()
        .filter(arg -> arg.getName().equals(name))
        .findFirst()
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    String.format("There is no argument called %s.", name)));
  }

  private static Arguments getFileArgument(List<Arguments> args) {
    return  args.stream()
            .filter(arg -> arg.getName().equals(accesslog))
            .findFirst()
            .orElse(new Arguments(accesslog, defaultFile));
  }
}
