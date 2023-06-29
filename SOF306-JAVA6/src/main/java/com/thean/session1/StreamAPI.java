package com.thean.session1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.thean.session1.bean.Student;

public class StreamAPI {

	static List<Student> list = Arrays.asList(new Student("Trần Hữu Đang", 1, 8.5),
			new Student("Trần Thị Mỹ Duyên", 2, 7.5), new Student("Nguyễn Thị Diễm Ngân", 2, 3.2),
			new Student("Lê Thị Ngọc Hân", 4, 4.8), new Student("Nguyễn Hoàng Hữu Phước", 3, 7),
			new Student("Hồ Phước Lộc", 1, 2.5), new Student("Phan Thị Thanh Thủy", 2, 1.5));

	public static void main(String[] args) {
		// demo1();
		// demo2();
		// demo3();
		// demo4();
	}

	private static void demo4() {
		double average = list.stream()
				.mapToDouble(sv -> sv.getMarks())
				.average().getAsDouble();
		System.out.println("average marks: " + average);
		

		double minMarks = list.stream()
				.mapToDouble(sv -> sv.getMarks())
				.min().getAsDouble();
		System.out.println("min marks: " + minMarks);
		
		double sumMarks = list.stream()
				.mapToDouble(sv -> sv.getMarks())
				.sum();
		System.out.println("sum marks: "+minMarks);

		boolean allPassed = list.stream()
				.allMatch(sv -> sv.getMarks() >= 5);
		System.out.println("All Passed: " + allPassed);

		Student minSV = list.stream()
				.reduce(list.get(0), (min, sv) -> sv.getMarks() < min.getMarks() ? sv : min);
		System.out.println("Min SV: " + minSV.getName());
	}

	private static void demo3() {
		List<Student> result = list.stream().filter(sv -> sv.getMarks() >= 5)
				.peek(sv -> sv.setName(sv.getName().toUpperCase())).collect(Collectors.toList());
		result.forEach(sv -> {
			System.out.println(">> Name: " + sv.getName());
			String getGender = "Nam";
			if (sv.getGender() > 1)
				getGender = "Nữ";
			if (sv.getGender() > 2)
				getGender = "LGBT";
			System.out.println(">> Gender: " + getGender);
			System.out.println(">> Marks: " + sv.getMarks());
			System.out.println();
		});
	}

	private static void demo2() {
		List<Integer> list = Arrays.asList(1, 9, 4, 2, 5);
		List<Double> result = list.stream() // Strea<Integer>
				.filter(n -> n % 2 == 0) // Stream<Integer>
				.map(n -> Math.sqrt(n)) // Stream<Double>
				.peek(d -> System.out.println(d)) // Stream<Double>
				.collect(Collectors.toList()); // List<Double>
	}

	private static void demo1() {
		List<Integer> list = Arrays.asList(2, 5, 7, 1, 89, 2);
		// Stream<Integer> stream = list.stream();
		list.stream().forEach(n -> {
			System.out.print(n + " ");
		});
	}
}
