package com.example.GraphQLRProject.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.GraphQLRProject.util.DateScalar;

import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLScalarConfiguration {

	@Bean
	public GraphQLScalarType jsonType() {
		return DateScalar.DATE;
	}
}
