package com.thean.session1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thean.session1.bean.Contact;
import com.thean.session1.bean.Student2;

public class Jackson2 {
	public static void main(String[] args) throws IOException {
		// demo1();
		// demo2();
		// demo3();
		// demo4();
		// demo5();
		// demo6();
		demo7();
	}

	private static void demo7() throws IOException {
		Contact contact = new Contact("teonv@gmail.com", "0123456789", null);
		List<String> subject = Arrays.asList("WEB205", "COM108");
		Student2 student = new Student2("Nguyễn Văn Tèo", true, 7.5, contact, subject);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(student);
		System.out.println(json);
		mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, student);
		mapper.writeValue(new File("src/main/resources/result3.json"), student);
	}

	private static void demo6() throws IOException {
		Map<String, Object> contact = new HashMap<String, Object>();
		contact.put("email", "teonv@gmail.com");
		contact.put("phone", "0123456789");

		List<String> subjects = Arrays.asList("WEB205", "COM108");

		Map<String, Object> student = new HashMap<String, Object>();
		student.put("name", "Nguyễn Văn Tèo");
		student.put("marks", 7.5);
		student.put("gender", true);
		student.put("contact", contact);
		student.put("subjects", subjects);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(student);
		System.out.println(json);
		mapper.writeValue(System.out, student);
		mapper.writeValue(new File("src/main/resources/result2.json"), student);
	}

	private static void demo5() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode student = mapper.createObjectNode();
		student.put("name", "Nguyễn Văn Tèo");
		student.put("marks", 7.5);
		student.put("gender", true);
		ObjectNode contact = student.putObject("contact");
		contact.put("email", "teonv@gmail.com");
		contact.put("phone", "0123456789");
		ArrayNode subjects = student.putArray("subjects");
		subjects.add("WEB205");
		subjects.add("COM108");

		// Write to String
		String json = mapper.writeValueAsString(student);

		// Write to ouput
		mapper.writeValue(System.out, student);

		// Write to file
		mapper.writeValue(new File("src/main/resources/result.json"), student);
	}

	private static void demo4() throws StreamReadException, DatabindException, IOException {
		String path = "src//main//resources//students.json";
		TypeReference<List<Student2>> type = new TypeReference<List<Student2>>() {
		};
		ObjectMapper mapper = new ObjectMapper();
		List<Student2> students = mapper.readValue(new File(path), type);
		students.forEach(student -> {
			System.out.println(">> Name: " + student.getName());
		});
	}

	private static void demo3() throws StreamReadException, DatabindException, IOException {
		String path = "src//main//resources//student.json";
		ObjectMapper mapper = new ObjectMapper();
		Student2 student = mapper.readValue(new File(path), Student2.class);

		System.out.println(">> Name : " + student.getName());
		System.out.println(">> Marks : " + student.getMarks());
		System.out.println(">> Gender : " + student.getGender());
		Contact contact = student.getContact();
		System.out.println(">> Email : " + contact.getEmail());
		System.out.println(">> Phone : " + contact.getPhone());
		List<String> subjects = student.getSubjects();
		subjects.forEach(subject -> {
			System.out.println(">> Subject : " + subject);
		});
	}

	private static void demo2() throws IOException {
		String jsonPath = "src//main//resources//students.json";

		ObjectMapper mapper = new ObjectMapper();
		// JsonNode student = mapper.readTree(json);
		List<Map<String, Object>> students = mapper.readValue(new File(jsonPath), List.class);

		students.forEach(sv -> {
			System.out.println(">> Name: " + sv.get("name"));
		});
	}

	private static void demo1() throws IOException {
		String jsonPath = "src//main//resources//student.json";

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> student = mapper.readValue(new File(jsonPath), Map.class);

		System.out.println(">> Name: " + student.get("name"));
		System.out.println(">> Marks: " + student.get("marks"));
		System.out.println(">> Gender: " + student.get("gender"));
		Map<String, Object> contact = (Map<String, Object>) student.get("contact");
		System.out.println(">> Email: " + contact.get("email"));
		System.out.println(">> Phone: " + contact.get("phone"));
		List<String> subjects = (List<String>) student.get("subjects");

		subjects.forEach(subject -> {
			System.out.println(">> Subject: " + subject);
		});
	}
}
