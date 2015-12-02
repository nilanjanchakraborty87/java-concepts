package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.Scanner;

/**
 * Given two integers N and M, calculate N Mod M (without using any inbuilt
 * modulus operator).
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept as its first argument a path to a filename. Each
 * line in this file contains two comma separated positive integers. E.g.
 * 
 * 20,6 
 * 2,3 
 * 
 * You may assume M will never be zero.
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out the value of N Mod M
 * 
 * @author nchak2
 *
 */
public class NModM_18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args != null && args.length >= 1) {
			try {
				Scanner sc = new Scanner(new File(args[0]));
				while (sc.hasNextLine()) {
					String _line = sc.nextLine().trim();
					String[] elems = _line.split(",");
					int N = Integer.valueOf(elems[0]);
					int M = Integer.valueOf(elems[1]);
					
					int mod = N;
					while( N >= M){
						mod = N - M;
						N = N - M;
					}
						
					System.out.println(mod);
				}
				sc.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
