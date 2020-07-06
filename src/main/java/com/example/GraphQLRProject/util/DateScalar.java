package com.example.GraphQLRProject.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

public class DateScalar {

	private static Coercing<Date, String> coercing = new Coercing<Date, String>() {

		@Override
		public String serialize(Object dataFetcherResult) {
			return serializeDate(dataFetcherResult);
		}

		@Override
		public Date parseValue(Object input) {
			return parseValueDate(input);// parseValueDate(input);
		}

		@Override
		public Date parseLiteral(Object input) {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar().coercing(coercing)
			.description("scalar para datas").name("Date").build();

	private static String serializeDate(Object data) {
		if (data.toString().length() == 10) {
			String day = data.toString().substring(8, 10);
			String month = data.toString().substring(5, 7);
			int year = Integer.parseInt(data.toString().substring(0, 4));

			return day + "/" + month + "/" + year;
		} else {
			String formatoFinal = "dd/MM/yyyy";

			Date original = (Date) data;
			SimpleDateFormat df = new SimpleDateFormat(formatoFinal);

			return df.format(original);
		}
	}

	private static Date parseValueDate(Object input) {
		String day = input.toString().substring(0, 2);
		String month = input.toString().substring(3, 5);
		int year = Integer.parseInt(input.toString().substring(6, 10));

		Calendar cal = new GregorianCalendar(year, Integer.parseInt(month) - 1, Integer.parseInt(day));
		return cal.getTime();
	}
}
