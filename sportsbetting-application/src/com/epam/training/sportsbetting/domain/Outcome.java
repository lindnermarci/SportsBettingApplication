package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

public class Outcome {
    private String description;
    private Bet bet;
    List<OutcomeOdd> outcomeOdds;
    
    public Outcome(String description, Bet bet, List<OutcomeOdd> outcomeOdds) {
        super();
        this.description = description;
        this.bet = bet;
        this.outcomeOdds = outcomeOdds;
    }
    
    public Outcome(String description, Bet bet) {
        super();
        this.description = description;
        this.bet = bet;
        this.outcomeOdds = new ArrayList<OutcomeOdd>();
    }
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Bet getBet() {
        return bet;
    }
    public void setBet(Bet bet) {
        this.bet = bet;
    }
    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }
    
    public String getSportEventTitle() {
        return bet.getSportEventTitle();
    }


    public void addOutcomeOdd(OutcomeOdd outcomeOdd) {
        outcomeOdds.add(outcomeOdd);
        
    }

    public String getBetDescription() {
        return bet.getDescription();
    }

    public SportEvent getSportEvent() {
        return bet.getSportevent();
    }
    
    
}
