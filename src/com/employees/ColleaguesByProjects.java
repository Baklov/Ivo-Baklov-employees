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
	public ColleaguesByProjects() {
		super();
	}
	private long empID;
	public ColleaguesByProjects(long empID, long projectID, LocalDate dateFrom, LocalDate dateTo) {
		super();
		this.empID = empID;
		this.projectID = projectID;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
	public ColleaguesByProjects(long empID, long projectID, String dateFrom, String dateTo) {
		super();
		this.empID = empID;
		this.projectID = projectID;
		if ("NULL".equals(dateFrom)) {
			this.dateFrom = LocalDate.parse(dateFrom);
		}
		else {
			this.dateFrom = LocalDate.parse(dateFrom);
		}
		if ("NULL".equals(dateTo)) {
			this.dateTo = LocalDate.now();;
		}
		else {		
			this.dateTo = LocalDate.parse(dateTo);;
		}
	}
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
