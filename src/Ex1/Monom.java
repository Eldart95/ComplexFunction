
package Ex1;

import java.util.Comparator;

import javax.management.RuntimeErrorException;

import java.util.ArrayList;

/**
 * This class represents a simple "Monom" of shape ax^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 * this method calculate the value of f in x.
	 * @return
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	
	/**
	 * this method take a string from the form of ax^b , and build a Monom out of it. 
	 * a is a real number, b>0; 
	 * @param s
	 */
	public Monom(String s) {
		s=s.toLowerCase();
		if(s.length()==2 && s.charAt(0)=='-') {
			if(s.charAt(1)=='x') {
				set_coefficient(-1);
				set_power(1);
			}
			else {
			s=s.substring(1);
			set_coefficient(-Double.parseDouble(s));
			set_power(0);
			}
		}
		else if(s.length()==1) {
		if(s.charAt(0)=='0') {
			
		}
		else if (s.charAt(0)=='x') {
			set_power(1);
			set_coefficient(1);
		}
		else {
			set_power(0);
			set_coefficient(Double.parseDouble(s));
		}
		}
		else {
		for (int a = 0; a < s.length(); a++) {
			
			if(s.charAt(a)>=0 && s.charAt(a)<48 ||  s.charAt(a)>57 ) {
				if(s.charAt(a)==120 || s.charAt(a)==94 || s.charAt(a)==46 || s.charAt(a)==45) {}
				else throw new ArithmeticException("Illegal char in monom");
			}
		}
		if(!s.contains("x")&&!s.contains("^")) {
			String temp4=s.substring(0, s.length());
			if(temp4.isEmpty()) set_coefficient(0);
			else set_coefficient(Double.parseDouble(temp4));
			set_power(0);
				
			}
		else if(!s.contains("^") && s.contains("x")) {
			String temp2=s.substring(0, s.length()-1);
			if(temp2.isEmpty()) set_coefficient(0);
			else set_coefficient(Double.parseDouble(temp2));
			set_power(1);
			
		}

		else {		
		int b=0;
		while(s.charAt(b)!='x') {
			b++;
		}
		
		if(b==0) {
			set_coefficient(1);
		}
		else {
		String temp0 = s.substring(0, b);
		
		if(temp0.charAt(0)=='-' && temp0.length()==1) {
			set_coefficient(-1);
		}
		else {
		set_coefficient(Double.parseDouble(temp0));
		}
		}
		int i=0;
		while(s.charAt(i)!='^') {
			i++;
		}
		String temp = s.substring(i+1, s.length());
		set_power(Integer.parseInt(temp));
		}
		}
	}
	/**
	 * this method adds a Monom to the Monom who called the method.
	 * Monoms must be of the same power.
	 * @param m
	 */
	public void add(Monom m) {
		String y = m.toString();
		Monom z = new Monom (y);
		
		if(this.isZero()) {
			this._coefficient=m._coefficient;
			this._power=m._power;
		}
		else if(m.isZero()) {
			
		}
		else if(this._power==m._power) {
			this._coefficient+=z._coefficient;
		}
		else {
			throw new RuntimeException("can't add togheter different powers");
			
			
		}

		
	}
	/**
	 * this method multiplys a Monom with the Monom who called the method. 
	 * @param d
	 */
	public void multipy(Monom d) {
		if(this.isZero() || d.isZero()) {
			this._coefficient=0;
			this._power=0;
		}
		
		else {
		this._coefficient*=d._coefficient;
		this._power+=d._power;
	}
		
	}
	/**
	 * this method makes a string out of a Monom.
	 */
	
	public String toString() {
		//syso by power and add x,^ if needed
		if(this.get_coefficient()==0) {
			return "0";
		}
		if(this.get_power()==0) {
			return ""+this._coefficient;
		}
		if(this.get_power()==1) {
			return this._coefficient+"x";
		}
		if(this.isZero()) {
			return "0";
		}
		
		else {
		String ans = this._coefficient+"x"+"^"+this._power;
		return ans;
	}
		
	}
	/**
	 * this method takes Monom and compares it to the Monom who called the method.
	 * @param x
	 * @return true if the Monoms are the same, false otherwise.
	 */
	public boolean equals(Object obj) {

        if(obj instanceof Monom)  {

            double dc = this.get_coefficient() -((Monom) obj).get_coefficient();
            if (Math.abs(dc) < EPSILON && this.get_power() == ((Monom) obj).get_power()) {
            	return true;
            }
            return false;
        }

        else if (obj instanceof function) {
            function f=(function) obj;
            return f.equals(this);
        }
        else {
            return false;
        }
	
	}
	
	public function copy() {
		String z = this.toString();
		Monom x = new Monom(z);
		return x;
	}
	@Override
	public function initFromString(String s) {
		function n = new Monom(s);
		return n;
	}
		
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	
	
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	public static void main(String[] args) {
	
	Monom a = new Monom("3.2x");
	Monom b = new Monom("6.2x");
	a.add(b);
	System.out.println(a);
	System.out.println(b);
		
	
		
	}
}
