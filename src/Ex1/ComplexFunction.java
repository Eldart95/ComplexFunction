package Ex1;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * The class ComplexFunction represents a ComplexFunction of the shape : g(f1(x), f2(x)).
 * g is an operation : plus,divide,multiply,composite,max and min
 * f1,f2 are function of the type Polynom or ComplexFunction
 * f1 is the left function and f2 is the right function
 * 
 * @author Eldar
 */

public class ComplexFunction implements complex_function {
	public Operation Sign;
	public function left,right;


	LinkedList<ComplexFunction> func = new LinkedList<ComplexFunction>();
	
	//////////////////Constructors///////////////////
	
	public ComplexFunction(Operation s,function l,function r ) {
		this.Sign=s;
		if(l!=null) this.left=l;
		if(r!=null) this.right=r;
	}
	
	public ComplexFunction(String s) {
		ComplexFunction x = (ComplexFunction) this.initFromString(s);
		
		this.Sign=x.Sign;
		this.left=x.left;
		this.right=x.right;
		
	}
	
	public ComplexFunction() {
		this.left=null;
		this.Sign=null;
		this.right=null;
	}
	public ComplexFunction (function left) {
		this.left = left;
		this.right= null;
		this.Sign = Operation.None;
	}

	public ComplexFunction(String s, function left, function right) {
		this.left=left;
		this.right=right;
		String a = s.toLowerCase();
		switch(a) {
		case "plus": this.Sign=Operation.Plus; break;
		case "min": this.Sign=Operation.Min; break;
		case "max": this.Sign=Operation.Max; break;
		case "comp": this.Sign=Operation.Comp; break;
		case "divid": this.Sign=Operation.Divid; break;
		case "div": this.Sign=Operation.Divid; break;
		case "mul": this.Sign=Operation.Times; break;
		default: this.Sign=Operation.None;
		}
	}
	//////////////////Getters///////////////////

	public Operation getOp() {
		return this.Sign;
	}
	
	public function left() {
		if(this.left!=null) return this.left;
		 
		return null;
	}
	public function right() {
		if(this.right!=null) return this.right;
		
		return null;
	}
	
	//////////////////Methods///////////////////
	/**
	 * Function to calculate the value of complexfunction at x.
	 */
	@Override
	public double f(double x) {
		switch(this.Sign) {
		case Plus: return this.left.f(x)+this.right.f(x);
		case Times: return this.left.f(x)*this.right.f(x);
		case Divid:
			if(this.right.f(x)!=0) {
			return this.left.f(x)/this.right.f(x);
			}
			else throw new RuntimeException("Cant divide by 0");
		case Min:
			if(this.right.f(x)>=this.left.f(x)) return this.left.f(x);
			return this.right.f(x);
		case Max:
			if(this.right.f(x)<=this.left.f(x)) return this.left.f(x);
			return this.right.f(x);
		case Comp:
			if (this.right != null ) {
				return this.left.f(this.right.f(x));
			}
			return this.left.f(x);
		case None:
		case Error:	throw new RuntimeException("An error occured");
		}
		
		return 0;
			
			
			
		}
	
	/**
	 * Takes a string and build an ComplexFunction out of it
	 */
		
	@Override
	public function initFromString(String ns) { 
		
		String s =clearSpaces(ns);
		
		if(!s.contains("(") && !s.contains(")")) {
			function p = new Polynom(s);
			
			return p;	
		}
		
		else {
			int j = 0;
			while(s.charAt(j)!='(') {
			j++;
		}
		String temp1 = s.substring(0,j);
		int w = split(s);
		String a = s.substring(j+1,w);                      //Cuts the string into two pieces
		function f1 = initFromString(a);                    //that will later become the left
		String b = s.substring(w+1,s.length()-1);           //and the right of a ComplexFunction.
		function f2 = initFromString(b);
		
	
	
		String temp = temp1.toLowerCase();
		switch(temp) {
		case "plus": this.Sign=Operation.Plus; break;
		case "min": this.Sign=Operation.Min; break;
		case "max": this.Sign=Operation.Max; break;
		case "comp": this.Sign=Operation.Comp; break;
		case "divid": this.Sign=Operation.Divid; break;
		case "div": this.Sign=Operation.Divid; break;
		case "mul": this.Sign=Operation.Times; break;
		default: this.Sign=Operation.None;
		}
		ComplexFunction f16=new ComplexFunction(temp,f1,f2);
		

		return f16;
		
		}
		
	}

	/**
	 * returns a deep copy of a function
	 */
	@Override
	public function copy() {
		
		function f35 = new ComplexFunction(this.Sign.toString(),this.left,this.right);
		return f35;
	}
	/**
	 * Compare between objects, will work only under certain condition- both objects are 
	 * ComplexFunctions.
	 * otherwise wont work.
	 * returns true if two functions are logically equal
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		boolean ans = false;
		function fobj = (function) obj;
		double l,r;
		double i;
		for (i = Math.random()*100; i < 5000; i++) {
			l=this.f(i);
			r=fobj.f(i);
			if(Math.abs(l-r)<0.0001) {
				ans=true;
			}
			
		}
		return ans;
	}
	
	
	/**
	 * manipulates the function and adds another function to it
	 * if there is right function than this function turns into complexfunction
	 * if there isnt than the new function becomes the right one
	 */
	
	@Override
	public void plus(function f1) {
		if(this.right!=null) {
			function new_f = new ComplexFunction(this.Sign.toString(), this.left,this.right);
			this.left=new_f;
		}
		
		this.right=f1;
		this.Sign=Operation.Plus;
		
	}
	/**
	 * manipulates the function and multiplys another function 
	 * if there is right function than this function turns into complexfunction
	 * if there isnt than the new function becomes the right one
	 */
	@Override
	public void mul(function f1) {
		if(this.right!=null) {
			function new_f = new ComplexFunction(this.Sign.toString(), this.left,this.right);
			this.left=new_f;
		}
		
		this.right=f1;
		this.Sign=Operation.Times;
		
	}
	
	/**
	 * manipulates the function and divides another function 
	 * if there is right function than this function turns into complexfunction
	 * if there isnt than the new function becomes the right one
	 */
	@Override
	public void div (function f1) {
		
		if(this.right!=null) {
			function new_f = new ComplexFunction(this.Sign.toString(), this.left,this.right);
			this.left=new_f;
		}
		
		this.right=f1;
		this.Sign=Operation.Divid;
		
	}
	/**
	 * Makes a string out of a complexfunction
	 */

	@Override
	public String toString() {
		String ans="";
		if(this.Sign==Operation.None) {
		ans+=this.left;
		}
		else ans+=this.Sign+"(";
		
		
	
		if(this.left!=null) {
			ans+=this.left;	
		}
		if(this.right!=null) {
			ans+=",";
			ans+=this.right;
			ans+=")";
			}
		
		return ans;
	}
		
	public Iterator<ComplexFunction> iteretor() {
		return func.iterator();
	}
	@Override
	/**
	 * manipulates the function and calculates the max 
	 * if there is right function than this function turns into complexfunction
	 * if there isnt than the new function becomes the right one
	 */
	public void max(function f1) {
		if(this.right!=null) {
			function new_f = new ComplexFunction(this.Sign.toString(), this.left,this.right);
			this.left=new_f;
		}
		
		this.right=f1;
		this.Sign=Operation.Max;
	}
	@Override
	/**
	 * manipulates the function and calculates the min 
	 * if there is right function than this function turns into complexfunction
	 * if there isnt than the new function becomes the right one
	 */
	public void min(function f1) {
		if(this.right!=null) {
			function new_f = new ComplexFunction(this.Sign.toString(), this.left,this.right);
			this.left=new_f;
		}
		
		this.right=f1;
		this.Sign=Operation.Min;
		
	}
	@Override
	/**
	 * manipulates the function and comps one on another
	 * if there is right function than this function turns into complexfunction
	 * if there isnt than the new function becomes the right one
	 */
	public void comp(function f1) {
		if(this.right!=null) {
			function new_f = new ComplexFunction(this.Sign.toString(), this.left,this.right);
			this.left=new_f;
		}
		
		this.right=f1;
		this.Sign=Operation.Comp;
		
	}
	//////////////////Helpers///////////////////
	
	/**
	 * 
	 * The helper of the init method, cuts the string.
	 */
	private int split(String s) {
		int q1=0,q2=0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)=='(') q1++;
			if(s.charAt(i)==')') q1--;
			if(s.charAt(i)==',' && q1==1) {
				q2=i;
			}
		}
		return q2;
	}
	/**
	 * Space clearer.
	 * if space (" ") is detected - than make it disapear.
	 * 
	 * @param s
	 * @return string without spaces.
	 */
	
	public String clearSpaces (String s) {
		String t="";
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)==' ') {
				continue;
				}
			t=""+t+s.charAt(i);
		}
		return t;
	}
	
	//done
	
}