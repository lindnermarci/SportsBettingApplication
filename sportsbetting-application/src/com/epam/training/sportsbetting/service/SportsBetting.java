package com.epam.training.sportsbetting.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.BetType;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public class SportsBetting implements SportsBettingSevice {

    private Player player;
    private List<SportEvent> sportEvents;
    private List<Wager> wagers;

    public SportsBetting() {
        List<Bet> bets = new ArrayList<Bet>();
        SportEvent event = new SportEvent("Arsenal vs Chelsea", LocalDateTime.parse("2020-01-01T12:00:00"), LocalDateTime.parse("2020-01-01T13:30:00"), null, "Arsenal", "Chelsea");
        bets.add(new Bet("player Oliver Giroud score", event, BetType.PLAYERS_SCORE));
        bets.add(new Bet("number of scored goals", event, BetType.GOALS));
        bets.add(new Bet("winner", event, BetType.GOALS));

        
        bets.get(0).getOutcomes().add(new Outcome("1", bets.get(0)));
        bets.get(1).getOutcomes().add(new Outcome("3", bets.get(1)));
        bets.get(2).getOutcomes().add(new Outcome("Arsenal", bets.get(2)));
        bets.get(2).getOutcomes().add(new Outcome("Chelsea", bets.get(2)));

        bets.get(0).setOutcomeOdd(new OutcomeOdd(BigDecimal.valueOf(2), LocalDateTime.parse("2000-01-01T12:00:00"), LocalDateTime.parse("2020-01-01T12:00:00"),
                bets.get(0).getOutcomes().get(0)), 0);
        bets.get(1).setOutcomeOdd(new OutcomeOdd(BigDecimal.valueOf(3), LocalDateTime.parse("2000-01-01T12:00:00"), LocalDateTime.parse("2020-01-01T12:00:00"),
                bets.get(1).getOutcomes().get(0)), 0);
        bets.get(2).setOutcomeOdd(new OutcomeOdd(BigDecimal.valueOf(2), LocalDateTime.parse("2000-01-01T12:00:00"), LocalDateTime.parse("2020-01-01T12:00:00"),
                bets.get(2).getOutcomes().get(0)), 0);
        bets.get(2).setOutcomeOdd(new OutcomeOdd(BigDecimal.valueOf(3), LocalDateTime.parse("2000-01-01T12:00:00"), LocalDateTime.parse("2020-01-01T12:00:00"),
                bets.get(2).getOutcomes().get(1)), 0);

        event.setBets(bets);
        sportEvents = new ArrayList<SportEvent>();
        sportEvents.add(event);
        wagers = new ArrayList<Wager>();
    }

    @Override
    public void savePlayer(Player player) {
        this.player = player;

    }

    @Override
    public Player findPlayer() {
        return player;
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return sportEvents;
    }

    @Override
    public void saveWager(Wager wager) {
        if (wager.getAmount().compareTo(player.getBalance()) == 1) {
            System.out.printf("You don't have enough money, your balance is %d HUF", player.getBalance());
            return;
        }
        wagers.add(wager);
        player.setBalance(player.getBalance().subtract(wager.getAmount()));

    }

    @Override
    public List<Wager> findAllWagers() {
        return wagers;
    }

    @Override
    public void calculateResult() {
        double random = Math.random();
            for(Wager wager: wagers) {
                if(random < 0.5) {
                    Outcome outcome = wager.getOutcome();
                    SportEvent sportEvent = wager.getSportEvent();
                    sportEvent.addWinnerOutcome(outcome);
                    wager.setWin(true);
                    wager.increasePlayerBalanace(wager.getAmount().multiply(wager.getOutcomeOdd()));
                }
                random = Math.random();
                wager.setProcessed(true);
            }
    }
    
    @Override
    public boolean sufficientBalance(BigDecimal wagerAmount) {
        if (player.getBalance().compareTo(wagerAmount) == -1) {
            return false;
        }
        return true;
    }

}
