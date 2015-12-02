package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.Scanner;

/**
 * Write a program which reverses the words in an input sentence.
 * 
 * INPUT SAMPLE:
 * 
 * The first argument is a file that contains multiple sentences, one per line.
 * Empty lines are also possible.
 * 
 * For example:
 * 
 * 
 * Hello World 
 * Hello CodeEval 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print to stdout each sentence with the reversed words in it, one per line.
 * Empty lines in the input should be ignored. Ensure that there are no trailing
 * empty spaces in each line you print.
 * 
 * For example:
 * 
 * 
 * World Hello 
 * CodeEval Hello
 * 
 * @author nchak2
 *
 */
public class ReverseWords_4 {

	private static final String SPACE = " ";

	public static void main(String[] args) {
		if (args != null && args.length >= 1) {
			Scanner s = null;
			try {
				File f = new File(args[0]);
				s = new Scanner(f);

				while (s.hasNextLine()) {
					String line = s.nextLine();
					if (line != null && line.length() > 0) {
						String[] words = line.split("\\s+");
						String str = "";
						for (int i = words.length - 1; i >= 0; i--) {
							str = str + words[i] + SPACE;
						}
						System.out.println(str.trim());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (s != null)
					s.close();
			}
		} else {
			System.err.println("No input file found!!");
		}
	}

}
