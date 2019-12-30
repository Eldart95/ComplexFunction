package Ex1Testing;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.Range;
import Ex1.function;
import Ex1.functions;
/**
 * Note: minor changes (thanks to Amichai!!)
 * The use of "get" was replaced by iterator!
 * 
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
class Functions_GUITest {
	public static void main(String[] a) {
		functions data = FunctionsFactory();
		int w=1000, h=600, res=200;
		Range rx = new Range(-10,10);
		Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		String file = "function_file.txt";
		String file2 = "function_file2.txt";
		try {
			data.saveToFile(file);
			Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}
		
		String JSON_param_file = "GUI_params.txt";
		try {
			data.drawFunctions(JSON_param_file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private functions _data=null;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		_data = FunctionsFactory();
	}


	@Test
	void testInitFromFile() throws IOException {
		
		function t = new ComplexFunction();
		String [] output = {
				"Min(-1.0x^4+2.4x^2+3.1,5.0x^2+0.5)" , 
				"Plus(Divid(2.0x,2.0x),3.0x)" ,
				"Plus(Min(Comp(2.0x,3.0x^2),4.0x^4),3.0x^2)" ,
				"Plus(3x,3x)",
				"Plus(-1.0x^4+2.4x^2+3.1,+1.1x^5-1.2999999999999998x+5.0)",
				"Divid(Plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)"
				
		};
		
		Functions_GUI FG = new Functions_GUI();
		Functions_GUI ex = new Functions_GUI();
		for (int j=0; j<output.length; j++) {
			FG.add(t.initFromString(output[j]));
		}
		
		
		try {
			ex.initFromFile("C:\\Users\\Eldar\\eclipse-workspace1\\Matala0\\src\\Ex1\\initTest.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator<function> i = ex.iterator();
		Iterator<function> j = FG.iterator();

		while (i.hasNext() && j.hasNext()) { 
			function z =i.next(); 
			function z1 = j.next();
			if (!(z1.equals(z))) { 
				fail(); 
				}
			}
	}

	

	@Test
	void testSaveToFile() {
		
		try {
			_data.saveToFile("C:\\\\Users\\\\Eldar\\\\eclipse-workspace1\\\\Matala0\\\\src\\\\Ex1Testing\\\\SaveToFileTest.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDrawFunctions() {
		Functions_GUI z = new Functions_GUI();
		Polynom z1 = new Polynom("-x^2");
		Polynom z2 = new Polynom("3x^2+5");
		Polynom z3 = new Polynom("4x^2+5x-3");
		z.add(z1);
		z.add(z2);
		z.add(z3);
		Range rx = new Range(-10,10);
		Range ry = new Range(-10,10);
		z.drawFunctions(700,700,rx,ry,500);
		_data.drawFunctions(700,700,rx,ry,900);
		Functions_GUI nf = new Functions_GUI();
		try {
			nf.initFromFile("C:\\Users\\Eldar\\eclipse-workspace1\\Matala0\\src\\Ex1\\initTest.txt");
			nf.drawFunctions(700, 700, rx, ry, 700);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	fail("Not yet implemented");
	}

	@Test
	void testDrawFunctionsIntIntRangeRangeInt() {
		Functions_GUI z = new Functions_GUI();
		Polynom x = new Polynom("2x^3");
		z.add(x);
		try {
			z.drawFunctions("C:\\Users\\Eldar\\eclipse-workspace1\\Matala0\\src\\Ex1Testing\\GUI_params.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Functions_GUI nf = new Functions_GUI();
		try {
			nf.initFromFile("C:\\Users\\Eldar\\eclipse-workspace1\\Matala0\\src\\Ex1\\initTest.txt");
			nf.drawFunctions("C:\\Users\\Eldar\\eclipse-workspace1\\Matala0\\src\\Ex1Testing\\GUI_params.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fail("Not yet implemented");
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1+2.4x^2-x^4";
		String s2 = "5+2x-3.3x+0.1x^5";
		String[] s3 = {"x+3","x-2","x-4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		
		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		function cc = new Polynom("x+1");
		ComplexFunction cf4 = new ComplexFunction("divid", cc ,cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		//ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);		
		return ans;
	}
}
