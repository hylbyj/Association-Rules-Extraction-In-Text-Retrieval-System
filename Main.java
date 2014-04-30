import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
        
		//locate csv file in bin
		String csvFile = "bin/GasTable.csv";
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
			Map<List<String>, Integer> tempmap2 = new HashMap<List<String>, Integer>();
			ArrayList<ArrayList<String>> templist = new ArrayList<ArrayList<String>>();
			ArrayList<List<String>> firstpass = new ArrayList<List<String>>();
			Map<List<String>, Double> supResults = new HashMap<List<String>, Double>();
			
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
			
			//minimum support, first pass
			double support;
			for (Map.Entry<String, Integer> entry : tempmap.entrySet()) {
				int value = entry.getValue();
				support = ((double)value)/((double)numRows);
				if(support>=min_sup){
					//keep track of variables that meet first pass of min support
					List<String> result = new ArrayList<String>();
					result.add(entry.getKey());
					firstpass.add(result);
					supResults.put(result, support);
				}
			}
			
			//----Final/reiterative pass
			//while there are still frequent item sets to be tested...
			while(firstpass.size() != 0){
				for(int i=0; i<firstpass.size(); i++){
					for(int j=i; j<firstpass.size(); j++){
						List<String> listi = firstpass.get(i);
						List<String> listj = firstpass.get(j);
						for(int k=0; k<listj.size(); k++){
							boolean check = listi.contains(listj.get(k));
							if(check == false){
								ArrayList<String> group = new ArrayList<String>();
								for(int l=0; l<listi.size(); l++){
									group.add(listi.get(l));
								}
								group.add(listj.get(k));
								boolean contains = templist.contains(group);
								if(contains == false){
									templist.add(group);
								}
							}
						}
					}
				}
				
				firstpass.clear();
				
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
							Object check = tempmap2.get(list);
							if(check == null){
								tempmap2.put(list, 1);
							}else{
								int count = tempmap2.get(list);
								count=count+1;
								tempmap2.put(list, count);
							}
						}
					}
				}
                
				for (Map.Entry<List<String>, Integer> entry : tempmap2.entrySet()) {
					List<String> key = entry.getKey();
					int value = entry.getValue();
					support = ((double)value)/((double)numRows);
					if(support>=min_sup){
						//store item sets that meet minimum support and need to be tested again
						firstpass.add(key); //this value will determine if while loop is repeated
						supResults.put(key, support);
					}
				}
				//clear lists to prepare for next pass of while loop
				tempmap2.clear();
				templist.clear();
			}
			
			//sort values in supResults by support
			supResults = MapUtil.sortByValue(supResults);
			
			//print all item sets that meet min_sup
			out.println("==Frequent itemsets (min_sup="+df.format(min_sup*(100))+"%)");
			for (Map.Entry<List<String>, Double> entry : supResults.entrySet()) {
				List<String> keys = entry.getKey();
				out.print("[");
				for(int j=0; j<keys.size(); j++){
					if(j == keys.size()-1){
						out.print(keys.get(j) + "], ");
					}else{
						out.print(keys.get(j) + ", ");
					}
				}
				out.println(df.format(entry.getValue()*100)+"%");
			}
            
			//close out file
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
//class to sort the results by support
class MapUtil
{
    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
        new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        //sort
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
                         {
                             public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
                             {
                                 return (o1.getValue()).compareTo( o2.getValue() );
                             }
                         });
        //now put the list in descending order
        Collections.reverse(list);
        
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}