package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.Parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.parser.JSONParser;
import com.google.gson.Gson;



public class Functions_GUI implements functions{
	
	public static void main(String[] args) {
		
	}
	
	
	LinkedList<function> func = new LinkedList<function>();
	
	Color [] colors = {Color.BLUE, Color.DARK_GRAY, Color.MAGENTA, Color.GREEN, Color.ORANGE, Color.CYAN, Color.RED, Color.PINK,Color.YELLOW };

	@Override
	public boolean add(function arg0) {
		return func.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return func.addAll(arg0);
	}

	@Override
	public void clear() {
		func.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		return func.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return func.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return func.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return func.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return func.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return func.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return func.retainAll(c);
	}

	@Override
	public int size() {
		return func.size();
	}

	@Override
	public Object[] toArray() {
		return func.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return func.toArray(a);
	}
	
	@Override
	public String toString() {
		Iterator<function> i = this.iterator();
		String ans ="";
		while (i.hasNext()) {
			function z=  i.next();
			if(z instanceof Polynom) {
				ans+=z;
			}
			else if( z instanceof ComplexFunction) {
				ans+=z;
			}
			i.next(); 
			ans+=",";
		}
		return ans;
	}		


	@Override
	/**
	 * read from file
	 * using BufferedReader
	 */
	public void initFromFile(String file) throws IOException {
		
		function f = new ComplexFunction();
		String s="";
		try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
        	
            while ((s = br.readLine()) != null) 
            {
                func.add(f.initFromString(s));
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("could not read file");
        }
		
	}
	/**
	 * save to file method 
	 * using PrintWriter
	 * using StringBuilder
	 * 
	 */

	@Override
	public void saveToFile(String file) throws IOException {
		Iterator<function> it = func.iterator();
		
		try {
			PrintWriter write = new PrintWriter(new File(file));
			StringBuilder sb = new StringBuilder();
			
			while (it.hasNext()) {
				function f = it.next();
				sb.append(f.toString()+"\n");
			}	
			write.write(sb.toString());
			write.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
	}
	/**
	 * draws functions using Stddraw lib showed in class
	 * 
	 */

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		if(resolution<1) throw new RuntimeException("Small res");
		StdDraw.setCanvasSize(width+5, height+5); // setting the canvas size
		StdDraw.setXscale(rx.get_min()-3, rx.get_max()+3); // setting the x scale
		StdDraw.setYscale(ry.get_min()-3, ry.get_max()+3); // setting the y scale 
		
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0); //setting the x axis
		StdDraw.line(0, ry.get_min(), 0, ry.get_max()); //setting the y axis
		
		Iterator<function> x = this.iterator();
		while(x.hasNext()) {
			function y = x.next();
			double step = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
			double c = ((Math.random()*9));
			int a = (int)c;
			Color color = colors[a];
		
			StdDraw.setPenColor(color);
			for (double i = rx.get_min(); i < rx.get_max(); i+=step) {
				StdDraw.line(i, y.f(i), i+step, y.f(i+step));
			}
		}
		
		
		
		
		
	}
	/**
	 * Reading json using googles json parser and string parsing.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */

	@Override
	public void drawFunctions(String json_file) throws FileNotFoundException, IOException, ParseException {
		Gson gson = new Gson();	

		GUI p;
		try {
		Reader r = new FileReader(json_file);
			p = gson.fromJson(r, GUI.class);
			Range rx = new Range(p.Range_X[0], p.Range_X[1]);
			Range ry = new Range(p.Range_Y[0], p.Range_Y[1]);
			drawFunctions(p.Width, p.Height, rx, ry, p.Resolution);
		} catch (FileNotFoundException e) {
			
		}
		

	}
	
	private class GUI{
		int Width = 0;
		int Height = 0;
		double[] Range_X = {0,1};
		double[] Range_Y = {0,1};
		int Resolution = 50;
		
	}
	}
	
	


