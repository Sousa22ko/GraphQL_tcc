package com.example.GraphQLRProject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.GraphQLRProject.util.DateScalar;

import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLConfiguration {
	
	@Bean
	public GraphQLScalarType jsonType() {
        return DateScalar.DATE;
    }

}
