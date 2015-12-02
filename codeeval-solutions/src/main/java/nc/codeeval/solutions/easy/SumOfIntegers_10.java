package nc.codeeval.solutions.easy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SumOfIntegers_10 {

	public static void main(String[] args) {
		if(args != null && args.length >=1 ){
			try{
				BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
				String ln = null;
				int sum = 0;
				while((ln = br.readLine()) != null){
					sum = sum + Integer.valueOf(ln.trim());
				}
				System.out.println(sum);
				br.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		else
			throw new RuntimeException("Please provide the input file complete path as the first argument");
	}

}
