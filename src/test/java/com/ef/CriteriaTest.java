package com.ef;

import com.ef.parser.dto.Criteria;
import com.ef.parser.dto.DurationEnum;
import com.ef.parser.helper.DateHelper;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class CriteriaTest {

    @Test
    public void shouldAddOneHour(){

        Date date = DateHelper.isValidString("2018-11-18 15:28:31");
        Criteria criteria = new Criteria();
        criteria.setStartDate(date);
        criteria.calcEndDate(DurationEnum.hourly);
        assertEquals( DateHelper.isValidString("2018-11-18 16:28:30"), criteria.getEndDate());
    }

    @Test
    public void shouldAddOneDay(){

        Date date = DateHelper.isValidString("2018-11-18 15:28:31");
        Criteria criteria = new Criteria();
        criteria.setStartDate(date);
        criteria.calcEndDate(DurationEnum.daily);
        assertEquals( DateHelper.isValidString("2018-11-19 15:28:30"), criteria.getEndDate());
    }
}
