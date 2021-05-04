package com.employees;
import java.time.LocalDate;
/*
 * ColeguesByProjects
 * 
 * Version 1
 *
 * 01-05-2021
 * 
 * Ivo Baklov
 */
public class ColleaguesByProjects {
	private long empID;
	private long projectID;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	public long getEmpID() {
		return empID;
	}
	public void setEmpID(long empID) {
		this.empID = empID;
	}
	public long getProjectID() {
		return projectID;
	}
	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}
	public LocalDate getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}
	public LocalDate getDateTo() {
		return dateTo;
	}
	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}
	
	
}
