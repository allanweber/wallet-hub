package com.ef.dto;

import com.ef.helper.Logger;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArgumentsLoader {

  private static List<String> allowedArgs =
      new ArrayList<>(Arrays.asList("startDate", "duration", "threshold", "accesslog"));

  public static List<Arguments> getArguments(String[] args) {
    Objects.requireNonNull(args, "Arguments must be informed.");
    Logger.info("Loading arguments");
    return Arrays.stream(args)
        .filter(onlyArgumets())
        .filter(onlyKeyValue())
        .map(mapToArguments())
        .filter(minimunArgs())
        .collect(toList());
  }

  private static Predicate<Arguments> minimunArgs() {
    return arg -> {
      if (allowedArgs.contains(arg.getName())) {
        return true;
      } else {
        Logger.error("The argument is not allowed: %s", arg.getName());
        return false;
      }
    };
  }

  private static Predicate<String> onlyArgumets() {
    return arg -> {
      if (arg.startsWith("--")) return true;
      else {
        Logger.error("The argument is invalid: %s", arg);
        return false;
      }
    };
  }

  private static Predicate<String> onlyKeyValue() {
    return arg -> {
      String[] parts = arg.split("=");
      if (parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) return true;
      else {
        Logger.error("The argument is invalid: %s", arg);
        return false;
      }
    };
  }

  private static Function<String, Arguments> mapToArguments() {
    return arg -> {
      String[] parts = arg.replace("--", "").split("=");
      return Arguments.builder().name(parts[0]).value(parts[1]).build();
    };
  }
}
