package nc.codeeval.solutions.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * An Armstrong number is an n-digit number that is equal to the sum of the n'th
 * powers of its digits. Determine if the input numbers are Armstrong numbers.
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept as its first argument a path to a filename. Each
 * line in this file has a positive integer. E.g.
 * 
 * 
 * 6 
 * 153 
 * 351 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out True/False if the number is an Armstrong number or not. E.g.
 * 
 * 
 * True 
 * True 
 * False
 * 
 * @author nchak2
 *
 */
public class ArmStrongNumbers_20 {

	// TODO Auto-generated method stub
	public static void main(String[] args) {
		if (args != null && args.length >= 1) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
				String ln = null;
				while ((ln = br.readLine()) != null) {
					int number = Integer.valueOf(ln.trim());
					int length = ln.length();
					
					int tempNum = number;
					int sum = 0;
					while(tempNum > 0){
						int d = tempNum % 10;
						sum = sum + (int) Math.pow(d, length);
						tempNum = tempNum / 10;
					}
					
					System.out.println(number == sum ? "True" : "False");
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
