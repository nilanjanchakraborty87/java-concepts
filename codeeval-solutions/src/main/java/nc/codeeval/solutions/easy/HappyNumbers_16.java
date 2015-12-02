package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HappyNumbers_16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args != null && args.length >= 1) {
			try {
				Scanner sc = new Scanner(new File(args[0]));
				while (sc.hasNextLine()) {
					String _line = sc.nextLine().trim();
					System.out.println(isHappy(_line, new HashSet<Integer>()));
				}
				sc.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private static int isHappy(String number, Set<Integer> happySequence) {
		if (number.equals("1"))
			return 1;
		else {
			int sequence = 0;
			char[] digits = number.toCharArray();
			for (char ch : digits) {
				int digit = Character.getNumericValue(ch);
				sequence = sequence + digit * digit;
			}
			if (happySequence.contains(sequence))
				return 0;

			happySequence.add(sequence);
			return isHappy(Integer.toString(sequence), happySequence);
		}
	}
	
}
