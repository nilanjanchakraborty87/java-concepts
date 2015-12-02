package nc.codeeval.solutions.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * You will be given a hexadecimal (base 16) number. Convert it into decimal
 * (base 10).
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept as its first argument a path to a filename. Each
 * line in this file contains a hex number. You may assume that the hex number
 * does not have the leading 'Ox'. Also all alpha characters (a through f) in
 * the input will be in lowercase. E.g.
 * 
 * 9f 
 * 11 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out the equivalent decimal number. E.g.
 * 
 * 159 
 * 17
 * 
 * @author nchak2
 *
 */
public class Hex2Decimal_19 {

	private static final Map<Character, Integer> HEX_DIGIT_MAPPER = new HashMap<Character, Integer>();

	static {
		HEX_DIGIT_MAPPER.put('a', 10);
		HEX_DIGIT_MAPPER.put('b', 11);
		HEX_DIGIT_MAPPER.put('c', 12);
		HEX_DIGIT_MAPPER.put('d', 13);
		HEX_DIGIT_MAPPER.put('e', 14);
		HEX_DIGIT_MAPPER.put('f', 15);
	}

	public static void main(String[] args) {
		if (args != null && args.length >= 1) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
				String ln = null;
				while ((ln = br.readLine()) != null) {
					char[] digits = ln.toCharArray();
					int dec = 0;
					int length = digits.length;
					for (int i = 0; i < digits.length; i++) {
						int digit = Character.getNumericValue(digits[i]);
						int pow = length - (i + 1);
						if(digit > 9)
							digit = HEX_DIGIT_MAPPER.get(digits[i]);
						
						dec = dec + digit * (int)Math.pow(16, pow);
					}
					System.out.println(dec);
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
