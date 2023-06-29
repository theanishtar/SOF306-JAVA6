package com.thean.session1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.thean.session1.bean.Student;

public class Lambda {
	
	@FunctionalInterface
	interface Demo4Inter{
		void m1(int x);
		default void m2() {
			System.out.println("Method 2");
		};
		public static void m3() {
			System.out.println("Method 3");
		};
	}
	
	
	static List<Student> list = Arrays.asList(
			new Student("Trần Hữu Đang",1,8.5),
			new Student("Trần Thị Mỹ Duyên",2,7.5),
			new Student("Nguyễn Thị Diễm Ngân",2,3.2),
			new Student("Lê Thị Ngọc Hân",4,4.8),
			new Student("Nguyễn Hoàng Hữu Phước",3,7),
			new Student("Hồ Phước Lộc",1,2.5),
			new Student("Phan Thị Thanh Thủy",2,1.5)
	);
	public static void main(String[] args) {
		System.out.println("Learn Lambda");
		//demo1();
		//demo2();
		//demo3();
		//demo4();
	}

	private static void demo4() {
		
		/*BASIC
		Demo4Inter obj = new Demo4Inter() {
			@Override
			public void m1(int x) {
				System.out.println(x);
			}
		};
		*/
		
		// LAMBDA
		Demo4Inter obj = x -> System.out.println(x);
		obj.m1(2023);
		obj.m2();
	}

	private static void demo3() {
		Collections.sort(list, (sv1, sv2) -> -sv1.getName().compareTo(sv2.getName()));
		//Collections.sort(list, (sv1, sv2) -> sv1.getName().compareTo(sv2.getName()));
		
		demo2();
	}

	private static void demo2() {
		
		
		list.forEach(sv -> {
			System.out.println(">> Name: " + sv.getName());
			String getGender = "Nam";
			if(sv.getGender() > 1)
				getGender = "Nữ";
			if(sv.getGender() > 2)
				getGender = "LGBT";
			System.out.println(">> Gender: " + getGender);
			System.out.println(">> Marks: " + sv.getMarks());
			System.out.println();
		});
	}

	private static void demo1() {
		List<Integer> list = Arrays.asList(1,4,0,2,7);
		list.forEach(n -> System.out.print(n + " "));
	}
}
