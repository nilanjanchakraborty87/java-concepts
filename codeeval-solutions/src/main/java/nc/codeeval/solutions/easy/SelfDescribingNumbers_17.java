package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A number is a self-describing number when (assuming digit positions are
 * labeled 0 to N-1), the digit in each position is equal to the number of times
 * that that digit appears in the number.
 * 
 * INPUT SAMPLE:
 * 
 * The first argument is the pathname to a file which contains test data, one
 * test case per line. Each line contains a positive integer. E.g.
 * 
 * 2020 
 * 22 
 * 1210 
 * 
 * OUTPUT SAMPLE:
 * 
 * If the number is a self-describing number, print out 1. If not, print out 0.
 * E.g.
 * 
 * 1 
 * 0 
 * 1 
 * 
 * For the curious, here's how 2020 is a self-describing number: Position
 * '0' has value 2 and there is two 0 in the number. Position '1' has value 0
 * because there are not 1's in the number. Position '2' has value 2 and there
 * is two 2. And the position '3' has value 0 and there are zero 3's.
 * 
 * @author nchak2
 *
 */
public class SelfDescribingNumbers_17 {

	public static void main(String[] args) {
		if (args != null && args.length >= 1) {
			try {
				Scanner sc = new Scanner(new File(args[0]));
				while (sc.hasNextLine()) {
					String _line = sc.nextLine().trim();
					int selfDescriptive = 1;
					Map<Integer, Integer> locItems = new HashMap<Integer, Integer>();
					Map<Integer, Integer> digitFrequencies = new HashMap<Integer, Integer>();
					for(int i = 0; i < _line.length(); i++){
						int digit = Character.getNumericValue(_line.charAt(i));
						locItems.put(i, digit);
						Integer frequency = digitFrequencies.get(digit);
						digitFrequencies.put(digit, frequency == null ? 1 : ++frequency);
					}
					
					for(Map.Entry<Integer, Integer> entry : locItems.entrySet()){
						int digit = entry.getKey();
						int expectedOccurence = entry.getValue();
						Integer freq = digitFrequencies.get(digit);
						if((expectedOccurence > 0 && freq == null) || (freq != null && freq.intValue() != expectedOccurence)){
							selfDescriptive = 0;
							break;
						}
					}
					System.out.println(selfDescriptive);
				}
				sc.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

}
