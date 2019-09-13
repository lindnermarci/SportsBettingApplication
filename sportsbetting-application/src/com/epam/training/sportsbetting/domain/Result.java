package com.epam.training.sportsbetting.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Outcome> winnerOutcomes;

    public Result(List<Outcome> winnerOutcomes) {
        super();
        this.winnerOutcomes = winnerOutcomes;
    }
    
    public Result() {
        super();
        this.winnerOutcomes = new ArrayList<Outcome>();
    }

    public List<Outcome> getWinnerOutcomes() {
        return winnerOutcomes;
    }

    public void addWinnerOutcome(Outcome outcome) {
        winnerOutcomes.add(outcome);
    }
 
}
