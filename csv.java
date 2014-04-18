//package dbproj3;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class csv {

	public static void main(String[] args) throws IOException{
    //read csv file to find the utility column
		String csvFilename = "/Users/hrj/Downloads/GasTable.csv";
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		String csv = "/Users/hrj/Downloads/GasTable0.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv));
		String[] row = null;
		String[] writerow = null;
		List<String[]> data = new ArrayList<String[]>();
		while((row = csvReader.readNext()) != null) {
		    System.out.println(row[0]
		              + " # " + row[1]);
		    int people = 0;
		    people = Integer.parseInt(row[6]);
		    //for the categraries of utility
		    Integer utility = 0;
		   
		    utility = Integer.valueOf(row[3]);
		    
		    String utilitycat = null;
		    if (utility == 0) utilitycat = "Unknown";
		    if (utility>0 && utility<100000) utilitycat="u1";
		    if (utility>=100000 && utility<200000) utilitycat="u2";
		    if (utility>=200000 && utility<300000) utilitycat="u3";
		    if (utility>=300000 && utility<400000) utilitycat="u4";
		    if (utility>=400000 && utility<500000) utilitycat="u5";
		    if (utility>=500000 && utility<600000) utilitycat="u6";
		    if (utility>=600000 && utility<700000) utilitycat="u7";
		    if (utility>=700000 && utility<800000) utilitycat="u8";
		    if (utility>=800000 && utility<900000) utilitycat="u9";
		    if (utility>=900000 && utility<1000000) utilitycat="u10";
		    if (utility>=1000000 && utility<1100000) utilitycat="u11";
		    if (utility>=1100000 && utility<1200000) utilitycat="u12";
		    if (utility>=1200000 && utility<1300000) utilitycat="u13";
		    if (utility>=1300000 && utility<1400000) utilitycat="u14";
		    if (utility>=1400000 && utility<1500000) utilitycat="u15";
		    if (utility>=1500000 && utility<1600000) utilitycat="u16";
		    if (utility>=1600000 && utility<1700000) utilitycat="u17";
		    if (utility>=1700000 && utility<1800000) utilitycat="u18";
		    if (utility>=1800000 && utility<1900000) utilitycat="u19";
		    if (utility>=1900000 && utility<2000000) utilitycat="u20";
		    if (utility>=2000000) utilitycat="u21";
		   // writerow[0] = row [0];
		    //writerow[1] = row [1];
		    //for the female percentage
		    double female = 0;
		    female = new Double(row[8]);
		    String femalepercentage = null;
		    if (female == 1000 || people == 0) femalepercentage = "Unknown";
		    if (female >= 0 && female <=0.1 && people != 0) femalepercentage = "f1";
		    if (female > 0.1 && female <=0.2) femalepercentage = "f2";
		    if (female > 0.2 && female <=0.3) femalepercentage = "f3";
		    if (female > 0.3 && female <=0.4) femalepercentage = "f4";
		    if (female > 0.4 && female <=0.5) femalepercentage = "f5";
		    if (female > 0.5 && female <=0.6) femalepercentage = "f6";
		    if (female > 0.6 && female <=0.7) femalepercentage = "f7";
		    if (female > 0.7 && female <=0.8) femalepercentage = "f8";
		    if (female > 0.8 && female <=0.9) femalepercentage = "f9";
		    if (female > 0.9 && female <=1.0) femalepercentage = "f10";
		    //for the male percentage 
		    double male = 0;
		    male = new Double(row[10]);
		    String malepercentage = null;
		    if (male == 1000 || people == 0) malepercentage = "Unknown";
		    if (male >= 0 && male <=0.1 && people!=0) malepercentage = "m1";
		    if (male > 0.1 && male <=0.2) malepercentage = "m2";
		    if (male > 0.2 && male <=0.3) malepercentage = "m3";
		    if (male > 0.3 && male <=0.4) malepercentage = "m4";
		    if (male > 0.4 && male <=0.5) malepercentage = "m5";
		    if (male > 0.5 && male <=0.6) malepercentage = "m6";
		    if (male > 0.6 && male <=0.7) malepercentage = "m7";
		    if (male > 0.7 && male <=0.8) malepercentage = "m8";
		    if (male > 0.8 && male <=0.9) malepercentage = "m9";
		    if (male > 0.9 && male <=1.0) malepercentage = "m10";
		    
		    //for the hispanic latino percentage
		    double hispanic = 0;
		    hispanic = new Double(row[18]);
		    String hispanicpercentage = null;
		    if (hispanic == 1000 || people == 0) hispanicpercentage = "Unknown";
		    if (hispanic >= 0 && hispanic <=0.1 && people !=0) hispanicpercentage = "h1";
		    if (hispanic > 0.1 && hispanic <=0.2) hispanicpercentage = "h2";
		    if (hispanic > 0.2 && hispanic <=0.3) hispanicpercentage = "h3";
		    if (hispanic > 0.3 && hispanic <=0.4) hispanicpercentage = "h4";
		    if (hispanic> 0.4 && hispanic <=0.5) hispanicpercentage = "h5";
		    if (hispanic > 0.5 && hispanic <=0.6) hispanicpercentage = "h6";
		    if (hispanic> 0.6 && hispanic <=0.7) hispanicpercentage = "h7";
		    if (hispanic > 0.7 && hispanic <=0.8) hispanicpercentage = "h8";
		    if (hispanic> 0.8 && hispanic<=0.9) hispanicpercentage = "h9";
		    if (hispanic> 0.9 && hispanic <=1.0) hispanicpercentage = "h10";
		    
		    //for the american indian percentage
		    double indian = 0;
		    indian = new Double(row[22]);
		    String indianpercentage = null;
		    if (indian== 1000 || people == 0) indianpercentage = "Unknown";
		    if (indian >= 0 && indian <=0.1 && people !=0) indianpercentage = "i1";
		    if (indian > 0.1 && indian <=0.2) indianpercentage = "i2";
		    if (indian > 0.2 && indian <=0.3) indianpercentage = "i3";
		    if (indian > 0.3 && indian <=0.4) indianpercentage = "i4";
		    if (indian> 0.4 && indian <=0.5) indianpercentage = "i5";
		    if (indian > 0.5 && indian <=0.6) indianpercentage = "i6";
		    if (indian> 0.6 && indian <=0.7) indianpercentage = "i7";
		    if (indian > 0.7 && indian <=0.8) indianpercentage = "i8";
		    if (indian> 0.8 && indian<=0.9) indianpercentage = "i9";
		    if (indian> 0.9 && indian <=1.0) indianpercentage = "i10";
		    
		    //Asian non hispanic
		    double asian = 0 ;
		    asian = new Double(row[24]);
		    String asianpercentage = null;
		    if (asian== 1000 || people == 0) asianpercentage = "Unknown";
		    if (asian >= 0 && asian <=0.1 && people !=0) asianpercentage = "a1";
		    if (asian > 0.1 && asian <=0.2) asianpercentage = "a2";
		    if (asian > 0.2 && asian <=0.3) asianpercentage = "a3";
		    if (asian > 0.3 && asian <=0.4) asianpercentage = "a4";
		    if (asian> 0.4 && asian <=0.5) asianpercentage = "a5";
		    if (asian > 0.5 && asian <=0.6) asianpercentage = "a6";
		    if (asian> 0.6 && asian <=0.7) asianpercentage = "a7";
		    if (asian > 0.7 && asian <=0.8) asianpercentage = "a8";
		    if (asian> 0.8 && asian<=0.9) asianpercentage = "a9";
		    if (asian> 0.9 && asian <=1.0) asianpercentage = "a10";
		    
		    //white not hispanic
		    double white = 0 ;
		    white = new Double (row[26]);
		    String whitepercentage = null;
		    if (white== 1000 || people == 0) whitepercentage = "Unknown";
		    if (white >= 0 && white <=0.1 && people !=0) whitepercentage = "w1";
		    if (white > 0.1 && white <=0.2) whitepercentage = "w2";
		    if (white > 0.2 && white <=0.3) whitepercentage = "w3";
		    if (white > 0.3 && white <=0.4) whitepercentage = "w4";
		    if (white> 0.4 && white <=0.5) whitepercentage = "w5";
		    if (white > 0.5 && white <=0.6) whitepercentage = "w6";
		    if (white> 0.6 && white <=0.7) whitepercentage = "w7";
		    if (white > 0.7 && white<=0.8) whitepercentage = "w8";
		    if (white> 0.8 && white<=0.9) whitepercentage = "w9";
		    if (white> 0.9 && white <=1.0) whitepercentage = "w10";
		    
		    //black not hispanic
		    double black = 0;
		    black = new Double (row[28]);
		    String blackpercentage = null;
		    if (black== 1000 || people == 0) blackpercentage = "Unknown";
		    if (black >= 0 && black <=0.1 && people !=0) blackpercentage = "b1";
		    if (black > 0.1 && black <=0.2) blackpercentage = "b2";
		    if (black > 0.2 && black <=0.3) blackpercentage = "b3";
		    if (black > 0.3 && black <=0.4) blackpercentage = "b4";
		    if (black> 0.4 && black <=0.5) blackpercentage = "b5";
		    if (black > 0.5 && black <=0.6) blackpercentage = "b6";
		    if (black> 0.6 && black <=0.7) blackpercentage = "b7";
		    if (black > 0.7 && black<=0.8) blackpercentage = "b8";
		    if (black> 0.8 && black<=0.9) blackpercentage = "b9";
		    if (black> 0.9 && black <=1.0) blackpercentage = "b10";
		    
		    //other ethnicity
		    double other = 0 ;
		    other = new Double (row[30]);
		    String otherpercentage = null;
		    if (other== 1000 || people == 0) otherpercentage = "Unknown";
		    if (other >= 0 && other <=0.1 && people !=0) otherpercentage = "o1";
		    if (other > 0.1 && other <=0.2) otherpercentage = "o2";
		    if (other > 0.2 && other <=0.3) otherpercentage = "o3";
		    if (other > 0.3 && other <=0.4) otherpercentage = "o4";
		    if (other> 0.4 && other <=0.5) otherpercentage = "o5";
		    if (other> 0.5 && other<=0.6) otherpercentage = "o6";
		    if (other> 0.6 && other<=0.7) otherpercentage = "o7";
		    if (other > 0.7 && other<=0.8) otherpercentage= "o8";
		    if (other> 0.8 && other<=0.9) otherpercentage = "o9";
		    if (other> 0.9 && other <=1.0) otherpercentage = "o10";
		    //permanent resident
		    double permanent = 0;
		    permanent = new Double (row [36]);
		    String permanentpercentage = null;
		    if (permanent== 1000 || people == 0) permanentpercentage = "Unknown";
		    if (permanent >= 0 && permanent <=0.1 && people !=0) permanentpercentage = "p1";
		    if (permanent > 0.1 && permanent <=0.2) permanentpercentage = "p2";
		    if (permanent > 0.2 && permanent <=0.3) permanentpercentage = "p3";
		    if (permanent > 0.3 && permanent <=0.4) permanentpercentage = "p4";
		    if (permanent> 0.4 && permanent <=0.5) permanentpercentage = "p5";
		    if (permanent> 0.5 && permanent<=0.6) permanentpercentage = "p6";
		    if (permanent> 0.6 && permanent<=0.7) permanentpercentage = "p7";
		    if (permanent > 0.7 && permanent<=0.8) permanentpercentage= "p8";
		    if (permanent> 0.8 && permanent<=0.9) permanentpercentage = "p9";
		    if (permanent> 0.9 && permanent <=1.0) permanentpercentage= "p10";
		    //us citizen
		    double citizen = 0 ;
		    citizen = new Double (row[38]);
		    String citizenpercentage = null;
		    if (citizen== 1000 || people == 0) citizenpercentage = "Unknown";
		    if (citizen >= 0 && citizen <=0.1 && people !=0) citizenpercentage = "c1";
		    if (citizen > 0.1 && citizen <=0.2) citizenpercentage = "c2";
		    if (citizen > 0.2 && citizen <=0.3) citizenpercentage = "c3";
		    if (citizen > 0.3 && citizen <=0.4) citizenpercentage = "c4";
		    if (citizen> 0.4 && citizen <=0.5) citizenpercentage = "c5";
		    if (citizen> 0.5 && citizen<=0.6) citizenpercentage = "c6";
		    if (citizen> 0.6 && citizen<=0.7) citizenpercentage = "c7";
		    if (citizen > 0.7 && citizen<=0.8) citizenpercentage= "c8";
		    if (citizen> 0.8 && citizen<=0.9) citizenpercentage= "c9";
		    if (citizen> 0.9 && citizen <=1.0) citizenpercentage= "c10";
		    
		    //receive public assistance 
		    double assistance = 0;
		    assistance = new Double (row[46]);
		    String assistancepercentage = null;
		    if (assistance== 1000 || people == 0) assistancepercentage = "Unknown";
		    if (assistance>= 0 && assistance <=0.1 && people !=0) assistancepercentage = "a1";
		    if (assistance > 0.1 && assistance<=0.2) assistancepercentage = "a2";
		    if (assistance > 0.2 && assistance <=0.3) assistancepercentage = "a3";
		    if (assistance > 0.3 && assistance<=0.4) assistancepercentage = "a4";
		    if (assistance> 0.4 && assistance <=0.5) assistancepercentage = "a5";
		    if (assistance> 0.5 && assistance<=0.6) assistancepercentage = "a6";
		    if (assistance> 0.6 && assistance<=0.7) assistancepercentage = "a7";
		    if (assistance > 0.7 && assistance<=0.8) assistancepercentage= "a8";
		    if (assistance> 0.8 && assistance<=0.9) assistancepercentage= "a9";
		    if (assistance> 0.9 && assistance <=1.0) assistancepercentage= "a10";
		    
		   data.add(new String[] {row[0],row[1],utilitycat,femalepercentage,malepercentage,hispanicpercentage,indianpercentage,asianpercentage,whitepercentage,blackpercentage,otherpercentage,permanentpercentage,citizenpercentage,assistancepercentage});
		   
		    
		}
		
		
		
		
		
		csvReader.close(); 
		
		
		 
		//
		//data.add(new String[] {"India", "New Delhi"});
		//data.add(new String[] {"United States", "Washington D.C"});
		//data.add(new String[] {"Germany", "Berlin"});
		 
		writer.writeAll(data);
		 
		writer.close();
		
	}
}