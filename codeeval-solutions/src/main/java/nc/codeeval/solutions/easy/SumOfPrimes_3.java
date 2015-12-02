package nc.codeeval.solutions.easy;

/**
 * Write a program which determines the sum of the first 1000 prime numbers.
 * 
 * @author nchak2
 *
 */
public class SumOfPrimes_3 {

	private static final int UPPER_BOUND = 1000;

	public static void main(String[] args) {

		int i = 1;
		int count = 1;
		long sum = 0;

		while (count <= UPPER_BOUND) {
			++i;

			boolean mePrime = true;

			for (int x = 2; x <= i / 2; x++) {
				if (i % x == 0) {
					mePrime = false;
					break;
				}
			}

			if (mePrime) {
				count++;
				sum = sum + i;
			}
		}

		System.out.println(sum);
	}

}
