package com.study.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateTimeConverter implements Converter<String, Date>
{

    @Override
    public Date convert(String source)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try
        {
            date = df.parse(source);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

}