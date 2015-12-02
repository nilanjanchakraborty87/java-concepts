package nc.codeeval.solutions.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Given a string write a program to convert it into lowercase.
 * 
 * INPUT SAMPLE:
 * 
 * The first argument will be a path to a filename containing sentences, one per
 * line. You can assume all characters are from the english language. E.g.
 * 
 * HELLO CODEEVAL 
 * This is some text OUTPUT SAMPLE:
 * 
 * Print to stdout, the lowercase version of the sentence, each on a new line.
 * E.g.
 * 
 * hello codeeval 
 * this is some text
 * 
 * @author nchak2
 *
 */
public class LowerCase_7 {
	
	public static void main(String[] args) {
		if(args != null && args.length >=1 ){
			try{
				BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
				String ln = null;
				while((ln = br.readLine()) != null){
					System.out.println(ln.toLowerCase());
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
