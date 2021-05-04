package com.employees;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * FindCoupleWorkedTogetherLongestPeriod colleagues who have worked together on common projects for the longest time
 * 
 * Version 1.0
 *
 * 01.05.2021
 * 
 * Ivo Baklov
 */
public class CoupleWorkedTogetherLongestPeriod {
	//This is class with collegues
	ColleaguesByProjects collegues = new ColleaguesByProjects();
	private static final String COMMA = ",";
	private long longMyParse(String str) {
		long i=-1;
		try  
	    {   
	          i = Integer.parseInt(str.trim());   
	    }  
	    catch (NumberFormatException nfe)  
	    {  
	          System.out.println("NumberFormatException: " + nfe.getMessage());  
	    }  
		return i;
	}
	private Function<String, ColleaguesByProjects> mapToAllColleagues = (line) -> {

		  String[] p = line.split(COMMA);// a CSV has comma separated lines
//in variable collegues will be saved info row by row from the file.
		  ColleaguesByProjects collegues = new ColleaguesByProjects();
		  if (p[0] != null && p[0].trim().length() > 0) {
			  long empID= longMyParse(p[0]);
			  if (empID!=-1){
				  collegues.setEmpID(empID);
			  }		   
		  }
		  if (p[1] != null && p[1].trim().length() > 0) {
			  long projectID= longMyParse(p[1]);
			  if (projectID!=-1){
				  collegues.setProjectID(projectID);
			  }		   
		  }
		  if (p[2] != null && p[2].trim().length() > 0) {
			  if ("NULL".compareTo(p[2].trim())==0) {
				  collegues.setDateFrom(LocalDate.now());
			  }
			  else {
				  LocalDate dateFrom= parseMyDate(p[2].trim());
				  collegues.setDateFrom(dateFrom);	
			  }
		  }
		  if (p[3] != null && p[3].trim().length() > 0) {
			  if ("NULL".compareTo(p[3].trim())==0) {
				  collegues.setDateTo(LocalDate.now());
			  }
			  else {
				  LocalDate dateTo= parseMyDate(p[3].trim());
				  collegues.setDateTo(dateTo);	
			  }
		  }

		  return collegues;
	};

	/**
     *This method will read .csv file and will put result in list of type class ColleaguesByProjects
     * 
     *  @param  inputFilePath
     *         This is path to CSV format file
     *
     * @return  The resultant List with all colleagues
     *
     */
	public List<ColleaguesByProjects> readCSVFile(String inputFilePath) throws Exception {

	    List<ColleaguesByProjects> inputListWithColleagues = new ArrayList<ColleaguesByProjects>();

	    try{

	    	 
	      File inputF = new File(inputFilePath);
	      InputStream inputFS = new FileInputStream(inputF);
	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

	      // skip the header of the csv
	      inputListWithColleagues = br.lines().skip(1).map(mapToAllColleagues).collect(Collectors.toList());
   
	      br.close();
	    } catch (IOException e) {
	        System.out.println("NumberFormatException: " + e.getMessage());  
		      
	    }
	   
		return inputListWithColleagues;
	}
	
	/**
     * This method sortColleguesByProject will sort the info of colleagues by Project: project ->list of colleagues
     * 
     *  @param  inputListWithColleagues
     *         List with all colleagues had read by file in class ColleaguesByProjects
     *
     * @return  The resultant hash map with information about projects and a list of colleagues who worked on them.
     *
     */
	public HashMap< Long, List<ColleaguesByProjects>> sortColleguesByProject(List<ColleaguesByProjects> inputListWithColleagues) {
		// Òhis hash variable stores information about all colleagues who have worked on a project. Sorts information from a project perspective.
		HashMap< Long, List<ColleaguesByProjects>> hashColleaguesByProject= new HashMap<Long, List<ColleaguesByProjects>>();
			
		for (ColleaguesByProjects coleguesByProjects : inputListWithColleagues) {
			if (hashColleaguesByProject.containsKey(coleguesByProjects.getProjectID())) {
				if (hashColleaguesByProject.get(coleguesByProjects.getProjectID()).add(coleguesByProjects)) {
					hashColleaguesByProject.put(coleguesByProjects.getProjectID(),hashColleaguesByProject.get(coleguesByProjects.getProjectID()) );
				}
			}
			else {
				List temp = new ArrayList();
				if (temp.add(coleguesByProjects)) {
					hashColleaguesByProject.put(coleguesByProjects.getProjectID(),temp );
				}
			}
		}
		return hashColleaguesByProject;
		
	}
		
		

	/**
     * This method will generarte list with  couple of colleagues who have worked together on common projects for the longest time
     * 
     *  @param  inputListWithColleagues
     *         List with all colleagues had read by file in class ColleaguesByProjects
     *
     * @return  The resultant List with couple of colleagues who have worked together on common projects for the longest time
     *
     */
	public List<String> get2ColleaguesWorkedTogetherOnPrjLongestTime(List<ColleaguesByProjects> inputListWithColleagues) {
		// Òhis hash variable stores information about all colleagues who have worked on a project. Sorts information from a project perspective.
		HashMap< Long, List<ColleaguesByProjects>> hashColleaguesByProject= new HashMap<Long, List<ColleaguesByProjects>>();
		hashColleaguesByProject=sortColleguesByProject(inputListWithColleagues);
		
		// This hash variable stores information about all couple worked together.
		HashMap< String, Long> hashCoupleColleaguesWorkedTogether= new HashMap<String, Long>();
		// This variable will store the couple with longest period work of common projects.
		String maxKeyCombine="-1";
		// This variable will store days the couple was worked together of common projects.
		long maxValue=0;
		// This hash variable stores information about colleagues who have worked together on common projects for the longest time. This couples can be more then one.
		HashMap< Long, List<String>> resultWorkedTogetherForLongestPeriod =new HashMap<Long, List<String>>();
		Set keys = hashColleaguesByProject.keySet();   
		
		for (Iterator i = keys.iterator(); i.hasNext();) 
		{
		      Long key = (Long) i.next();

		      List<ColleaguesByProjects> value = ( List<ColleaguesByProjects>) hashColleaguesByProject.get(key);
		       for (int k= 0; k < value.size(); k++) {
		    	  for (int j = k+1; j < value.size(); j++) {
		    		  String keyCombine=""+value.get(k).getEmpID()+"-"+ value.get(j).getEmpID();
		    		  if (value.get(k).getEmpID()>value.get(j).getEmpID()) {
		    			  keyCombine=""+value.get(j).getEmpID()+"-"+ value.get(k).getEmpID();
		    		  }
				      
				      LocalDate dateFromFirstCollegue	= value.get(k).getDateFrom();
				      LocalDate dateToFirstCollegue		= value.get(k).getDateTo();
				      LocalDate dateFromSecondCollegue	= value.get(j).getDateFrom();
				      LocalDate dateToSecondCollegue		= value.get(j).getDateTo();
				      // calculates days a couple has worked together on a project
				      long days =getDaysCoupleWorkTogetherbyPrj(dateFromFirstCollegue, dateToFirstCollegue, dateFromSecondCollegue, dateToSecondCollegue);
				      
//				      if (hashCoupleColleaguesWorkedTogether.containsKey(keyCombine)) {		
//				    	  long tmpDays= hashCoupleColleaguesWorkedTogether.get(keyCombine);
//				    	  tmpDays+=days;
//				    	  //stored accumulated time in days of together work for the couple				     
//				    	  hashCoupleColleaguesWorkedTogether.put(keyCombine, tmpDays);
//				    	  days = hashCoupleColleaguesWorkedTogether.get(keyCombine);
//				      }
//				      else {
//				    	// store for first time days of together work for a couple
//				    	  hashCoupleColleaguesWorkedTogether.put(keyCombine, days);
//				      }
				      
				      //sets the maximum value
				      if ("-1".equals(maxKeyCombine)) {
				    	  maxKeyCombine=keyCombine;
				    	  maxValue=days;			
				    	  List temp = new ArrayList();
			    		  if (temp.add(maxKeyCombine))
			    			  resultWorkedTogetherForLongestPeriod.put(maxValue, temp );
				      }
				      else {
				    	  if (maxKeyCombine.equals(keyCombine)) {
				    		  maxKeyCombine=keyCombine;
				    		  maxValue= hashCoupleColleaguesWorkedTogether.get(keyCombine);	
				    		  List temp = new ArrayList();
						      if (temp.add(maxKeyCombine)) {
						    	  resultWorkedTogetherForLongestPeriod.put(maxValue, temp );
						      }
				    	  }
				    	  else {
					    	  if (maxValue<days) {
					    		  maxKeyCombine=keyCombine;
					    		  maxValue=days;
							      List temp = new ArrayList();
							      if (temp.add(maxKeyCombine)) {
							    	  resultWorkedTogetherForLongestPeriod.put(maxValue, temp );
							      }
					    	  }
					    	  else {

					    		  if (maxValue==days) {
								      if (resultWorkedTogetherForLongestPeriod.containsKey(days)) {
								    	  resultWorkedTogetherForLongestPeriod.get(maxValue).add(keyCombine);
								      }
					    		  }
					    	  }
				    	  }
				      }

		    	  }
		  		    
		      }
		      
		}
//		System.out.println(maxKeyCombine+"-->"+maxValue);
//		System.out.println(resultWorkedTogetherForLongestPeriod.toString());
//		System.out.println(resultWorkedTogetherForLongestPeriod.get(maxValue).toString()+"-->"+maxValue);
		return resultWorkedTogetherForLongestPeriod.get(maxValue);
		
	}

	private long getDaysCoupleWorkTogetherbyPrj(LocalDate dateFromFirstCollegue, LocalDate dateToFirstCollegue, LocalDate dateFromSecondCollegue, LocalDate dateToSecondCollegue) {
		long daysBetween=0;
		if (dateFromFirstCollegue.isBefore(dateFromSecondCollegue)) {
			if (dateFromSecondCollegue.isBefore(dateToFirstCollegue)) {
				daysBetween =ChronoUnit.DAYS.between(dateFromSecondCollegue, dateToFirstCollegue);
			}
		}
		else {
			if (dateFromFirstCollegue.isBefore(dateToSecondCollegue)) {
				daysBetween =ChronoUnit.DAYS.between(dateFromFirstCollegue, dateToSecondCollegue);
			}
		}
		
		return daysBetween;
	}

	private LocalDate parseMyDate(String str) {
		LocalDate myDate = LocalDate.parse(str.trim());
		return myDate;
	}
}