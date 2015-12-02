package nc.codeeval.solutions.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * The Fibonacci series is defined as: F(0) = 0; F(1) = 1; F(n) = F(n–1) +
 * F(n–2) when n>1. Given an integer n gte 0, print out the F(n).
 * 
 * INPUT SAMPLE:
 * 
 * The first argument will be a path to a filename containing integer numbers,
 * one per line. E.g.
 * 
 * 5 
 * 12 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print to stdout, the fibonacci number, F(n). E.g.
 * 
 * 5 
 * 144
 * 
 * @author nchak2
 *
 */
public class FibonacciSeries {

	static Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
	public static void main(String[] args) {
		if(args != null && args.length >=1 ){
			try{
				BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
				String ln = null;
				while((ln = br.readLine()) != null){
					int number = fib(Integer.valueOf(ln.trim()));
					System.out.println(number);
				}
				br.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		else
			throw new RuntimeException("Please provide the input file complete path as the first argument");
	}

	private static int fib(int X){

		int f = 0;

		if(memo.get(X) != null)
			f = memo.get(X);
		else{
			if(X == 0)
				f = 0;
			else if(X == 1)
				f = 1;
			else
				f = fib(X-1) + fib(X-2);
			
			memo.put(X, f);
		}
		return f;
	}

}
