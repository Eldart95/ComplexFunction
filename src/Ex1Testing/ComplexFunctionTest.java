package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {
	
	
	

		
	/**
	 * Test for constructors and toString method.
	 */

	@Test
	void constructorsTest() {
		function a = new Polynom("3x+2");
		function b = new Polynom("4x^2");
		function f1 = new ComplexFunction("plus",a,b);
		assertEquals(f1.toString(),"Plus(3.0x+2.0,4.0x^2)");
		
		function c = new Polynom("2");
		function f2 = new ComplexFunction("min",f1,c);
		assertEquals(f2.toString(),"Min(Plus(3.0x+2.0,4.0x^2),2.0)");
		
		function d = new Polynom("2x");
		function e = new Polynom("5x^3+4x^2+4x");
		function f3 = new ComplexFunction("MuL",d,e);
		assertEquals(f3.toString(),"Times(2.0x,5.0x^3+4.0x^2+4.0x)");
		
		function f = new Polynom("4x^3+5x^2-2x");
		function g = new Polynom("-4x^2+2x");
		function f4 = new ComplexFunction("cOmP",f,g);
		assertEquals(f4.toString(),"Comp(4.0x^3+5.0x^2-2.0x,-4.0x^2+2.0x)");
		
		function f5 = new ComplexFunction("DIVID",f3,f4);
		assertEquals(f5.toString(),"Divid(Times(2.0x,5.0x^3+4.0x^2+4.0x),Comp(4.0x^3+5.0x^2-2.0x,-4.0x^2+2.0x))");
		
		
		ComplexFunction f6 = new ComplexFunction("Plus(3x,3x)");
		ComplexFunction f7 = new ComplexFunction("divid(3x^2,3x-2)");
		ComplexFunction f8 = new ComplexFunction(Operation.Max,f6,f7);
		
		
		assertEquals(f6.toString(),"Plus(3.0x,3.0x)");
		assertEquals(f7.toString(),"Divid(3.0x^2,3.0x-2.0)");
		assertEquals(f8.toString(),"Max(Plus(3.0x,3.0x),Divid(3.0x^2,3.0x-2.0))");
		
		
		ComplexFunction f10 = new ComplexFunction("comp(mul(plus(3x,3x),2x),plus(3x,3x))");
		ComplexFunction f11 = new ComplexFunction("min(max(plus(3x,3x),2x),divid(3x,3x))");
		ComplexFunction f12 = new ComplexFunction("plus(plus(3x,3x),plus(3x,3x))");
		
		
		assertEquals(f10.toString(),"Comp(Times(Plus(3.0x,3.0x),2.0x),Plus(3.0x,3.0x))");
		assertEquals(f11.toString(),"Min(Max(Plus(3.0x,3.0x),2.0x),Divid(3.0x,3.0x))");
		assertEquals(f12.toString(),"Plus(Plus(3.0x,3.0x),Plus(3.0x,3.0x))");
	
	}
	/**
	 * test for all arithmetic methods, the methods implemented in exactly the same way
	 * if one works than all are.
	 */
//	@Test
	void operationTest() {
		function a = new Polynom("3x");
		function b = new Polynom("2x");
		ComplexFunction f1 = new ComplexFunction("max",a,b);
		f1.plus(a);
		assertEquals(f1.toString(),"Plus(Max(3.0x,2.0x),3.0x)");
		
		function c = new Polynom("4x^4+2x^2");
		function d = new Polynom("2x^3+4x+4");
		ComplexFunction f2 = new ComplexFunction("comp",c,d);
		f2.min(b);
		assertEquals(f2.toString(),"Min(Comp(4.0x^4+2.0x^2,2.0x^3+4.0x+4.0),2.0x)");
		
		function e = new Polynom("12x^2");
		ComplexFunction f3 = new ComplexFunction(e);
		f3.div(e);
		assertEquals(f3.toString(),"Divid(12.0x^2,12.0x^2)");
	}
	
	@Test
	void fxTest() {
		function a = new Polynom("3x");
		function b = new Polynom("2x");
		ComplexFunction f1 = new ComplexFunction("plus",a,b);
		assertEquals(f1.f(5),25);
		
		function c = new Polynom("4x^2");
		function d = new Polynom("2x+5x^3+15");
		ComplexFunction f2 = new ComplexFunction("mul",c,d);	
		assertEquals(f2.f(5),65000);

		ComplexFunction f3 = new ComplexFunction("comp",a,b);
		assertEquals(f3.f(5),30);
		
		function e = new Polynom("4x^2");
		function f = new Polynom("2x+5x^3+15");
		ComplexFunction f4 = new ComplexFunction("divid",e,f);	
		assertEquals(f4.f(5),0.15384615384615385);
		
		
	}
	@Test
	void equalsTest() {
		function a = new Polynom("3x+2");
		function b = new Polynom("4x^2");
		ComplexFunction f1 = new ComplexFunction(Operation.Plus,a,b);
		
		function c = new Polynom("4x^2");
		function d = new Polynom("3x+2");
		ComplexFunction f2 = new ComplexFunction(Operation.Plus,c,d);
		
		assertEquals(f1.equals(f2),true);
		
		Polynom e = new Polynom("x");
		function f = new Polynom("x");
		ComplexFunction f3 = new ComplexFunction(Operation.Times,e,f);
		
		Monom g = new Monom("1");
		function h = new Polynom("x^2");
		ComplexFunction f4 = new ComplexFunction(Operation.Times,g,h);
		
		assertEquals(f3.equals(f4),true);
		
		function i = new Polynom("x");
		function j = new Polynom("x");
		ComplexFunction f5 = new ComplexFunction(Operation.Plus,i,j);
		
		function k = new Polynom("1");
		function l = new Polynom("x^2");
		ComplexFunction f6 = new ComplexFunction(Operation.Times,k,l);
		
		assertEquals(f5.equals(f6),false);
	}
	
	@Test
	void initTest() {
		function a = new ComplexFunction();
	
		
		function b = a.initFromString("plus(plus(comp(3x,3x),2x),4x^2)");
		function c = a.initFromString("plus(divid(5x^2+3,2x),mul(4x^4,x))");
		function d = new ComplexFunction("max(2x,-2x)");
		
		assertEquals(b.toString(), "Plus(Plus(Comp(3.0x,3.0x),2.0x),4.0x^2)");
		assertEquals(c.toString(), "Plus(Divid(5.0x^2+3.0,2.0x),Times(4.0x^4,1.0x))");
		assertEquals(d.toString(), "Max(2.0x,-2.0x)");
		
	}

	
}
