package com.example.GraphQLRProject;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.coxautodev.graphql.tools.SchemaParser;
import com.example.GraphQLRProject.core.Mutation;
import com.example.GraphQLRProject.core.Query;
import com.example.GraphQLRProject.util.DateScalar;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;

@ComponentScan(basePackageClasses = GraphQlrProjectApplication.class)
@SpringBootApplication(scanBasePackages = "com.example")
@EntityScan("com.example")
@EnableJpaRepositories("com.example")
@SuppressWarnings({"rawtypes", "unchecked"})
public class GraphQlrProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphQlrProjectApplication.class, args);
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Recife"));
	}
	
	@Bean
	public ServletRegistrationBean graphQLServlet() {
		return new ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(buildSchema()).build(),"/graphql");
	}

	private static GraphQLSchema buildSchema() {
		return SchemaParser
				.newParser()
				.file("schema.graphqls")
				.resolvers(new Query(), new Mutation())
				.scalars(DateScalar.DATE)
				.build()
				.makeExecutableSchema();
	}
}
