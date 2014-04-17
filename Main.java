package dbproj3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
			ArrayList<String> buildings = new ArrayList<String>();
			ArrayList<String> consumption = new ArrayList<String>();
			ArrayList<String> utility = new ArrayList<String>();
			Map<String, Integer> bdmap = new HashMap<String, Integer>();
			Map<String, Integer> utilmap = new HashMap<String, Integer>();
			
			int numRows = 0;

			while ((line = br.readLine()) != null){
				
				//keep track of total number of row (to help debug & to calculate support)
				numRows = numRows + 1;
				
				//separate by comma
				String[] naturalGas = line.split(splitBy);
				buildings.add(naturalGas[1]);
				//Using consumption in gigajoules (GJ)
				consumption.add(naturalGas[3]);
				utility.add(naturalGas[4]);
				
				//check if value type has already been accounted for
				Object check = bdmap.get(naturalGas[1]);
				//if it hasn't, add to list
				if(check == null){
					bdmap.put(naturalGas[1], 1);
				}else{
					//if it has, increase count by 1
					int count = bdmap.get(naturalGas[1]);
					count=count+1;
					bdmap.put(naturalGas[1], count);
				}
				
				check = utilmap.get(naturalGas[4]);
				//if it hasn't, add to list
				if(check == null){
					utilmap.put(naturalGas[4], 1);
				}else{
					//if it has, increase count by 1
					int count = utilmap.get(naturalGas[4]);
					count=count+1;
					utilmap.put(naturalGas[4], count);
				}
				
				
			}
			double support;	
			//First Pass for minimum support
			System.out.println("First Pass: Minimum Support");
			//building type
			for (Map.Entry<String, Integer> entry : bdmap.entrySet()) {
				int value = entry.getValue();
				support = ((double)value)/((double)numRows);
				if(support>min_sup){
					System.out.println("Building Type = "+entry.getKey()+", support = "+df.format(support));
				}
	 
			}
			//utility
			for (Map.Entry<String, Integer> entry : utilmap.entrySet()) {
				int value = entry.getValue();
				support = ((double)value)/((double)numRows);
				if(support>min_sup){
					System.out.println("Utility Name = "+entry.getKey()+", support = "+df.format(support));
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
