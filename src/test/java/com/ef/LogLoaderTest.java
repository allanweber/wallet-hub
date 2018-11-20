package com.ef;

import com.ef.parser.dto.Log;
import com.ef.parser.dto.LogLoader;
import com.ef.parser.helper.DateHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import static junit.framework.TestCase.assertEquals;

@RunWith(PowerMockRunner.class)
public class LogLoaderTest {

  @Test
  public void shoulLoadLog() {
    String[] line =
        "2017-01-01 00:06:46.957|192.168.206.141|GET / HTTP/1.1|200|Mozilla".split("\\|");

    Log log = LogLoader.load().apply(line);

    assertEquals(DateHelper.isValidString("2017-01-01 00:06:46.957"), log.getDate());
    assertEquals("192.168.206.141", log.getIp());
    assertEquals("GET / HTTP/1.1", log.getRequest());
    assertEquals(200, log.getStatus().intValue());
    assertEquals("Mozilla", log.getUserAgent());
  }
}
