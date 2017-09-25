package com.shtm.manage.converter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class TimeConverter implements Converter<String, Timestamp> {

	@Override
	public Timestamp convert(String source) {
		
		if(source == null || source.isEmpty()){
			return new Timestamp(new Date().getTime());
		}
		
		Timestamp d = null;
		try {
			
			d = Timestamp.valueOf(source);
			
		} catch (Exception e) {
			e.printStackTrace();
			d = new Timestamp(new Date().getTime());
		}
		return d;
	}

}
