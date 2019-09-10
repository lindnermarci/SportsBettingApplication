package com.epam.training.toto.domain;

import java.time.LocalDate;
import java.util.List;

import com.epam.training.toto.service.TotoService;

/*
 * A class to represent the results of a round. (One line of the CSV)
 */

public class Round {
	private int year;
	private int week;
	private int roundOfWeek;
	private LocalDate date;
	
	public Round(int year, int week, int roundOfWeek, LocalDate date) {
		super();
		this.year = year;
		this.week = week;
		this.roundOfWeek = roundOfWeek;
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getRoundOfWeek() {
		return roundOfWeek;
	}

	public void setRoundOfWeek(int roundOfWeek) {
		this.roundOfWeek = roundOfWeek;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public List<Hit>getHits(){
		TotoService ts = TotoService.getInstance();
		return ts.getHits(this);
	}
	
	public void setHits (Round round, List<Hit> hits){
		TotoService ts = TotoService.getInstance();
		ts.setHits(this, hits);
	}
	
	public List<Outcome> getOutcomes(){
		TotoService ts = TotoService.getInstance();
		return ts.getOutcome(this);
	}
	
	public void setOutcomes (Round round, List<Outcome> outcomes){
		TotoService ts = TotoService.getInstance();
		ts.setOutcome(this, outcomes);
	}

	@Override
	public String toString() {
		return "Round [year=" + year + ", week=" + week + ", roundOfWeek=" + roundOfWeek + ", date=" + date + "]";
	}
	
}
