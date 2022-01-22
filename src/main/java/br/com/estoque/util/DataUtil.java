package br.com.estoque.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {

	public static final String FORMATO_DATA_XML = "yyyy-MM-dd";
	public static final String FORMATO_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String FORMATO_DATA_PADRAO_BR = "dd/MM/yyyy";
	public static final String FORMATO_DATA_ANGULAR = "dd/MM/yyyy";
	public static final String FORMATO_DATA_HORA_ANGULAR = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMATO_DATA_ANGULAR_HIFEN = "yyyy-MM-dd";
	public static final String FORMATO_DATA_YYYY_MM_DD_HIFEN_JSON = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
	public static final String FORMATO_DATA_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String FORMATO_DATA_YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";
	public static final String FORMATO_DATA_YYYY_MM_DD_HH_MM_SS_SSS = "yyyyMMddHHmmssSSS";
	public static final String FORMATO_DATA_YYYY_MM_DD = "yyyyMMdd";
	public static final String FORMATO_HORA_HH_MM_SS = "HH:mm:ss";
	public static final String FORMATO_HORA_HH_MM = "HH:mm";
	
	private DataUtil() {}
	
	
	public static Date maiorData(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	public static Date menorData(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static String obterDataFormatada(Date data, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(data);
	}
	
	public static Date obterDataFormatada(String data, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(data);
	}
}
