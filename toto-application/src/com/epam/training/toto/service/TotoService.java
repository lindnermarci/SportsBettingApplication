package com.epam.training.toto.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.Round;

/*
 * A singleton class, that implements methods to satisfy confirmation.
 */

public class TotoService {

    private static TotoService totoService;

    private static final String COMMA_DELIMITER = ";";

    private List<Round> rounds;
    private List<ArrayList<Hit>> hits;
    private List<ArrayList<Outcome>> outcomes;

    private TotoService(List<Round> rounds, List<ArrayList<Hit>> hits,
            List<ArrayList<Outcome>> outcomes) {
        this.rounds = rounds;
        this.hits = hits;
        this.outcomes = outcomes;
    }

    public static TotoService getInstance() {
        if (totoService == null) {
            initalise();
        }
        return totoService;
    }

    public List<Hit> getHits(Round round) {
        return hits.get(getRoundIndex(round));
    }

    public void setHits(Round round, List<Hit> hits2) {
        int ind = getRoundIndex(round);
        for (int i = 0; i < hits2.size(); ++i) {
            hits.get(ind).set(i, hits2.get(i));
        }

    }

    public List<Outcome> getOutcome(Round round) {
        return outcomes.get(getRoundIndex(round));
    }

    public void setOutcome(Round round, List<Outcome> outcomes2) {
        int ind = getRoundIndex(round);
        for (int i = 0; i < outcomes2.size(); ++i) {
            outcomes.get(ind).set(i, outcomes2.get(i));
        }

    }

    public static ArrayList<Hit> getHitsOfLine(String[] values) {
        ArrayList<Hit> hits = new ArrayList<Hit>();
        int hitCount = 14;
        for (int i = 4; i < 14; i += 2) {
            hits.add(new Hit(hitCount, Integer.parseInt(values[i]),
                    Integer.parseInt(values[i + 1].substring(0, values[i + 1].length() - 3).replaceAll("\\s+", ""))));
            hitCount--;
        }
        return hits;
    }

    public static ArrayList<Outcome> getOutcomesOfLine(String[] values) {
        ArrayList<Outcome> outcomes = new ArrayList<Outcome>();
        for (int i = 14; i < values.length - 1; i++) {
            outcomes.add(stringToOutcome(values[i]));
        }

        String last = values[values.length-1];
        if(last.startsWith("+")) {
            last = values[values.length-1].substring(1);   
        }
        outcomes.add(stringToOutcome(last));
        
        return outcomes;
    }

    public String getLargestPrize() {
        int max = 0;
        for (ArrayList<Hit> h : hits) {
            // the largest prize in a given game must be the 14 hit
            int prize = h.get(0).getPrize();
            if (prize > max) {
                max = prize;
            }
        }
        return "The largest prize ever recorded: " + formatCurrency(max);
    }

    private static void initalise() {
        ArrayList<Round> rounds = new ArrayList<Round>();
        ArrayList<ArrayList<Hit>> hits = new ArrayList<ArrayList<Hit>>();
        ArrayList<ArrayList<Outcome>> outcomes = new ArrayList<ArrayList<Outcome>>();

        try (BufferedReader br = new BufferedReader(new FileReader("toto.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Round round = getRoundOfLine(values);
                hits.add(getHitsOfLine(values));
                outcomes.add(getOutcomesOfLine(values));
                rounds.add(round);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        totoService = new TotoService(rounds, hits, outcomes);
    }

    private static Round getRoundOfLine(String[] values) {
        return new Round(Integer.parseInt(values[0]), Integer.parseInt(values[1]), round(values), date(values));
    }

    private static LocalDate date(String[] values) {
        if (values[3].length() > 1) {
            String date = values[3].replace('.', '-');
            return LocalDate.parse(date.substring(0, date.length() - 1));
        } else {
            return null;
        }

    }

    private static int round(String[] values) {
        return (values[2].equals("-")) ? 1 : Integer.parseInt(values[2]);
    }

    private static Outcome stringToOutcome(String value) {
        if (value.equals("1")) {
            return Outcome._1;
        } else if (value.equals("2")) {
            return Outcome._2;
        } else {
            return Outcome.X;
        }
    }

    private String formatCurrency(int value) {
        String pattern = "###,###.###";

        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        return decimalFormat.format(value) + " Ft";
    }

    public String getDistribution() {
        int _1count = 0;
        int _2count = 0;
        int _Xcount = 0;
        int total = outcomes.size() * outcomes.get(0).size();

        for (ArrayList<Outcome> list : outcomes) {
            for (Outcome outcome : list) {
                if (outcome == Outcome._1) {
                    _1count++; 
                }
                else if(outcome == Outcome.X) {
                    _Xcount++;   
                }
                else if (outcome == Outcome._2) {
                    _2count++;
                }
            }
        }

        // return _1count + "";
        return format((float)_1count / total * 100, (float) _2count / total*100, (float) _Xcount / total*100);
    }

    private String format(double d, float e, float f) {
        // TODO Auto-generated method stub
        return String.format("Statistics: team #1 won: %.2f %%, team #2 won: %.2f %%, draw: %.2f %%", d, e, f);
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date: ");
        String dateString = scanner.nextLine().replace('.', '-');
        LocalDate date = LocalDate.parse(dateString.substring(0, dateString.length() - 1));

        System.out.println("Enter outcomes: ");
        char[] outcome = scanner.nextLine().toCharArray();
        scanner.close();
        if (outcome.length < 14) {
            System.out.println("Wrong length");
            return;
        }

        System.out.println(date);
        int ind = getRoundIndex(date);
        int charInd = 0;
        int hitCount = 0;
        for (Outcome x : outcomes.get(ind)) {
            if (x == Outcome._1 && outcome[charInd] == '1')
                hitCount++;
            else if (x == Outcome._2 && outcome[charInd] == '2')
                hitCount++;
            else if (x == Outcome.X && outcome[charInd] == 'X')
                hitCount++;
            charInd++;
        }

        for (Hit hit : hits.get(ind)) {
            if (hit.getHitCount() == hitCount) {
                System.out.println(formatResult(hit.getHitCount(), hit.getPrize()));
            }
        }

    }

    private int getRoundIndex(LocalDate date) {
        return rounds.indexOf(rounds.parallelStream().filter(round -> (round.getDate() != null) && round.getDate().equals(date)).findAny().get());
    }

    private int getRoundIndex(Round round) {
        return rounds.indexOf(rounds.parallelStream().filter(r -> (round != null) && round.equals(round)).findAny().get());
    }

    private String formatResult(int hitCount, int prize) {
        return "Result: hits: " + hitCount + ", amount: " + formatCurrency(prize);

    }

}
