package com.tests;

import org.junit.Test;

import com.decorator.Util;

public class ShoppingCartFromFilesTest {

	@Test
	public void testFileEntry1() {
		Util.getFromFile("source/in1.txt");
	}
	
	@Test
	public void testFileEntry2() {
		Util.getFromFile("source/in2.txt");
	}
	
	@Test
	public void testFileEntry3() {
		Util.getFromFile("source/in3.txt");
	}

	@Test
	public void testFileEntry4() {
		Util.getFromFile("source/in4.txt");
	}
}
