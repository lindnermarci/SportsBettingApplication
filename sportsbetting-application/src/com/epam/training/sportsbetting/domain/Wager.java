package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wager {
    private BigDecimal amount;
    private LocalDateTime timestampCreated;
    private boolean processed;
    private boolean win;
    private OutcomeOdd outcomeOdd;
    private Player player;
    private Currency currency;
    
    public Wager(BigDecimal amount, OutcomeOdd odd, Player player) {
        super();
        this.amount = amount;
        this.timestampCreated = LocalDateTime.now();
        this.processed = false;
        this.win = false;
        this.outcomeOdd = odd;
        this.player = player;
        this.currency = player.getCurrency();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    public void setTimestampCreated(LocalDateTime timestampCreated) {
        this.timestampCreated = timestampCreated;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public OutcomeOdd getOdd() {
        return outcomeOdd;
    }

    public void setOdd(OutcomeOdd odd) {
        this.outcomeOdd = odd;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    public String getSportEventTitle() {
        return outcomeOdd.getSportEventTitle();
    }

    public String getOutcomeDescription() {
        // TODO Auto-generated method stub
        return outcomeOdd.getOutcomeDescription();
    }

    public String getBetDescription() {
        return outcomeOdd.getBetDescription();
    }

    public BigDecimal getOutcomeOdd() {
        return outcomeOdd.getValue();
    }

    public Outcome getOutcome() {
        return outcomeOdd.getOutcome();
        
    }

    public SportEvent getSportEvent() {
        return outcomeOdd.getSportEvent();
    }

    public void increasePlayerBalanace(BigDecimal val) {
        player.increasePlayerBalanace(val);
        
    }
    
}
