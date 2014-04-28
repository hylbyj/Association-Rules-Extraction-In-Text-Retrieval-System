import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
        
		//need to use more generic path
		String csvFile = "/Users/arianagiorgi/Documents/workspace/dbproj3/GasTable.csv";
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		
		//create output file
		PrintWriter out = new PrintWriter("output.txt");
		
		//will be from parameters
		double min_sup = 0.3;
		double min_conf = 0.5;
		
		DecimalFormat df = new DecimalFormat("#0");
		
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
			Map<String, Integer> tempmap = new HashMap<String, Integer>();
			ArrayList<ArrayList<String>> templist = new ArrayList<ArrayList<String>>();
			ArrayList<String> firstpass = new ArrayList<String>();
			Map<List<String>, Integer> secondpass = new HashMap<List<String>, Integer>();
			ArrayList<List<String>> supResults = new ArrayList<List<String>>();
			
			int numRows = 0;
            
			while ((line = br.readLine()) != null){
				
				//keep track of total number of row (to help debug & to calculate support)
				numRows = numRows + 1;
				
				//separate by comma
				String[] naturalGas = line.split(splitBy);
				
				//store into a map to compare variables within a row
				List<String> valueSet = new ArrayList<String>();
				for(int i=0; i<naturalGas.length; i++){
					valueSet.add(naturalGas[i]);
				}
				
				map.put(numRows, valueSet);
				
			}
			
			//-----This is to keep count of how often variables are used, to calculate minimum support
			//loop every row
			for (Map.Entry<Integer, List<String>> entry : map.entrySet()){
				List<String> valueSet = entry.getValue();
				//loop every column
				for(int i=0; i<valueSet.size(); i++){
					String x = valueSet.get(i);
					Object check = tempmap.get(x);
					//don't consider the "unknown" answers
					if(!x.equals("Unknown")){
						//if it hasn't been accounted for, add to list
						if(check == null){
							tempmap.put(x, 1);
						}else{
							//if it has, increase count by 1
							int count = tempmap.get(x);
							count=count+1;
							tempmap.put(x, count);
						}
					}
				}
			}
			
			//-----Prints out the variables that meet minimum support requirement
			//minimum support, first pass
			double support;
			out.println("==Frequent itemsets (min_sup="+df.format(min_sup*(100))+"%)");
			for (Map.Entry<String, Integer> entry : tempmap.entrySet()) {
				int value = entry.getValue();
				support = ((double)value)/((double)numRows);
				if(support>=min_sup){
					//keep track of variables that meet first pass of min support
					firstpass.add(entry.getKey());
					out.println("["+entry.getKey()+"], "+df.format(support*100)+"%");
				}
			}
			
			//-----Start of the second pass of support requirement, testing pairs of items from first pass
			for (Map.Entry<Integer, List<String>> entry : map.entrySet()){
				List<String> valueSet = entry.getValue();
				ArrayList<String> list = new ArrayList<String>();
				int count = 0;
				//loop every column
				for(int i=0; i<valueSet.size(); i++){
					String x = valueSet.get(i);
					//loop through firstpass to see if row contains more than one item
                listloop:
					for(int j=0; j<firstpass.size(); j++){
						if(firstpass.get(j).equals(x)){
							count = count + 1; //to keep track of how many items the row has in common
							list.add(x);
							break listloop;
						}
					}
				}
				//if row has 2 or more items that match, these items are possible item sets
				if(count >= 2){
					boolean check = templist.contains(list);
					if(check == false){
						templist.add(list);
					}
				}
			}
			
			//loop through groups of variables to check for existence in rows
			for (int i=0; i<templist.size(); i++){
				List<String> list = templist.get(i);
				//loop rows
				for (Map.Entry<Integer, List<String>> entry : map.entrySet()){
					boolean good = true;
					List<String> valueSet = entry.getValue();
                listloop:
					for(int j=0; j<list.size(); j++){
						boolean check = valueSet.contains(list.get(j));
						//if one of the items does not exist in the row, the group doesn't exist in the row
						if(check == false){
							good = false;
							break listloop;
						}
					}
					//if all items are in the row, count the row as an occurrence of the item set
					if(good==true){
						Object check = secondpass.get(list);
						if(check == null){
							secondpass.put(list, 1);
						}else{
							int count = secondpass.get(list);
							count=count+1;
							secondpass.put(list, count);
						}
					}
				}
			}
            
			for (Map.Entry<List<String>, Integer> entry : secondpass.entrySet()) {
				List<String> key = entry.getKey();
				int value = entry.getValue();
				support = ((double)value)/((double)numRows);
				if(support>=min_sup){
					//store item sets that meet minimum support
					supResults.add(key);
					out.print("[");
					for(int i=0; i<key.size(); i++){
						if(i == key.size() - 1){
							out.print(key.get(i));
						}else{
							out.print(key.get(i) + ", ");
						}
					}
					out.println("], "+df.format(support*100)+"%");
				}
			}
			
			out.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
