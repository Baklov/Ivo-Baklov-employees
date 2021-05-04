package com.employees;
import java.io.File;
import java.util.List;
import java.util.Scanner;

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
			String fileEmployeesName =giveMeFileName();
			List<ColleaguesByProjects> AllCollegues =f.readCSVFile(fileEmployeesName);
			 //Couple colleagues who have worked together on common projects for the longest time
		    List<String> result =f.get2ColleaguesWorkedTogetherOnPrjLongestTime(AllCollegues);
		    System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String giveMeFileName() {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("What is the filename and where is the file? You should write filename and path to this file as this way: disk:\\subfolder\\..\\filename!");
		String input = in.nextLine();
		File file = new File(input);
		return input;
	}
}
