package nc.codeeval.solutions.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Given numbers x and n, where n is a power of 2, print out the smallest
 * multiple of n which is greater than or equal to x. Do not use division or
 * modulo operator.
 * 
 * INPUT SAMPLE:
 * 
 * The first argument will be a path to a filename containing a comma separated
 * list of two integers, one list per line. E.g.
 * 
 * 13,8 
 * 17,16 
 * 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print to stdout, the smallest multiple of n which is greater than or equal to
 * x, one per line. E.g.
 * 
 * 16 
 * 32
 * 
 * @author nchak2
 *
 */
public class SmallestMultiplies_5 {

	public static void main(String[] args) {

		if (args != null && args.length >= 1) {
			try {
				File f = new File(args[0]);
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line = "";
				
				while((line = br.readLine()) != null){
					String[] nums = line.split(",");
					int X = Integer.valueOf(nums[0]);
					int n = Integer.valueOf(nums[1]);
					
					int i = 1;
					while(i * n < X)
						i++;
					
					System.out.println(i * n);
				}
				
				br.close();
			} catch (Exception e) {

			}
			 

		}
		else
			System.err.println("Please provide a valid file name");
	}

}
