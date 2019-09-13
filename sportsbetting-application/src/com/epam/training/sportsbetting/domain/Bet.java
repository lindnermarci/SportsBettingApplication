package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

public class Bet {
    private String description;
    private SportEvent sportEvent;
    private BetType type;
    List<Outcome> outcomes;
    
    public Bet(String description, SportEvent sportevent, BetType type, List<Outcome> outcomes) {
        super();
        this.description = description;
        this.sportEvent = sportevent;
        this.type = type;
        this.outcomes = outcomes;
    }
    
    public Bet(String description, SportEvent sportevent, BetType type) {
        super();
        this.description = description;
        this.sportEvent = sportevent;
        this.type = type;
        this.outcomes = new ArrayList<Outcome>();
    }
    
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public SportEvent getSportevent() {
        return sportEvent;
    }
    public void setSportevent(SportEvent sportevent) {
        this.sportEvent = sportevent;
    }
    public BetType getType() {
        return type;
    }
    public void setType(BetType type) {
        this.type = type;
    }
    public List<Outcome> getOutcomes() {
        return outcomes;
    }
    
    public String getSportEventTitle() {
        return sportEvent.getTitle();
    }
    
    public void setOutcomeOdd(OutcomeOdd outcomeOdd, int indexOfOutcome) {
        if(outcomes.size() < indexOfOutcome + 1) {
           System.out.println("Error in Bet.java line 57");
            return;
        }
        outcomes.get(indexOfOutcome).addOutcomeOdd(outcomeOdd);
    }

    
}
