package com.epam.training.sportsbetting.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.epam.training.sportsbetting.domain.Bet;
import com.epam.training.sportsbetting.domain.Currency;
import com.epam.training.sportsbetting.domain.Outcome;
import com.epam.training.sportsbetting.domain.OutcomeOdd;
import com.epam.training.sportsbetting.domain.Player;
import com.epam.training.sportsbetting.domain.SportEvent;
import com.epam.training.sportsbetting.domain.Wager;

public class SportsBettingView implements View {

    private Scanner scanner = new Scanner(System.in);
    static boolean correctGrammar = false;

    @Override
    public Player readPlayerData() {
        // Scanner scanner = new Scanner(System.in);
        System.out.println("> What is your name?");
        String name = scanner.nextLine();
        System.out.println("> How much money do you have (more than 0)?");
        String amount = scanner.nextLine();
        BigDecimal money = BigDecimal.valueOf(Double.parseDouble(amount));
        System.out.println("> What is your currency? (HUF, EUR or USD)");
        String stringCurrency = scanner.nextLine();

        Currency currency = stringCurrency.contentEquals("HUF") ? Currency.HUF : (stringCurrency.contentEquals("EUR") ? Currency.EUR : Currency.USD);
        // scanner.close();
        return new Player(name, 1, money, LocalDate.now(), currency);
    }

    @Override
    public void PrintWelcomeMessage(Player player) {
        System.out.println("> Welcome " + player.getName() + "!");

    }

    @Override
    public void PrintBalanace(Player player) {
        System.out.printf("> Your balance is " + formatCurrency(player.getBalance().intValue(), player.getCurrency()));
        System.out.println();
    }

    private String formatCurrency(int value, Currency currency) {
        String pattern = "###,###.###";

        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        return decimalFormat.format(value) + " " + currency.toString();
    }

    @Override
    public void PrintOutcomeOdds(List<SportEvent> sportEvents) {
        System.out.println("> What " + (correctGrammar ? "do" : "are") + " you want to bet on? (choose a number or press q for quit)");
        correctGrammar = true;
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (SportEvent event : sportEvents) {
            for (Bet bet : event.getBets()) {
                for (Outcome outcome : bet.getOutcomes()) {
                    for (OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()) {
                        sb.append("> ")
                                .append(i)
                                .append(": Sport event: ")
                                .append(event.getTitle())
                                .append(" (start: ")
                                .append(event.getStringStartDate())
                                .append("), Bet: ")
                                .append(bet.getDescription())
                                .append(", Outcome: ")
                                .append(outcomeOdd.getOutcomeDescription())
                                .append(", Actual odd: ")
                                .append(outcomeOdd.getValue())
                                .append(", Valid between ")
                                .append(outcomeOdd.getStringValidForm())
                                .append(" and ")
                                .append(outcomeOdd.getStringValidUntil())
                                .append('.');
                        System.out.println(sb.toString());
                        sb = new StringBuilder();
                        i++;
                    }
                }
            }
        }

    }

    @Override
    public Optional<OutcomeOdd> selectOutcomeOdd(List<SportEvent> sportEvents) {
        // Scanner scanner = new Scanner(System.in);
        /*
         * if(!scanner.hasNextLine()) { System.out.println("no next line"); scanner.close(); return null; }
         */
        String selected = scanner.nextLine();
        if (selected.contentEquals("q")) {
            scanner.close();
            return Optional.empty();
        }
        int selectedIndex = Integer.parseInt(selected);
        // scanner.close();
        int i = 1;
        for (SportEvent event : sportEvents) {
            for (Bet bet : event.getBets()) {
                for (Outcome outcome : bet.getOutcomes()) {
                    for (OutcomeOdd outcomeOdd : outcome.getOutcomeOdds()) {
                        if (i == selectedIndex) {
                            return Optional.of(outcomeOdd);
                        }
                        i++;
                    }
                }
            }
        }
        return null;

    }

    @Override
    public BigDecimal readWagerAmount() {
        System.out.println("> What amount do you wish to bet on it?");
        // Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        // System.out.println(next);
        BigDecimal wager = BigDecimal.valueOf(Double.parseDouble(next));
        // scanner.close();
        return wager;
    }

    @Override
    public void printWagerSaved(Wager wager) {

        StringBuilder sb = new StringBuilder();
        sb.append("> Wager \'")
                .append(wager.getBetDescription())
                .append("=")
                .append(wager.getOutcomeDescription())
                .append("\' of ")
                .append(wager.getSportEventTitle())
                .append(" [odd: ")
                .append(wager.getOdd().getValue())
                .append(", amount: ")
                .append(wager.getAmount().intValue())
                .append("] saved!");
        System.out.println(sb.toString());
    }

    @Override
    public void printNotEnoughBalance(Player player) {
        System.out.printf("> You don't have enough money, your balance is %s", formatCurrency(player.getBalance().intValue(), player.getCurrency()));
        System.out.println();

    }

    @Override
    public void printResult(Player player, List<Wager> wagers) {
        System.out.println("> Results:");
        StringBuilder sb = new StringBuilder();
        for (Wager wager : wagers) {
            sb.append("> Wager \'")
                    .append(wager.getBetDescription())
                    .append("=")
                    .append(wager.getOutcomeDescription())
                    .append("\' of ")
                    .append(wager.getSportEventTitle())
                    .append(" [odd: ")
                    .append(wager.getOdd().getValue())
                    .append(", amount: ")
                    .append(wager.getAmount().intValue())
                    .append("], win: ")
                    .append(wager.isWin());

            System.out.println(sb.toString());
            sb = new StringBuilder();
        }

        System.out.println("> Your new balance is " + formatCurrency(player.getBalance().intValue(), player.getCurrency()) + '.');
    }

}
