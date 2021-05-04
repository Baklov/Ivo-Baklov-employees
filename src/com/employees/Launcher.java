package com.employees;
import java.util.List;

/*
 * Launcher of Application
 * 
 * Version 1
 *
 * 01/05/2021
 * 
 * Created by Ivo Baklov
 */
public class Launcher {

	public static void main(String[] args) {
		CoupleWorkedTogetherLongestPeriod f = new CoupleWorkedTogetherLongestPeriod();
		try {
			// Read all colleagues from File
			List<ColleaguesByProjects> AllCollegues =f.readCSVFile("E:\\ivo\\coleguesbyprojects1.txt");
			 //Couple colleagues who have worked together on common projects for the longest time
		    List<String> result =f.get2ColleaguesWorkedTogetherOnPrjLongestTime(AllCollegues);
		    System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
