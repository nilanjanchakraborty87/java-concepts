package nc.codeeval.solutions.easy;

/**
 * Write a program which determines the largest prime palindrome less than 1000.
 * 
 * @author nchak2
 *
 */
public class LargestPrimePalindrome_2 {

	private static final int UPPER_BOUND = 1000;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		boolean isPrimePalindrome = false;
		int i = UPPER_BOUND - 1;
		for(; i >= 1; i--){
			int reverse = Integer.valueOf((new StringBuilder(Integer.toString(i))).reverse().toString());
			//checking whether palindrome, if so then we will check for whether the number is a prime number
			if(i == reverse){
				boolean isPrime = true;
				for(int x = 2; x <= i / 2; x++){
					if(i % x == 0){
						isPrime = false;
						break;
					}
				}
				if(isPrime){
					isPrimePalindrome = true;
					break;
				}
			}
		}
		
		if(isPrimePalindrome){
			System.out.println(i);
			System.out.println("Total time elapsed " + (System.currentTimeMillis() - startTime) +"ms");
		}
		
	}

}
