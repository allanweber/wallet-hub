package com.ef;

import static org.junit.Assert.assertEquals;
import java.util.List;
import com.ef.dto.Arguments;
import com.ef.dto.ArgumentsLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class ArgumentsLoaderTest {

  @Test
  public void shoulValidateArguments() {

    String[] commands = {
      "--startDate=2017-01-01.13:00:00",
      "--duration=hourly",
      "--threshold=100",
      "--accesslog=c:\\temp\\temp.log",
      "--arg2=123",
      "--arg3",
      "arg4"
    };
    List<Arguments> arguments = ArgumentsLoader.getArguments(commands);

    assertEquals(4, arguments.size());

    assertEquals("startDate", arguments.get(0).getName());
    assertEquals("2017-01-01.13:00:00", arguments.get(0).getValue());

    assertEquals("duration", arguments.get(1).getName());
    assertEquals("hourly", arguments.get(1).getValue());

    assertEquals("threshold", arguments.get(2).getName());
    assertEquals("100", arguments.get(2).getValue());

    assertEquals("accesslog", arguments.get(3).getName());
    assertEquals("c:\\temp\\temp.log", arguments.get(3).getValue());
  }

  @Test
  public void shouldReturnEmptyList() {
    String[] commands = {"--arg2=123", "--arg3", "arg4"};
    List<Arguments> arguments = ArgumentsLoader.getArguments(commands);

    assertEquals(0, arguments.size());
  }

  @Test
  public void shouldReturnEmptyListByEmpty() {
    String[] commands = {};
    List<Arguments> arguments = ArgumentsLoader.getArguments(commands);

    assertEquals(0, arguments.size());
  }

  @Test(expected = NullPointerException.class)
  public void shouldReturnEmptyListByNull() {
    List<Arguments> arguments = ArgumentsLoader.getArguments(null);
  }
}
