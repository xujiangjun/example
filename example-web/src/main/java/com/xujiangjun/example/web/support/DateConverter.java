package com.xujiangjun.example.web.support;

import com.xujiangjun.example.common.util.DateUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * String To Date Converter
 *
 * @author jiangjun.xu
 * @date 2017-04-25 14:13
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if(source == null || source.trim().length() == 0){
            return null;
        }
        String pattern = DateUtils.getPattern(source);
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return formatter.parseDateTime(source).toDate();
    }
}
