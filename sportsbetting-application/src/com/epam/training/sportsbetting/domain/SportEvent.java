package com.epam.training.sportsbetting.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class SportEvent implements FootballSportEvent, TenisSportEvent {

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Bet> bets;
    private Result result;
    private String player1;
    private String player2;
    
    
    public SportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, List<Bet> bets, Result result, String player1, String player2) {
        super();
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bets = bets;
        this.result = result;
        this.player1 = player1;
        this.player2 = player2;
    }
    

    public SportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, Bet bet, String player1, String player2) {
        this(title, startDate, endDate, Collections.emptyList(), new Result(), player1, player2);    
        }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }


    public LocalDateTime getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }


    public Result getResult() {
        return result;
    }


    public void setResult(Result result) {
        this.result = result;
    }


    public List<Bet> getBets() {
        return bets;
    }
    
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }


    public String getPlayer1() {
        return player1;
    }


    public void setPlayer1(String player1) {
        this.player1 = player1;
    }


    public String getPlayer2() {
        return player2;
    }


    public void setPlayer2(String player2) {
        this.player2 = player2;
    }



    public void addWinnerOutcome(Outcome outcome) {
        result.addWinnerOutcome(outcome);
        
    }


    public String getStringStartDate() {
        return startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    
}
