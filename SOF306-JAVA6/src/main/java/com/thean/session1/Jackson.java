package com.thean.session1;


import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson {
	public static void main(String[] args) throws IOException {
		//demo1();
		demo2();
	}

	private static void demo2() throws IOException {
		String jsonPath = "src//main//resources//students.json";
		
		ObjectMapper mapper = new ObjectMapper();
		//JsonNode student = mapper.readTree(json);
		JsonNode students = mapper.readTree(new File(jsonPath));
		
		students.iterator().forEachRemaining(sv -> {
			System.out.println(">> Name: "+sv.get("name").asText());
		});
	}

	private static void demo1() throws IOException {
		String json = "{\r\n"
				+ "\"name\":\"Nguyễn Văn Tèo\",\r\n"
				+ "\"gender\":true,\r\n"
				+ "\"marks\":7.5,\r\n"
				+ "\"subject\":[\"WEB205\",\"COM108\"],\r\n"
				+ "\"contact\":{\r\n"
				+ "\"email\":\"teonv@gmaul.com\",\r\n"
				+ "\"phone\":\"0123456789\"},\r\n"
				+"\"subjects\":[\"WEB205\",\"COM108\"]"
				+ "}";
		
		String jsonPath = "src//main//resources//student.json";
		
		ObjectMapper mapper = new ObjectMapper();
		//JsonNode student = mapper.readTree(json);
		JsonNode student = mapper.readTree(new File(jsonPath));
		
		System.out.println(">> Name: "+student.get("name").asText());
		System.out.println(">> Marks: "+student.get("marks").asDouble());
		System.out.println(">> Gender: "+student.get("gender").asBoolean());
		System.out.println(">> Email: "+student.get("contact").get("email").asText());
		System.out.println(">> Phone: "+student.findValue("phone").asText());
		student.get("subjects").iterator().forEachRemaining(subject -> {
			System.out.println(">> Subject: "+subject.asText());
		});
		
	}
}
