package nc.codeeval.solutions.easy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Credits: This problem appeared in the Facebook Hacker Cup 2013 Hackathon.
 * 
 * When John was a little kid he didn't have much to do. There was no internet,
 * no Facebook, and no programs to hack on. So he did the only thing he could...
 * he evaluated the beauty of strings in a quest to discover the most beautiful
 * string in the world.
 * 
 * Given a string s, little Johnny defined the beauty of the string as the sum
 * of the beauty of the letters in it. The beauty of each letter is an integer
 * between 1 and 26, inclusive, and no two letters have the same beauty. Johnny
 * doesn't care about whether letters are uppercase or lowercase, so that
 * doesn't affect the beauty of a letter. (Uppercase 'F' is exactly as beautiful
 * as lowercase 'f', for example.)
 * 
 * You're a student writing a report on the youth of this famous hacker. You
 * found the string that Johnny considered most beautiful. What is the maximum
 * possible beauty of this string?
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept as its first argument a path to a filename. Each
 * line in this file has a sentence. E.g.
 * 
 * 
 * ABbCcc 
 * Good luck in the Facebook Hacker Cup this year! 
 * Ignore punctuation, please :) 
 * Sometimes test cases are hard to make up.
 * So I just go consult Professor Dalves 
 * 
 * OUTPUT SAMPLE:
 * 
 * Print out the maximum beauty for the string. E.g.
 * 
 * 
 * 152 
 * 754 
 * 491 
 * 729 
 * 646
 * 
 * @author nchak2
 *
 */
public class BeautifulStrings_21 {

	public static void main(String[] args) {
		if (args != null && args.length >= 1) {
			try {
				Scanner sc = new Scanner(new File(args[0]));
				while (sc.hasNextLine()) {
					String _line = sc.nextLine().trim().toLowerCase();
					_line = _line.replaceAll("[^a-z]", "");
					char[] letters = _line.toCharArray();
					List<Integer> occurences = new ArrayList<Integer>();

					/** First approach with array **/
					Arrays.sort(letters);
					String sortedString = new String(letters);
					for(int i = 0; i < letters.length;){
						int frequency = (sortedString.lastIndexOf(letters[i]) - sortedString.indexOf(letters[i])) + 1;
						occurences.add(frequency);
						i += frequency;
					}
					
					
					/** Second approach with map **/
					/*
					Map<Character, Integer> letterFrequencies = new HashMap<Character, Integer>();
					for(char ch : letters){
						Integer freq = letterFrequencies.get(ch);
						freq = (freq == null) ? 1 : freq + 1;
						letterFrequencies.put(ch, freq);
					}
					occurences.addAll(letterFrequencies.values());
					*/
					
					/** Third Approach with Array again **/
					/*
					for(int i = 65, j = 97; i <= 90 && j <= 123; i++, j++ ){
						String str = _line.replaceAll("[^" + (char)i + (char)j + "]", "");
						occurences.add(str.length());
					}
					*/
					
					Collections.sort(occurences, new Comparator<Integer>() {

						public int compare(Integer o1, Integer o2) {
							// TODO Auto-generated method stub
							return o2.compareTo(o1);
						}
					});
					int beautifulSum = 0;
					int i = 26;
					for(Integer x : occurences){
						beautifulSum = beautifulSum + x * i;
						--i;
					}
					
					System.out.println(beautifulSum);
				}
				sc.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
