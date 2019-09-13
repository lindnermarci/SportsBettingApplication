package com.epam.training.sportsbetting;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.Wager;
import com.epam.training.sportsbetting.service.SportsBetting;
import com.epam.training.sportsbetting.service.SportsBettingSevice;
import com.epam.training.sportsbetting.view.SportsBettingView;
import com.epam.training.sportsbetting.view.View;

public class App {
    
    private static SportsBettingSevice service;
    private static View view;
    
    public static void main(String[] args) {
        App app = new App(new SportsBetting(), new SportsBettingView());
        createPLayer();
        play();
        calculateResult();
        printResult();
    }
    
    public App(SportsBettingSevice sportsBettingSevice, View view) {
        App.service = sportsBettingSevice;
        App.view = view;
    }
    
    public static void play() {
        Player player = service.findPlayer();
        
        view.PrintWelcomeMessage(player);
        
        while(true) {
            view.PrintBalanace(player);
            view.PrintOutcomeOdds(service.findAllSportEvents());
            Optional<OutcomeOdd> outcomeOdd = view.selectOutcomeOdd(service.findAllSportEvents());
            if (outcomeOdd.isEmpty()) break;
            
            BigDecimal wagerAmount = view.readWagerAmount();
            Wager wager = null;
            if(service.sufficientBalance(wagerAmount)) {
                wager = new Wager(wagerAmount, outcomeOdd.get(), player);
                service.saveWager(wager);
            }else {
                do {
                    view.printNotEnoughBalance(player);
                    wagerAmount = view.readWagerAmount();
                }while(!service.sufficientBalance(wagerAmount));
                wager = new Wager(wagerAmount, outcomeOdd.get(), player);
                service.saveWager(wager);
            }
            view.printWagerSaved(wager);
        }
    }
    
    private static void createPLayer() {
        Player player = view.readPlayerData();
        service.savePlayer(player);
    }
    
    private static void calculateResult() {
        service.calculateResult();
    }
    
    private static void printResult() {
        view.printResult(service.findPlayer(), service.findAllWagers());
    }
}
