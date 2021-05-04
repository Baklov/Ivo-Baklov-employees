package com.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.employees.ColleaguesByProjects;
import com.employees.CoupleWorkedTogetherLongestPeriod;


class CoupleWorkedTogetherLongestPeriodTest {

	@Test
	void testGet2ColleaguesWorkedTogetherOnPrjLongestTime() {
		CoupleWorkedTogetherLongestPeriod couple = new CoupleWorkedTogetherLongestPeriod();
		List<ColleaguesByProjects> coleagues= new ArrayList<ColleaguesByProjects>();
		coleagues.add(new ColleaguesByProjects(200, 15, "2000-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(201, 15, "2000-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(202, 15, "2000-01-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(203, 15, "2017-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(143, 12, "2013-11-01", "2014-01-05"));
		
		coleagues.add(new ColleaguesByProjects(218, 10, "2012-05-16", "NULL"));
		coleagues.add(new ColleaguesByProjects(143, 10, "2009-01-01", "2020-04-27"));
		coleagues.add(new ColleaguesByProjects(144, 13, "2014-11-01", "2020-01-05"));		
		coleagues.add(new ColleaguesByProjects(146, 13, "2015-11-01", "2021-01-05"));
		
		coleagues.add(new ColleaguesByProjects(147, 14, "2000-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(148, 14, "2000-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(149, 14, "2000-11-01", "2021-01-05"));
		
		coleagues.add(new ColleaguesByProjects(150, 14, "2015-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(218, 14, "2020-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(151, 14, "2020-11-01", "2021-01-05"));
		
		
		List<String> result =couple.get2ColleaguesWorkedTogetherOnPrjLongestTime(coleagues);
		assertEquals("[147-148, 147-149, 148-149, 200-201, 200-202, 201-202]",result.toString());
	}
	
	@Test
	void testGet2ColleaguesWorkedTogetherOnPrjLongestTime2() {
		CoupleWorkedTogetherLongestPeriod couple = new CoupleWorkedTogetherLongestPeriod();
		List<ColleaguesByProjects> coleagues= new ArrayList<ColleaguesByProjects>();
		coleagues.add(new ColleaguesByProjects(200, 15, "2000-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(201, 15, "2000-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(202, 15, "2000-01-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(203, 15, "2017-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(143, 12, "2013-11-01", "2014-01-05"));
		
		coleagues.add(new ColleaguesByProjects(218, 10, "2012-05-16", "NULL"));
		coleagues.add(new ColleaguesByProjects(143, 10, "2009-01-01", "2020-04-27"));
		coleagues.add(new ColleaguesByProjects(144, 13, "2014-11-01", "2020-01-05"));		
		coleagues.add(new ColleaguesByProjects(146, 13, "2015-11-01", "2021-01-05"));
		
		coleagues.add(new ColleaguesByProjects(147, 14, "2000-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(148, 14, "2000-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(149, 14, "2000-11-01", "2021-01-05"));
		
		coleagues.add(new ColleaguesByProjects(150, 14, "2015-11-01", "2021-01-05"));
		coleagues.add(new ColleaguesByProjects(218, 14, "1999-11-01", "NULL"));
		coleagues.add(new ColleaguesByProjects(143, 14, "1999-11-01", "NULL"));
		
		
		List<String> result =couple.get2ColleaguesWorkedTogetherOnPrjLongestTime(coleagues);
		assertEquals("[143-218]",result.toString());
	}
	
	@Test
	void testGet2ColleaguesWorkedTogetherOnPrjLongestTime1() {
		CoupleWorkedTogetherLongestPeriod couple = new CoupleWorkedTogetherLongestPeriod();
		List<ColleaguesByProjects> coleagues= new ArrayList<ColleaguesByProjects>();
		coleagues.add(new ColleaguesByProjects(143, 12, "2013-11-01", "2014-01-05"));
		coleagues.add(new ColleaguesByProjects(218, 10, "2012-05-16", "NULL"));
		coleagues.add(new ColleaguesByProjects(143, 10, "2009-01-01", "2020-04-27"));
		coleagues.add(new ColleaguesByProjects(218, 14, "2020-11-01", "NULL"));
		coleagues.add(new ColleaguesByProjects(143, 14, "2000-11-01", "2021-01-05"));
		
		
		List<String> result =couple.get2ColleaguesWorkedTogetherOnPrjLongestTime(coleagues);
		assertEquals("[143-218]",result.toString());
	}

}
