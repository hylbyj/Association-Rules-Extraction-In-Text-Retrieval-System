package dbproj3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {

		//need to use more generic path
		String csvFile = "/Users/arianagiorgi/Documents/workspace/dbproj3/GasTable.csv";
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		
		//will be from parameters
		double min_sup = 0.3;
		double min_conf = 0.5;
		
		DecimalFormat df = new DecimalFormat("#0.00");
		
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
			Map<String, Integer> tempmap = new HashMap<String, Integer>();

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
			
			//-----Prints out the variables that meet minimum support requirement
			double support;	
			for (Map.Entry<String, Integer> entry : tempmap.entrySet()) {
				int value = entry.getValue();
				support = ((double)value)/((double)numRows);
				if(support>=min_sup){
					System.out.println("Variable = "+entry.getKey()+", support = "+df.format(support));
				}
			}
			
			
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
