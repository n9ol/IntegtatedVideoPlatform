package com.zzrenfeng.zhsx.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
		} catch (Exception e) {
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(source);
			} catch (ParseException e1) {
				try {
					return new SimpleDateFormat("HH:mm").parse(source);
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
			}
		}
		return null;
	}

}
