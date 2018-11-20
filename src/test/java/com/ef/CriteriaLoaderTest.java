package com.ef;

import com.ef.dto.Arguments;
import com.ef.parser.dto.Criteria;
import com.ef.parser.dto.CriteriaLoader;
import com.ef.parser.dto.DurationEnum;
import com.ef.parser.exception.InvalidDateFormatException;
import com.ef.parser.helper.DateHelper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class CriteriaLoaderTest {

  @Test
  public void shouldReturnNiceCriterias() {
    List<Arguments> args =
        Arrays.asList(
            new Arguments("startDate", "2017-01-01.14:00:00"),
            new Arguments("duration", "hourly"),
            new Arguments("threshold", "200"),
            new Arguments("accesslog", "c:\\temp\\temp.log"));

    Criteria criteria = CriteriaLoader.loadFromArguments().apply(args);

    assertEquals(DateHelper.isValidString("2017-01-01.14:00:00"), criteria.getStartDate());
    assertEquals(DurationEnum.hourly, criteria.getDuration());
    assertEquals(200, criteria.getThreshold().intValue());
    assertEquals("c:\\temp\\temp.log", criteria.getLogFile());
  }

  @Test
  public void shouldReturnNiceCriteriasWithoutLogFile() {
    List<Arguments> args =
        Arrays.asList(
            new Arguments("startDate", "2017-01-01.14:00:00"),
            new Arguments("duration", "hourly"),
            new Arguments("threshold", "200"));

    Criteria criteria = CriteriaLoader.loadFromArguments().apply(args);

    assertEquals(DateHelper.isValidString("2017-01-01.14:00:00"), criteria.getStartDate());
    assertEquals(DurationEnum.hourly, criteria.getDuration());
    assertEquals(200, criteria.getThreshold().intValue());
    assertEquals("access.log", criteria.getLogFile());
  }

  @Test(expected = InvalidDateFormatException.class)
  public void shouldThrowExceptionInvalidDate() {
    List<Arguments> args =
        Arrays.asList(
            new Arguments("startDate", "2017-01-01"),
            new Arguments("duration", "hourly"),
            new Arguments("threshold", "200"));

    CriteriaLoader.loadFromArguments().apply(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionInvalidDuration() {
    List<Arguments> args =
        Arrays.asList(
            new Arguments("startDate", "2017-01-01.14:00:00"),
            new Arguments("duration", "monthly"),
            new Arguments("threshold", "200"));

    CriteriaLoader.loadFromArguments().apply(args);
  }

  @Test(expected = NumberFormatException.class)
  public void shouldThrowExceptionInvalidThreshold() {
    List<Arguments> args =
        Arrays.asList(
            new Arguments("startDate", "2017-01-01.14:00:00"),
            new Arguments("duration", "hourly"),
            new Arguments("threshold", "xx"));

    CriteriaLoader.loadFromArguments().apply(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionArgStartDateNotFounded() {
    List<Arguments> args =
        Arrays.asList(new Arguments("duration", "hourly"), new Arguments("threshold", "xx"));

    CriteriaLoader.loadFromArguments().apply(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionArgDurationNotFounded() {
    List<Arguments> args =
        Arrays.asList(
            new Arguments("startDate", "2017-01-01.14:00:00"), new Arguments("threshold", "xx"));

    CriteriaLoader.loadFromArguments().apply(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionArgThresholdNotFounded() {
    List<Arguments> args =
        Arrays.asList(
            new Arguments("startDate", "2017-01-01.14:00:00"), new Arguments("duration", "hourly"));

    CriteriaLoader.loadFromArguments().apply(args);
  }
}
