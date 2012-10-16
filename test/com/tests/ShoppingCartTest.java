package com.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.decorator.ItemParser;
import com.decorator.ShoppingCart;

public class ShoppingCartTest {

	@Test
	public void testInput() {
		ShoppingCart sc = new ShoppingCart();
		sc.put(ItemParser.parser("1 book at 12.49"), ItemParser.count("1 book at 12.49"));
		sc.put(ItemParser.parser("1 music CD at 14.99"), ItemParser.count("1 music CD at 14.99"));
		sc.put(ItemParser.parser("1 chocolate bar at 0.85"), ItemParser.count("1 chocolate bar at 0.85"));
		sc.printOrderInput();
		sc.printOrderResults();
		assertTrue (Math.abs((sc.getTaxtotal() - 1.50)) < 0.001);
		assertTrue (Math.abs((sc.getTotal() - 29.83)) < 0.001);
	}

	@Test
	public void testInputMultipleItems() {
		ShoppingCart sc = new ShoppingCart();
		sc.put(ItemParser.parser("2 book at 12.49"), ItemParser.count("2 book at 12.49"));
		sc.put(ItemParser.parser("2 music CD at 14.99"), ItemParser.count("2 music CD at 14.99"));
		sc.put(ItemParser.parser("2 chocolate bar at 0.85"), ItemParser.count("2 chocolate bar at 0.85"));
		sc.printOrderInput();
		sc.printOrderResults();
	}

	@Test
	public void testDoubleEntry() {
		ShoppingCart sc = new ShoppingCart();
		sc.put(ItemParser.parser("2 book at 10.00"), ItemParser.count("2 book at 10.00"));
		sc.put(ItemParser.parser("2 book at 100.00"), ItemParser.count("2 book at 100.00"));
		sc.printOrderInput();
		sc.printOrderResults();
		assertTrue(sc.getItems().size() == 2);
	}

	@Test
	public void testDoubEntry() {
		ShoppingCart sc = new ShoppingCart();
		sc.put(ItemParser.parser("2 book at 10.00"), ItemParser.count("2 book at 10.00"));
		sc.put(ItemParser.parser("2 book at 10.00"), ItemParser.count("2 book at 10.00"));
		sc.printOrderInput();
		sc.printOrderResults();
		assertTrue(sc.getItems().size() == 1);
	}
}
