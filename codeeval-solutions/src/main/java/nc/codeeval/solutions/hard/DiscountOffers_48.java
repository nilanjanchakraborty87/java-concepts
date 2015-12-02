package nc.codeeval.solutions.hard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Our marketing department has just negotiated a deal with several local
 * merchants that will allow us to offer exclusive discounts on various products
 * to our top customers every day. The catch is that we can only offer each
 * product to one customer and we may only offer one product to each customer.
 * 
 * Each day we will get the list of products that are eligible for these special
 * discounts. We then have to decide which products to offer to which of our
 * customers. Fortunately, our team of highly skilled statisticians has
 * developed an amazing mathematical model for determining how likely a given
 * customer is to buy an offered product by calculating what we call the
 * "suitability score" (SS). The top-secret algorithm to calculate the SS
 * between a customer and a product is this:
 * 
 * 1. If the number of letters in the product's name is even then the SS is the
 * number of vowels (a, e, i, o, u, y) in the customer's name multiplied by 1.5.
 * 2. If the number of letters in the product's name is odd then the SS is the
 * number of consonants in the customer's name. 3. If the number of letters in
 * the product's name shares any common factors (besides 1) with the number of
 * letters in the customer's name then the SS is multiplied by 1.5.
 * 
 * Your task is to implement a program that assigns each customer a product to
 * be offered in a way that maximizes the combined total SS across all of the
 * chosen offers. Note that there may be a different number of products and
 * customers. You may include code from external libraries as long as you cite
 * the source.
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept as its only argument a path to a file. Each line
 * in this file is one test case. Each test case will be a comma delimited set
 * of customer names followed by a semicolon and then a comma delimited set of
 * product names. Assume the input file is ASCII encoded. For example (NOTE: The
 * example below has 3 test cases):
 * 
 * Jack Abraham,John Evans,Ted Dziuba;iPad 2 - 4-pack,Girl Scouts Thin
 * Mints,Nerf Crossbow 
 * 
 * Jeffery Lebowski,Walter Sobchak,Theodore Donald
 * Kerabatsos,Peter Gibbons,Michael Bolton,Samir Nagheenanajar;Half & Half,Colt
 * M1911A1,16lb bowling ball,Red Swingline Stapler,Printer paper,Vibe Magazine
 * Subscriptions - 40 pack 
 * 
 * 
 * Jareau Wade,Rob Eroh,Mahmoud Abdelkader,Wenyi
 * Cai,Justin Van Winkle,Gabriel Sinkin,Aaron Adelson;Batman No. 1,Football -
 * Official Size,Bass Amplifying Headphones,Elephant food - 1024 lbs,Three Wolf
 * One Moon T-shirt,Dom Perignon 2000 Vintage 
 * 
 * 
 * OUTPUT SAMPLE:
 * 
 * For each line of input, print out the maximum total score to two decimal
 * places. For the example input above, the output should look like this:
 * 
 * 21.00 
 * 83.50 
 * 71.25
 * 
 * @author nchak2
 *
 */
public class DiscountOffers_48 {
	
	private static final String COMMA = ",";

	public static void main(String[] args) {
		if(args != null && args.length >= 1){
			Scanner sc = null;
			try {
				sc = new Scanner(new File(args[0]));
				sc.nextLine();
				while(sc.hasNextLine()){
					String line = sc.nextLine().trim();
					String names[] = line.substring(0,  line.indexOf(';')).split(COMMA);
					String products[] = line.substring(line.indexOf(';') + 1).split(COMMA);
					
					for(String productName : products){
						String product = productName.replaceAll("[^a-zA-z]", "");
						System.out.println(product);
						float SS = 0;
						float SSsum = 0;
						for(String customer : names){
							customer = customer.replaceAll("\\s+", "");
							//System.out.println(customer);
							int customerNameLength = customer.replaceAll("[^a-zA-z]", "").length();
							//System.out.println(customerNameLength);
							char[] letters = customer.toLowerCase().replaceAll("[^a-zA-z]", "").toCharArray();
							int vowels = 0;
							for(char letter : letters)
								if ((letter == 'a') || (letter == 'e')
								        || (letter == 'i') || (letter == 'o')
								        || (letter == 'u')) {
								      ++vowels;
								      }
							//System.out.println("Vowels " + vowels);
							int consonants = customerNameLength - vowels;
							//System.out.println("Consonants " +consonants);
							if(product.length() % 2 == 0){
								SS = (float) (vowels * 1.5);
							}
							else if(product.length() % 2 != 0)
								SS = consonants;
							
							if(commonFactor(customerNameLength, product.length()) > 1)
								SS = (float) (SS * 1.5);
							
							System.out.println("Suitable Score->" + SS);
							SSsum = SSsum + SS; 
						}
						System.out.println(SSsum);
						//break;
					}
					break;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				sc.close();
			}
			
		}
	}
	
	private static int commonFactor(int a, int b){
		if (b == 0) {
			return a;
		} else {
			return commonFactor(b, a % b);
		}
	}

}
