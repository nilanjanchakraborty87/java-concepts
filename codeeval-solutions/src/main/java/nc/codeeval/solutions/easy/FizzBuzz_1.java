package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Players generally sit in a circle. The first player says the number “1”, and
 * each player says next number in turn. However, any number divisible by X (for
 * example, three) is replaced by the word fizz, and any divisible by Y (for
 * example, five) by the word buzz. Numbers divisible by both become fizz buzz.
 * A player who hesitates, or makes a mistake is eliminated from the game.
 * 
 * Write a program that prints out the final series of numbers where those
 * divisible by X, Y and both are replaced by “F” for fizz, “B” for buzz and
 * “FB” for fizz buzz.
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept a file as its first argument. The file contains
 * multiple separated lines; each line contains 3 numbers that are space
 * delimited. The first number is the first divider (X), the second number is
 * the second divider (Y), and the third number is how far you should count (N).
 * You may assume that the input file is formatted correctly and the numbers are
 * valid positive integers.
 * 
 * For example:
 * 
 * 
 * 3 5 10
 * 2 7 15
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out the series 1 through N replacing numbers divisible by X with “F”,
 * numbers divisible by Y with “B” and numbers divisible by both with “FB”.
 * Since the input file contains multiple sets of values, your output should
 * print out one line per set. Ensure that there are no trailing empty spaces in
 * each line you print.
 * 
 * 
 * 1 2 F 4 B F 7 8 F B 
 * 1 F 3 F 5 F B F 9 F 11 F 13 FB 15
 * 
 * CONSTRAINTS:
 * 
 * The number of test cases less than or equal 20 
 * "X" is in range [1, 20] 
 * "Y" is in range [1, 20]
 * "N" is in range [21, 100]
 * 
 * @author nchak2
 *
 */
public class FizzBuzz_1 {
	
	private static final int FIRST_DIVISOR_LOWER_LIMIT = 1;
	private static final int FIRST_DIVISOR_UPPER_LIMIT = 20;
	
	private static final int SECOND_DIVISOR_LOWER_LIMIT = 1;
	private static final int SECOND_DIVISOR_UPPER_LIMIT = 20;
	
	private static final int NUM_RANGE_LOWER_LIMIT = 21;
	private static final int NUM_RANGE_UPPER_LIMIT = 100;
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static final int MAX_NUM_TESTCASES = 20;
	
	
	public static void main(String[] args) {
		
		args = new String[1];
		args[0] = "C://fizz_buzz_input.txt";
		
		if (args != null && args.length >= 1) {
			File f = new File(args[0]);
			if (!f.exists()) {
				throw new RuntimeException("@Yirrrk!! Seems U have supplied a wrong file location @");
			}
			
			List<Integer[]> list = readFileIntoLines(f);
			
			//traverse over the formatted input and prepare the output
			for(Integer[] nums : list){
				for(int i = 1; i <= nums[2]; i++){
					if(i % nums[0] == 0 && i % nums[1] == 0){
						System.out.print("FB");
					}
					else if(i % nums[0] == 0){
						System.out.print("F");
					}
					else if(i % nums[1] == 0){
						System.out.print("B");
					}
					else
						System.out.print(i);
					
					if(i != nums[2])
						System.out.print(SPACE);
				}
				System.out.print(NEW_LINE);
			}
			
		}
		else
		 throw new RuntimeException("@Seems U forgot to provide the input file path as first argument@");
	}
	
	/**
	 * Read the into file from the given path and return the formatted input
	 * @param _f
	 * @return
	 */
	private static List<Integer[]> readFileIntoLines(File _f){
		List<Integer[]> list = new ArrayList<Integer[]>();
		Scanner sc = null;

		try {
			sc = new Scanner(_f);
			int count = 0;
			while (sc.hasNextLine()) {
				if(++count == MAX_NUM_TESTCASES + 1)
					break;
				
				String _l = sc.nextLine();
				if(_l != null){
					String[] numbers = _l.split("\\s+");
					if(numbers.length == 3){
						int fizz = Integer.valueOf(numbers[0]);
						int buzz = Integer.valueOf(numbers[1]);
						int range = Integer.valueOf(numbers[2]);
						
						if ((fizz >= FIRST_DIVISOR_LOWER_LIMIT && fizz <= FIRST_DIVISOR_UPPER_LIMIT)
								&& (fizz >= SECOND_DIVISOR_LOWER_LIMIT && fizz <= SECOND_DIVISOR_UPPER_LIMIT)
								&& (range >= NUM_RANGE_LOWER_LIMIT && range <= NUM_RANGE_UPPER_LIMIT)) {
							list.add(new Integer[]{fizz, buzz, range});
						}
						
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null)
				sc.close();
		}
		return list;
	}

}
