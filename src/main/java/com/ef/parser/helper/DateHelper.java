package com.ef.parser.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ef.parser.exception.InvalidDateFormatException;

public class DateHelper {

  public static Date isValidString(String dateValue) {

    dateValue = dateValue.replace("T", ".");
    dateValue = dateValue.replace("Z", " ");

    String[] allowFormats =
        new String[] {"yyyy-MM-dd.HH:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.sss"};
    for (String format : allowFormats) {
      try {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        formatter.setLenient(false);
        return formatter.parse(dateValue);
      } catch (ParseException ignored) {
      }
    }

    throw new InvalidDateFormatException(dateValue);
  }

  public static String format(Date value) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(value);
  }
}
