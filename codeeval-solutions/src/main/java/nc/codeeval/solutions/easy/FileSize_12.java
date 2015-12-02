package nc.codeeval.solutions.easy;

import java.io.File;

public class FileSize_12 {
	
	public static void main(String[] args) {
		if(args != null && args.length >= 1){
			File f = new File(args[0]);
			System.out.println(f.length());
		}
		else
			System.err.println("Please provide a valid file");
	}

}
