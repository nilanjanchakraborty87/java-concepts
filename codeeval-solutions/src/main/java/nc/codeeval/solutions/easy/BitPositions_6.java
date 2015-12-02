package nc.codeeval.solutions.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Given a number n and two integers p1,p2 determine if the bits in position p1
 * and p2 are the same or not. Positions p1 and p2 are 1 based.
 * 
 * INPUT SAMPLE:
 * 
 * The first argument will be a path to a filename containing a comma separated
 * list of 3 integers, one list per line. E.g.
 * 
 * 
 * 86,2,3 
 * 125,1,2 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print to stdout, 'true'(lowercase) if the bits are the same, else
 * 'false'(lowercase). E.g.
 * 
 * true 
 * false
 * 
 * @author nchak2
 *
 */
public class BitPositions_6 {

	private static final String  COMMA = ",";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args != null && args.length >=1 ){
			try{
				BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
				String ln = null;
				while((ln = br.readLine()) != null){
					String[] items = ln.split(COMMA);
					int number = Integer.valueOf(items[0]);
					int pos1 = Integer.valueOf(items[1]);
					int pos2 = Integer.valueOf(items[2]);
					
					//bitwise operator solution
					/*if(((number>>1) & 1) == ((number>>2) & 1))
						System.out.println(true);
					else
						System.out.println(false);*/
					
					//another approach
					String binary = Integer.toBinaryString(number);
					System.out.println(binary);
					if(binary.charAt(pos1-1) == binary.charAt(pos2-1))
						System.out.println(true);
					else
						System.out.println(false);
							
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
