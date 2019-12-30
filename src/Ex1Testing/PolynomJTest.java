package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;

class PolynomJTest {
	
	Polynom x0 = new Polynom("0");
	Polynom x1 = new Polynom("2x");
	Polynom x2 = new Polynom("2x^2+x-3");
	Polynom x3 = new Polynom("14x^3+2x^5");
	Polynom x4 = new Polynom("14x^3-12x");
	

	
	
	/*
	 * toString test
	 */
	
	@Test
	void tostrTest() {
		
		assertEquals(x0.toString(),"0");
		assertEquals(x1.toString(),"2.0x");
		assertEquals(x2.toString(),"2.0x^2+1.0x-3.0");
		assertEquals(x3.toString(),"14.0x^3+2.0x^5");
		assertEquals(x4.toString(),"14.0x^3-12.0x");
	}
	
	@Test
	void fTest() {
		
		assertEquals(x1.f(2),4.0);
		assertEquals(x1.f(0),0.0);
		assertEquals(x4.f(1),2.0);
		assertEquals(x2.f(2),7.0);
		assertEquals(x1.f(5),10.0);
		
	}
	
	@Test
	void addTest() {
		Monom a1=new Monom("2");
		Monom a2=new Monom("2x");
		x0.add(a1);
		x1.add(x2);
		x4.add(a2);
		x2.add(x3);
		
		

		assertEquals(x0.toString(),"2.0");
		assertEquals(x1.toString(),"3.0x+2.0x^2-3.0");
		assertEquals(x2.toString(),"2.0x^2+1.0x-3.0+14.0x^3+2.0x^5");
		assertEquals(x4.toString(),"14.0x^3-10.0x");
		
	
	}
	
	@Test
	void subTest() {
		Polynom a0 = new Polynom ("2x^5");
		x2.substract(x0);
		x4.substract(x1);
		x3.substract(a0);
		
		assertEquals(x2.toString(),"2.0x^2+1.0x-3.0");
		assertEquals(x4.toString(),"14.0x^3-14.0x");
		assertEquals(x3.toString(),"14.0x^3+0");
		
	}
	
	@Test
	void multTest() {
		x1.multiply(x1);
		x0.multiply(x1);
		x4.multiply(x1);
		
		
		assertEquals(x1.toString(),"4.0x^2");
		assertEquals(x0.toString(),"0");
		assertEquals(x4.toString(),"56.0x^5-48.0x^3");
	}
	
	//@Test
	void eqTest() {
		Polynom a0 = new Polynom("1.0x-3.0+2.0x^2");
		Polynom a1 = new Polynom("2.0x^5+14.0x^3");
		Polynom a2 = new Polynom("14.0x^3-12.0x");
	
		assertTrue(x2.equals(a0));
		assertTrue(x3.equals(a1));
		assertTrue(x4.equals(a2));
		
	}
	
	@Test
	void derTest() {
		
		assertEquals(x0.derivative().toString(),"0");
		assertEquals(x1.derivative().toString(),"2.0");
		assertEquals(x2.derivative().toString(),"4.0x+1.0");
		assertEquals(x3.derivative().toString(),"42.0x^2+10.0x^4");
		assertEquals(x4.derivative().toString(),"42.0x^2-12.0");
		
	}
	
	@Test
	void areaTest() {
		
		assertEquals(x1.area(0,5,0.001),24.994999999998683);
		assertEquals(x2.area(0,5,0.001),82.64066799999054);
		assertEquals(x3.area(0,5,0.001),7391.833941665243);
		assertEquals(x4.area(0,5,0.001),2039.2265148409338);
		
	}
	
	@Test
	void rootTest() {
		Polynom x = new Polynom("x^2-4");
		Polynom y = new Polynom("x^2-16");
		Polynom z = new Polynom("0");
		
		assertEquals(x.root(0, 5, 0.01),2.0000000000000013);
		assertEquals(y.root(0, 5, 0.01),3.9999999999999587);
		assertEquals(z.root(0, 5, 0.01),0);
	}

}
