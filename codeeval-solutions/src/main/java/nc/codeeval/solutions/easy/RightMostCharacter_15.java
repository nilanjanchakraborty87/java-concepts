package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.Scanner;

/**
 * You are given a string 'S' and a character 't'. Print out the position of the
 * rightmost occurrence of 't' (case matters) in 'S' or -1 if there is none. The
 * position to be printed out is zero based.
 * 
 * INPUT SAMPLE:
 * 
 * The first argument will ba a path to a filename, containing a string and a
 * character, comma delimited, one per line. Ignore all empty lines in the input
 * file. 
 * 
 * E.g. 
 * 
 * Hello World,r 
 * Hello CodeEval,E 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out the zero based position of the character 't' in string 'S', one per
 * line. Do NOT print out empty lines between your output. E.g.
 * 
 * 8 
 * 10
 * 
 * @author nchak2
 *
 */
public class RightMostCharacter_15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args != null && args.length >= 1) {
			try {
				Scanner sc = new Scanner(new File(args[0]));
				while (sc.hasNextLine()) {
					String _line = sc.nextLine().trim();
					String elements[] = _line.split(",");
					if(elements.length != 2){
						System.out.println("");
						continue;
					}
					String str = elements[0];
					char c = elements[1].toCharArray()[0];
					System.out.println(str.lastIndexOf(c));
				}
				sc.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
