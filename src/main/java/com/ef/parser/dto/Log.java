package com.ef.parser.dto;

import com.ef.parser.helper.DateHelper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Log {

  private Date date;

  private String ip;

  private String request;

  private Integer status;

  private String userAgent;

  @Override
  public String toString() {
    return DateHelper.format(date) + "| " + ip + "| " + request + "| " + status + "| " + userAgent;
  }
}
