package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * You are given a sorted list of numbers with duplicates. Print out the sorted
 * list with duplicates removed.
 * 
 * INPUT SAMPLE:
 * 
 * File containing a list of sorted integers, comma delimited, one per line.
 * E.g.
 * 
 * 1,1,1,2,2,3,3,4,4 
 * 2,3,4,5,5 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out the sorted list with duplicates removed, one per line. E.g.
 * 
 * 
 * 1,2,3,4 
 * 2,3,4,5
 * 
 * @author nchak2
 *
 */
public class UniqiueElements_13 {
	
	public static void main(String[] args) {
		if(args != null && args.length >= 1){
			try{
				Scanner sc = new Scanner(new File(args[0]));
				while(sc.hasNextLine()){
					String _line = sc.nextLine().trim();
					Set<String> uniqueElements = new TreeSet<String>(new Comparator<String>() {

						public int compare(String o1, String o2) {
							Integer x1 = Integer.valueOf(o1);
							Integer x2 = Integer.valueOf(o2);
							return x1.compareTo(x2);
						}
					});
					String[] elements = _line.split(",");
					uniqueElements.addAll(Arrays.asList(elements));
					System.out.println(uniqueElements.toString().replace("[", "").replace("]", "").replaceAll(", ", ","));
				}
				sc.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
