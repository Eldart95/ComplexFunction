package Ex1;

//import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import javax.crypto.EncryptedPrivateKeyInfo;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4x^3-34x", "3x^2+x", "4x^3+2x^2+3x+5",5x^2};
	 *  The legal chars of a string are ax^b,ax,a,0,+,-,.(!)
	 * @param s: is a string representing a Polynom
	 */
	LinkedList<Monom> monoms = new LinkedList<Monom>();
	public Polynom(String ns) {
		String s = clearSpaces(ns);
		//polynomCheck(s);
		boolean isMinus = false;
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i)=='-') {
				count++;
			}
		}
		if(s.charAt(0)=='-') {
			s=s.substring(1);
			isMinus=true;
		}

		if(!s.contains("+") && !s.contains("-") || (s.charAt(0)=='-' && !s.contains("+") && count<2)) {
			
			Monom temp = new Monom(s);
			
			monoms.add(temp);
		}
		else {
			for (int i = 0; i < s.length(); i++) {//-3x^2-2x^3
	
				if(s.charAt(i)=='+') {
					String tempo = s.substring(0, i);
					s=s.substring(i+1, s.length());
					Monom temp1 = new Monom(tempo);
					monoms.add(temp1);	
					i=0;
		
				}
				else if(s.charAt(i)=='-') {
					String tempo = s.substring(0, i);
					s=s.substring(i, s.length());
					Monom temp1=new Monom(tempo);
					monoms.add(temp1);
					i=0;
				}
				if(i==s.length()-1) {
					Monom temp2= new Monom(s);
					monoms.add(temp2);				
				}
			}
		}
		if(isMinus) {
			Monom minus = new Monom("-1");
			monoms.peek().multipy(minus);
		}
		
	}
	/**
	 * This method computes the value of Polynom at 'x'.
	 * 
	 */
	@Override
	public double f(double x) {
		double ans =0;
		Iterator<Monom> it = this.iteretor();
		
		while(it.hasNext()) {
			Monom nextItem = it.next();
			ans+=nextItem.f(x);
			
		}
		
		return ans;
	}
	/**
	 * This method adds polynom to polynom.
	 */

	@Override
	public void add(Polynom_able p1) {
		String a = p1.toString();
		Polynom_able z = new Polynom(a);
		Iterator<Monom> it = this.iteretor();
		Iterator<Monom> it2 = z.iteretor();
		
		while(it.hasNext()) {
			Monom next = it.next();
			
			while(it2.hasNext()) {
				Monom next2=it2.next();
				
				if(next.get_power()==next2.get_power()) {
					Monom x = new Monom(next2.toString());
					next.add(x);
					it2.remove();
				}
		}					
			it2 = z.iteretor();
		
		}
		while(it2.hasNext()) {
			Monom temp = it2.next();
			monoms.add(temp);
		}
	}
	/**
	 * This method add monom the polynom
	 * If the powers of a monom inside the polynom and the monom that
	 * is being added are equals, the method will joint the monoms.
	 */

	@Override
	public void add(Monom m1) {
		Iterator<Monom> it = this.iteretor();
		boolean flag = false;
		String a = m1.toString();
		Monom x = new Monom(a);
		
		while(it.hasNext()) {
			Monom nextItem = it.next();
			if(nextItem.get_power()==x.get_power()) {
				nextItem.add(x);
				flag = true;
			}
			
		}
		if(!flag) {
			monoms.add(x);
		}
		
	}
	/**
	 * This method uses the add method to subtract polynom from polynom
	 */

	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> it2 = p1.iteretor();
		Monom y = new Monom("-1");
		while(it2.hasNext()) {
			Monom x = it2.next();
			x.multipy(y);
		}
		
		this.add(p1);
	
		
	}
	/**
	 * This method multiplys polynom with polynom
	 */

	@Override
	public void multiply(Polynom_able p1) {
		String a = p1.toString();
		Polynom_able z = new Polynom(a); 
		Iterator<Monom> it = this.iteretor();
		Iterator<Monom> it2 = z.iteretor();
		while(it.hasNext()) {
			Monom x = it.next();
			while(it2.hasNext()) {
				Monom y = it2.next();
				x.multipy(y);
			}
			it2=z.iteretor();
		}
		
	}
	/**
	 * This method check if 2 polynoms represents the same function.
	 * 
	 */

	@Override
	public boolean equals(Object p1) {
		
	       if(p1 instanceof Monom) {
	            Polynom p2 = new Polynom();
	            p2.add((Monom) p1);
	            return this.equals(p2);
	        }
	        else if(p1 instanceof Polynom) {
	            Polynom p2=(Polynom)p1;
	            boolean isEquals = true;
	            Iterator<Monom> it1 = this.iteretor();
	            Iterator<Monom> it2 = p2.iteretor();
	            while (it1.hasNext() && it2.hasNext()) {
	                Monom a = it1.next();
	                Monom b = it2.next();
	                if (!a.equals(b)) isEquals = false;
	            }
	            if(it1.hasNext()||it2.hasNext()) isEquals = false;
	            return isEquals;
	        }
	        return false;
	}
	/**
	 * This method check if the polynom is a zero polynom
	 */
	@Override
	public boolean isZero() {
		
		boolean flag = true;
		Iterator<Monom> it = this.iteretor();
		
		while(it.hasNext()) {
			Monom nextItem = it.next();
			if(nextItem.get_coefficient()!=0) {
				flag = false;
			}
			
		}
		return flag;
	}
	/**
	 * This method calculates the root of a function
	 * If there are more than one root-
	 * the method will return the smaller.
	 */

	@Override
	public double root(double x0, double x1, double eps) {
		if(this.isZero()) return 0;
		if(this.f(x0)*this.f(x1)>0) throw new RuntimeException("no root");
		double from = x0;
		double to = x1;
		while(this.f(from)<this.f(to)) {
			if(this.f(from)==0 || Math.abs(this.f(from))<eps) {
				return from;
				
			}
			from+=eps;
		}
		throw new RuntimeException("No Root Detected");
		
	}
	/**
	 * This method creates an exact copy of given polynom
	 */

	@Override
	public Polynom_able copy() {
		Polynom p = new Polynom();
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom m = it.next();
			p.add(m);
		}
		return p;
	}
	/**
	 * This method calcultes the derivtive of a given function
	 */

	@Override
	public Polynom_able derivative() {
		Polynom y = new Polynom();
		Iterator<Monom>it=this.iteretor();
		while(it.hasNext()) {
			Monom x = it.next();
			Monom z = x.derivative();
			y.add(z);
			
		}
		return y;
		
	}
	/**
	 * This method calcultes the area between the 'x' axis and the function.
	 * By steps of eps.
	 */

	@Override
	public double area(double x0, double x1, double eps) {
		if(this.isZero()) return 0;
		if(x0>x1) return 0;
		

		
		if(x0==x1) return 0;
		double from = x0;
		double ans=0;
		double to = x1;
		while(this.f(from)<=this.f(to)) {
			if(this.f(from)>0) ans+=this.f(from)*eps;
			from+=eps;
			
		
	
		if(ans<0) throw new RuntimeException("No Area Detected");
		
	}
		return ans;
	}
	/**
	 * This method multiplys a polynom with monom.
	 */

	@Override
	public void multiply(Monom m1) {
		
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom x = it.next();
			x.multipy(m1);
			
		}
		
		
	}
	@Override
	public function initFromString(String s) {
		function n = new Polynom(s);
		return n;
	}
	/**
	 * This method determines is a string is valid
	 * @param s is the string that is being checked
	 */
	public void polynomCheck(String s) {
		s=s.toLowerCase();
		if(s=="") {
			throw new RuntimeException("Empty String Received");
		}
		for (int i = 0; i < s.length(); i++) {
			char x = s.charAt(i);
			if((x>=48 && x<=57)||x==43||x==94||x==45||x==120 || x==46) {
				
			}
			else {
				throw new RuntimeException("Illegal Character In Polynom");
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		Polynom a0 = new Polynom("x^2-16");
		//System.out.println(a0.f(-5)*a0.f(5));
		System.out.println(a0.root(0,4, 0.01));
		
		

	}
	
	/**
	 * This is the iterator of the LinkedList
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return monoms.iterator();
	}
	/**
	 * This method creates a string out of a polynom
	 */
	@Override
	public String toString() {
		if(this.isZero()) return "0";
		String ans ="";
		Iterator<Monom> it = this.iteretor();

		while(it.hasNext()) {
		    Monom nextItem = it.next();
		    if(nextItem.get_coefficient()>=0) {
		    	ans+="+"+nextItem;
		    }
		    else {
		    	ans+=nextItem;
		    }
		    
		    
		}
		
		if(ans.charAt(0)=='+') {
			if(ans.length()==1) throw new RuntimeException("Empty String");
			else ans=ans.substring(1);
			
		}
		

		return ans;
	}
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

}
