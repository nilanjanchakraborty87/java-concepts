package nc.codeeval.solutions.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Given a positive integer, find the sum of its constituent digits.
 * 
 * INPUT SAMPLE:
 * 
 * The first argument will be a path to a filename containing positive integers,
 * one per line. E.g.
 * 
 * 23 
 * 496 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print to stdout, the sum of the numbers that make up the integer, one per
 * line. E.g.
 * 
 * 5 
 * 19
 * 
 * @author nchak2
 *
 */
public class SumAllDigits_8 {

	public static void main(String[] args) {
		if(args != null && args.length >=1 ){
			try{
				BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
				String ln = null;
				while((ln = br.readLine()) != null){
					int sum = 0;
					
					/*int number = Integer.valueOf(ln.trim());
					while(number > 0){
						int digit = number % 10;
						sum = sum + digit;
						number = number / 10;
					}*/
					
					char[] digits = ln.trim().toCharArray();
					for(Character c : digits){
						sum = sum + Character.getNumericValue(c);
					}
					System.out.println(sum);
				}
				br.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		else
			throw new RuntimeException("Please provide the input file complete path as the first argument");
	}


}
