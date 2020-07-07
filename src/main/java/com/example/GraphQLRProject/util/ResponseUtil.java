package com.example.GraphQLRProject.util;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {

	public static void write(HttpServletResponse servletResponse, String content) throws IOException {
		Writer out = servletResponse.getWriter();
		out.append(JSONProcessor.toJSON(content));
		out.flush();
	}
}
