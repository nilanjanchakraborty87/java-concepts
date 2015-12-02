package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * You are given two sorted list of numbers (ascending order). The lists
 * themselves are comma delimited and the two lists are semicolon delimited.
 * Print out the intersection of these two sets.
 * 
 * INPUT SAMPLE:
 * 
 * File containing two lists of ascending order sorted integers, comma
 * delimited, one per line. 
 * 
 * E.g. 
 * 
 * 1,2,3,4;4,5,6 
 * 20,21,22;45,46,47
 * 7,8,9;8,9,10,11,12 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out the ascending order sorted intersection of the two lists, one per
 * line. Print empty new line in case the lists have no intersection. 
 * 
 * E.g. 
 * 
 * 4
 * 
 * 8,9
 * 
 * @author nchak2
 *
 */
public class SetIntersection_14 {

	public static void main(String[] args) {
		if (args != null && args.length >= 1) {
			try {
				Scanner sc = new Scanner(new File(args[0]));
				while (sc.hasNextLine()) {
					String _line = sc.nextLine().trim();
					Set<String> set1 = new TreeSet<String>(new Comparator<String>() {

						public int compare(String o1, String o2) {
							return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
						}
					});
					
					Set<String> set2 = new TreeSet<String>();
					
					String[] elems1 = _line.split(";")[0].split(",");
					String[] elems2 = _line.split(";")[1].split(",");
					
					set1.addAll(Arrays.asList(elems1));
					set2.addAll(Arrays.asList(elems2));
					set1.retainAll(set2);
					System.out.println(set1.toString().replace("[", "").replace("]", "").replaceAll(", ", ","));
				}
				sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
