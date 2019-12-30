package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Ex1.Monom;

class MonomJTest {
	Monom x0 = new Monom("0");
	Monom x1 = new Monom("4");
	Monom x2 = new Monom("3x");
	Monom x3 = new Monom("7x^2");
	Monom x4 = new Monom("4x^1");
	Monom x5 = new Monom("12x^0");
	Monom x6 = new Monom("11.5x");
	
	String z0 = x0.toString();
	String z1 = x1.toString();
	String z2 = x2.toString();
	String z3 = x3.toString();
	String z4 = x4.toString();
	String z5 = x5.toString();
	String z6 = x6.toString();
	
	
	
	/*
	 * Constractor tester
	 */

	@Test
	public void conTest() {

		assertEquals(z0,"0");
		assertEquals(z1,"4.0");
		assertEquals(z2,"3.0x");
		assertEquals(z3,"7.0x^2");
		assertEquals(z4,"4.0x");
		assertEquals(z5,"12.0");
		assertEquals(z6,"11.5x");
		
	}
	/*
	 * Add tester
	 */
	@Test
	public void addTest() {
		
		
		x0.add(x0);
		x1.add(x1);
		x4.add(x0);
		x3.add(x3);
		x6.add(x6);
	
		
		z0=x0.toString();
		z1=x1.toString();
		z3=x3.toString();
		z4=x4.toString();
		z6=x6.toString();

		
		
		assertEquals(z0,"0");
		assertEquals(z1,"8.0");
		assertEquals(z3,"14.0x^2");
		assertEquals(z4,"4.0x");
		assertEquals(z6,"23.0x");
		
		
	}
	/*
	 * Multiply test
	 */
	
	@Test
	public void multTest() {
		Monom a0 = new Monom("2");
		Monom a1 = new Monom("2x");

		x0.multipy(x1);
		x1.multipy(x4);
		x4.multipy(a0);
		x3.multipy(a0);
		a1.multipy(a1);
		z0=x0.toString();
		z1=x1.toString();
		z3=x3.toString();
		z4=x4.toString();
		z6=a1.toString();
		
		assertEquals(z0,"0");
		assertEquals(z1,"16.0x");
		assertEquals(z3,"14.0x^2");
		assertEquals(z4,"8.0x");
		assertEquals(z6,"4.0x^2");
	}
	
	
	
	

}
