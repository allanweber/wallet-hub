package com.ef.parser.dto;

import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Criteria {

    private Date startDate;

    private Date endDate;

    private DurationEnum duration;

    private Integer threshold;

    private String logFile;

    public void calcEndDate(DurationEnum duration){
        if(startDate == null) startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        if(duration == DurationEnum.hourly){
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            calendar.add(Calendar.SECOND, -1);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.SECOND, -1);
        }

        endDate = calendar.getTime();
    }
}