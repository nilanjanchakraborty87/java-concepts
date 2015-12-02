package nc.codeeval.solutions.hard;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * You want to send your friend a package with different things. Each thing you
 * put inside the package has such parameters as index number, weight and cost.
 * The package has a weight limit. Your goal is to determine which things to put
 * into the package so that the total weight is less than or equal to the
 * package limit and the total cost is as large as possible. You would prefer to
 * send a package which weights less in case there is more than one package with
 * the same price.
 * 
 * INPUT SAMPLE:
 * 
 * Your program should accept as its first argument a path to a filename. The
 * input file contains several lines. Each line is one test case. Each line
 * contains the weight that the package can take (before the colon) and the list
 * of things you need to choose. Each thing is enclosed in parentheses where the
 * 1st number is a thing's index number, the 2nd is its weight and the 3rd is
 * its cost. E.g.
 * 
 * 81 : (1,53.38,$45) (2,88.62,$98) (3,78.48,$3) (4,72.30,$76) (5,30.18,$9)
 * (6,46.34 ,$48) 
 * 8 : (1,15.3,$34) 
 * 75 : (1,85.31,$29) (2,14.55,$74) (3,3.98,$16)
 * (4,26.24,$55) (5,63.69,$52) (6,76.25 ,$75) (7,60.02,$74) (8,93.18,$35)
 * (9,89.95,$78) 
 * 56 : (1,90.72,$13) (2,33.80,$40) (3,43.15,$10) (4,37.97,$16)
 * (5,46.81,$36) (6,48.77 ,$79) (7,81.80,$45) (8,19.36,$79) (9,6.76,$64)
 * 
 * 
 * OUTPUT SAMPLE:
 * 
 * For each set of things that you put into the package provide a list (items
 * index numbers are separated by comma). E.g.
 * 
 * 4
 * - 
 * 2,7 
 * 8,9
 * 
 * CONSTRAINTS:
 * 
 * 1) Max weight that a package can take is less than or equal 100 
 * 2) There might be up to 15 items you need to choose from 
 * 3) Max weight and cost of an item is less than or equal 100
 * 
 * @author nchak2
 *
 */

@SuppressWarnings("all")
public class PackageProblem_114 {
	
	private static final String NEW_LINE = "\n";
	private static final int MAX_NUMBER_OF_ITEMS = 15;
	
	// holds the each Package specific Items found from the test cases and the maximum weight a package can hold
	private static class PkgSpec {
		private int weightCapacity;
		private List<PkgItem> items;
		
		PkgSpec(int _maxweight){
			this.weightCapacity = _maxweight;
			this.items = new ArrayList<PkgItem>();
		}
		
		public void addItemToPkgSpec(int index, int weight, int cost){
			this.items.add(new PkgItem(index, weight, cost));
		}
		
		static class PkgItem{
			int index;
			int weight;
			int cost;
			
			PkgItem(int index, int weight, int cost) {
				super();
				this.index = index;
				this.weight = weight;
				this.cost = cost;
			}
			
			
		}
	}
	
	private static class Pkg {
		List<Integer> indexes = new ArrayList<Integer>();
		int totalWeight;
		int totalCost;
		
		/**
		 * Add each item to this package
		 * @param index
		 * @param weight
		 * @param cost
		 */
		public void addItemstoPackage(int index, int weight, int cost){
			indexes.add(index);
			totalWeight = totalWeight + weight;
			totalCost = totalCost + cost;
		}
		
		/**
		 * Add each item to this package
		 * @param index
		 * @param weight
		 * @param cost
		 */
		public void addItemstoPackage(List<Integer> indxs, int totalWeight, int totalCost){
			this.indexes.addAll(indxs);
			this.totalWeight = totalWeight;
			this.totalCost = totalCost;
		}
		
		@Override
		public String toString() {
			return indexes.size() > 0 ? indexes.toString().replace("[", "").replace("]", "")
		            .replace(", ", ",") : "-";
		}
	}
	
	public static void main(String[] args) {
		if (args != null && args.length >= 1) {
			File f = new File(args[0]);
			if (!f.exists()) {
				throw new RuntimeException("@Yirrrk!! Seems U have supplied a wrong file location @");
			}
			List<String> lines = readFileIntoLines(f);
			List<PkgSpec> specs = processInputLines(lines);
			
			List<Pkg> optimalPkgs = new ArrayList<Pkg>();
			for(PkgSpec spec : specs)
				optimalPkgs.add(chooseMostSuitablePkg(spec));
			
			//prints the suitable packages
			printOptimalPackages(optimalPkgs);
		}
		else
		 throw new RuntimeException("@Seems U forgot to provide the input file path as first argument@");

	}
	
	/**
	 * 
	 * @param _pkgs
	 */
	private static void printOptimalPackages(List<Pkg> _pkgs){
		StringBuilder builder = new StringBuilder();
		for(Pkg pkg : _pkgs)
			builder.append(pkg).append(NEW_LINE);
		
		System.out.println(builder.toString().length() == 0 ? "@OOOps! Seems something went wrong. Let's try again@" : builder);
	}
	
	/**
	 * retrieve most suitable pkg
	 * @return
	 */
	private static Pkg chooseMostSuitablePkg(PkgSpec spec){
		List<PkgSpec.PkgItem> items = spec.items;
		
		Pkg bestPkg = new Pkg();
		
		if(items.size() == 0 || spec.weightCapacity > 10000)
			return bestPkg; //returns the empty Pkg itself
		
		
		int pkgCost = 0, pkgWeight = spec.weightCapacity;
		List<Integer> itemIndexes = null;
		
		//finds the most suitable package contents
		for(int i=0; i<items.size(); i++){
			for(int j=i; j<items.size(); j++){
				PkgSpec.PkgItem item = items.get(j);
				
				int tempWeight = 0;
				int tempCost = 0;
				boolean packaged = false;
				List<Integer> tempIds = new ArrayList<Integer>();
				
				if(item.weight <= spec.weightCapacity){
					tempWeight = item.weight;
					tempCost = item.cost;
					tempIds.add(item.index);
				}
				else 
					continue;
                
				//Traverse over rest of the items available in the list
				for(int k=i; k<items.size(); k++){
					if(k != j){
						PkgSpec.PkgItem cItem = items.get(k);
						if((tempWeight + cItem.weight) <= spec.weightCapacity){
							tempWeight +=cItem.weight;
							tempCost +=cItem.cost;
							tempIds.add(cItem.index);
						}
					}
				}
				
				if(tempCost > pkgCost || ((tempCost == pkgCost) && (tempWeight < pkgWeight))){
					pkgCost = tempCost;
					pkgWeight = tempWeight;
					itemIndexes = tempIds;
				}
				
			}
		}
		
		if(itemIndexes!= null){
			Collections.sort(itemIndexes);
			bestPkg.addItemstoPackage(itemIndexes, pkgWeight, pkgCost);
		}

		return bestPkg;
	}
	
	
	
	/**
	 * this method transforms all the input lines into custom object to make future computations more convenient
	 * @param _lines
	 * @return
	 */
	private static List<PkgSpec> processInputLines(List<String> _lines){
		
		List<PkgSpec> specs = new ArrayList<PkgSpec>();
		
		//Won't validate any line considering that each line will be well formed according to the given instruction 
		for(String ln : _lines){
			
			String maxWeight = ln.split(":")[0].trim();
			String[] items = ln.split(":")[1].trim().split("\\s+(?![^\\(]*\\))");
			//Multiplying 100 to each weight to forcefully convert them to Integer to make calculations more convenient
			PkgSpec spec = new PkgSpec(Integer.valueOf(maxWeight) * 100);
			
			//iterates over each item, processing them and putting them into the PkgSpec
			int itemCounter = 0;
			for(String item : items){
				itemCounter++;
				//Will consider maximum 15 items
				if(itemCounter == MAX_NUMBER_OF_ITEMS)
					break;
				item = item.substring(item.indexOf('(') + 1, item.indexOf(')')).trim();
				Scanner sc = new Scanner(item);
				sc.useDelimiter(",");
				int index = Integer.valueOf(sc.next().trim());
				int weight = (int) (Float.valueOf(sc.next().trim()) * 100);
				int cost = Integer.valueOf(sc.next().substring(1).trim()); //removing the leading $ character 
				//will consider only those items which have weight and cost <= 100
				if(weight <= 100*100 && cost <= 100)
					spec.addItemToPkgSpec(index, weight, cost);
			}
			specs.add(spec);
		}
		return specs;
	}

	/**
	 * Accepts the file path, reads the entire file line by line and returns the Lines into a
	 * list
	 * 
	 * @param _file
	 * @return
	 */
	private static List<String> readFileIntoLines(File _file) {

		List<String> lines = new ArrayList<String>();
		Scanner sc = null;

		try {
			sc = new Scanner(_file);
			while (sc.hasNextLine()) {
				String _l = sc.nextLine();
				if(_l != null)
					lines.add(_l.trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null)
				sc.close();
		}

		return lines;
	}
}
