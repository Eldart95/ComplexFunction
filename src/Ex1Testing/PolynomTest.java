package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

/**
 * This class represents a simple (naive) tester for the Polynom class, 
 * Note:                                              <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Polynom class.  <br>
 * (iii) Expected output:                             <br>
 * *****  Test3:  *****                               <br>
0) x^2+5x   	isZero: false	 f(0) = 0.0           <br>
1) x^3-1.0x+5  	isZero: false	 f(1) = 5.0           <br>
2) -0.5x^2+3x^5	isZero: false	 f(2) = 94.0          <br>
3) 0         	isZero: true	 f(3) = 0.0           <br>
*****  Test4:  *****                                  <br>
0) x^2          	Der: 2x         	eq: true      <br>
1) 4x^2-2x-5    	Der: 8x-2       	eq: true      <br>
2) -1.3x^3+2x    	Der: -3.9x^2+2  	eq: true      <br>
3) 0             	Der: 0  	        eq: true      <br>
***** Test5: *****                                    <br>
0) x^2-4      area(1,5,0.001):~25   root(1,4,0.001):2 <br>
1) x^2-2x+1	  area(1,5,0.001):~22	root(1,4,0.001):1 <br>
2) x^4-16	  area(1,5,0.001):~57	root(1,4,0.001):2 <br>
3) 0	      area(1,5,0.001):0	    root(1,4,0.001):0 <br>
***** Eror Testing *****                              <br>
0) 3a      	toString:Illegal char                     <br>
1) -x	                            root:No roots     <br>    
2) -x^2       area:No area                            <br>	
 */




public class PolynomTest {
	public static void main(String[] args) {
	//	test1();
		test2();
	//	test3();
	//	test4();
	//	test5();
	//	erorTesting();
		
	
		
	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		System.out.println(p1);
		double aa = p1.area(0, 2, 0.0001);
		System.out.println(aa);
		//p1.substract(p1);
		//System.out.println(p1);
		}
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		System.out.println(s1);
		
	}
	
	private static void test3() {
		System.out.println("*****  Test3:  *****");
		String[] monoms = {"x^2+5x", "x^3-x+5","-0.5x^2+3x^5","0"}; 
		for(int i=0;i<monoms.length;i++) {
			Polynom m = new Polynom(monoms[i]);
			String s = m.toString();
			m = new Polynom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	
	private static void test4() {
		System.out.println("*****  Test4:  *****");
		String[] monoms = {"x^2", "4x^2-2x-5","-1.3x^3","0"}; 
		for(int i=0;i<monoms.length;i++) {
			Polynom m = new Polynom(monoms[i]);
			String s = m.toString();
			
			
			Polynom m1 = new Polynom(s);
			
			boolean e = m.equals(m1);
			
			Polynom_able x = m.derivative();
			
			System.out.println(i+") "+m +"    \tDer: "+x+"  \teq: "+e);
		}
	}
	
	private static void test5() {
		System.out.println("*****  Test5:  *****");
		String[] monoms = {"x^2-4", "x^2-2x+1","x^4-16","0"}; 
		for(int i=0;i<monoms.length;i++) {
			Polynom m = new Polynom(monoms[i]);
			String s = m.toString();
			
			Polynom m1 = new Polynom(s);
			double a = m1.area(1, 5, 0.001);
			double r = m1.root(1, 4, 0.001);
			System.out.println(i+") "+s +"    \tarea: "+a+"  \troot: "+r);
		}
	}
	/**
	 * eror tester.
	 * enable manually
	 */
	private static void erorTesting() {
		System.out.println("****** erorTesting: *****");
	//	Polynom x = new Polynom("3a");
	//	Polynom y = new Polynom("-x");
		Polynom z = new Polynom("-x^2");
		
	//	System.out.println(x.toString());
	//	System.out.println(y.root(1, 4, 0.001));
		System.out.println(z.area(2, 4, 0.001));
	}
}
