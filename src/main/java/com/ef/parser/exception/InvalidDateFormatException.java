package com.ef.parser.exception;

public class InvalidDateFormatException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "A data %s possui formato inválido.";
  
    private static final long serialVersionUID = -1447437870797591301L;
  
    public InvalidDateFormatException (String value){
      super(String.format(DEFAULT_MESSAGE, value));
    }
  }
