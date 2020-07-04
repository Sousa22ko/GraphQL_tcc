package com.example.GraphQLRProject.util;

import java.util.Date;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

public class DateScalar {

	private static Coercing<String, String> coercing = new Coercing<String, String>() {
		
		@Override
		public String serialize(Object dataFetcherResult) {
			return serializeDate(dataFetcherResult);
		}
		
		@Override
		public String parseValue(Object input) {
			return parseValueDate(input);
		}
		
		@Override
		public String parseLiteral(Object input) {
			// TODO Auto-generated method stub
			return null;
		}	
	};
	
	public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar().coercing(coercing).description("scalar para datas").name("Date").build();

	private static String serializeDate(Object data) {
		int year = Integer.parseInt(data.toString().substring(0, 4));	
		String month = data.toString().substring(5, 7);
		int day = Integer.parseInt(data.toString().substring(8, 10));

		return day + "/" + month + "/" + year;
	}

	private static String parseValueDate(Object input) {
		return "" + ((Date) input).getTime();
	}
}
