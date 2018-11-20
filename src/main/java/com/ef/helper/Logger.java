package com.ef.helper;

public class Logger {

  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_BLUE = "\u001B[34m";

  private static final String INFO = ANSI_BLUE + "[INFO] - " + ANSI_RESET;
  private static final String ERROR = ANSI_RED + "[ERROR] - " + ANSI_RED;

  public static void info(String message, Object... args) {
    System.out.println(INFO + String.format(message, args));
  }

  public static void error(String message, Object... args) {
    System.out.println(ERROR + String.format(message, args));
  }

  public static void error(Throwable e) {
    error(e.getMessage());
  }

  public static void simple(Object log){
    System.out.println(log.toString());
  }
}
