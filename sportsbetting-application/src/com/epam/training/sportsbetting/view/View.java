package com.epam.training.sportsbetting.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public interface View {
    Player readPlayerData();
    void PrintWelcomeMessage(Player player);
    void PrintBalanace(Player player);
    void PrintOutcomeOdds(List<SportEvent> sportEvents);
    Optional<OutcomeOdd> selectOutcomeOdd(List<SportEvent> sportEvents);
    BigDecimal readWagerAmount();
    void printWagerSaved(Wager wager);
    void printNotEnoughBalance(Player player);
    void printResult(Player player, List<Wager> wagers);
}
