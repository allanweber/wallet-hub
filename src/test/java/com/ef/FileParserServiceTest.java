package com.ef;

import com.ef.dto.Arguments;
import com.ef.parser.service.FileParserService;
import com.ef.parser.dto.Criteria;
import com.ef.parser.dto.CriteriaLoader;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class FileParserServiceTest {

  @Test(expected = NullPointerException.class)
  public void shouldThrowExceptionArgumentsNull() {
    new FileParserService(null);
  }

  @Test
  public void shouldLoadAFile(){
    List<Arguments> args =
        Arrays.asList(
            new Arguments("startDate", "2017-01-01.14:00:00"),
            new Arguments("duration", "hourly"),
            new Arguments("threshold", "200"),
            new Arguments("logFile", "C:\\Git\\WalletHub\\Java_MySQL_Test\\access.log"));

    Criteria criteria = CriteriaLoader.loadFromArguments().apply(args);

    new FileParserService(criteria);
  }
}
