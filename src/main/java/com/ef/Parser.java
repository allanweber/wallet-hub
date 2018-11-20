package com.ef;

import com.ef.helper.Logger;
import com.ef.parser.service.ParserExecuterService;


public class Parser {

  public static void main(String[] args) {

    try {

      ParserExecuterService.execute(args);

    } catch (Exception ex) {
      Logger.error(ex);
    }
  }
}
