package com.shtm.manage.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			try {
				//2017-09-15 10:18:09
				d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
			} catch (ParseException e1) {
				e.printStackTrace();
				try {
					d = new SimpleDateFormat("yyyy-MM-dd").parse(source);
				} catch (ParseException e2) {
					e.printStackTrace();
				}
			}
		}
		return d;
	}

}
