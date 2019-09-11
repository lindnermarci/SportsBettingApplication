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
    private List<Hit> hits;
    private List<Outcome> outcomes;

    public Round(int year, int week, int roundOfWeek, LocalDate date) {
        super();
        this.year = year;
        this.week = week;
        this.roundOfWeek = roundOfWeek;
        this.date = date;
    }
    
    

    public Round(int year, int week, int roundOfWeek, LocalDate date, List<Hit> hits, List<Outcome> outcomes) {
        super();
        this.year = year;
        this.week = week;
        this.roundOfWeek = roundOfWeek;
        this.date = date;
        this.hits = hits;
        this.outcomes = outcomes;
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

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(Round round, List<Hit> hits) {
        this.hits = hits;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(Round round, List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    @Override
    public String toString() {
        return "Round [year=" + year + ", week=" + week + ", roundOfWeek=" + roundOfWeek + ", date=" + date + "]";
    }

}
