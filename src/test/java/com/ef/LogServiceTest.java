package com.ef;

import com.ef.parser.dto.Criteria;
import com.ef.parser.dto.DurationEnum;
import com.ef.parser.dto.Log;
import com.ef.parser.helper.DateHelper;
import com.ef.parser.service.LogService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class LogServiceTest {

  @Test(expected = NullPointerException.class)
  public void shouldExceptionLogsNull() {
    new LogService(null);
  }

  @Test(expected = NullPointerException.class)
  public void shouldExceptionCriteriaNull() {
    new LogService(new ArrayList<>()).applyCriteria(null);
  }

  @Test
  public void shouldReturnOneIp() {

    List<Log> logs =
        Arrays.asList(
            new Log(
                DateHelper.isValidString("2018-11-19 20:00:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:01:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:02:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:03:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:04:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:05:00"), "192.168.1.2", "GET", 200, "xyz"));

    Criteria criteria = new Criteria();
    criteria.setStartDate(DateHelper.isValidString("2018-11-19 20:00:00"));
    criteria.setDuration(DurationEnum.hourly);
    criteria.setThreshold(4);

    LogService service = new LogService(logs);
    List<Log> result = service.applyCriteria(criteria);

    assertEquals(4, result.size());

    String ip =
        result
            .stream()
            .collect(Collectors.groupingBy(Log::getIp))
            .entrySet()
            .stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.joining());

    assertEquals("192.168.1.1", ip);
  }

  @Test
  public void shouldReturnTwoIp() {

    List<Log> logs =
        Arrays.asList(
            new Log(
                DateHelper.isValidString("2018-11-19 20:00:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:01:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:02:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:03:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:04:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:05:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:06:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:07:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:04:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:05:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:06:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:07:00"), "192.168.1.3", "GET", 200, "xyz"));

    Criteria criteria = new Criteria();
    criteria.setStartDate(DateHelper.isValidString("2018-11-19 20:00:00"));
    criteria.setDuration(DurationEnum.hourly);
    criteria.setThreshold(4);

    LogService service = new LogService(logs);
    List<Log> result = service.applyCriteria(criteria);

    assertEquals(8, result.size());

    List<String> ips =
        result
            .stream()
            .collect(Collectors.groupingBy(Log::getIp))
            .entrySet()
            .stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

    assertEquals(2, ips.size());
    assertEquals("192.168.1.1", ips.get(0));
    assertEquals("192.168.1.2", ips.get(1));
  }

  @Test
  public void shouldReturnNoneIpBecouseOfThreshold() {

    List<Log> logs =
        Arrays.asList(
            new Log(
                DateHelper.isValidString("2018-11-19 20:00:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:01:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:02:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:03:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:04:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:05:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:06:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:07:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:04:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:05:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:06:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:07:00"), "192.168.1.3", "GET", 200, "xyz"));

    Criteria criteria = new Criteria();
    criteria.setStartDate(DateHelper.isValidString("2018-11-19 20:00:00"));
    criteria.setDuration(DurationEnum.hourly);
    criteria.setThreshold(5);

    LogService service = new LogService(logs);
    List<Log> result = service.applyCriteria(criteria);

    assertEquals(0, result.size());
  }

  @Test
  public void shouldReturnNoneIpBecouseOfDate() {

    List<Log> logs =
        Arrays.asList(
            new Log(
                DateHelper.isValidString("2018-11-19 20:00:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:01:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:02:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:03:00"), "192.168.1.1", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:04:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:05:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:06:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 20:07:00"), "192.168.1.2", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:04:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:05:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:06:00"), "192.168.1.3", "GET", 200, "xyz"),
            new Log(
                DateHelper.isValidString("2018-11-19 21:07:00"), "192.168.1.3", "GET", 200, "xyz"));

    Criteria criteria = new Criteria();
    criteria.setStartDate(DateHelper.isValidString("2018-11-19 22:00:00"));
    criteria.setDuration(DurationEnum.hourly);
    criteria.setThreshold(2);

    LogService service = new LogService(logs);
    List<Log> result = service.applyCriteria(criteria);

    assertEquals(0, result.size());
  }
}
