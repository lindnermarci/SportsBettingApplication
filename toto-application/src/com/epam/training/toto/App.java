package com.epam.training.toto;

import com.epam.training.service.TotoService;

/*
 * Entry point for the application. It uses TotoService to get results and prints them to the console.
 */

public class App {

	public static void main(String[] args) {
		TotoService ts = TotoService.getInstance();
		
		System.out.println(ts.getLargestPrize());
		
		System.out.println(ts.getDistribution());
		
		ts.playGame();
		
		
		/*
		for(Round r: ts.getRounds()) {
			System.out.println(r.toString());
		}
		
		for(List<Outcome> o: ts.getOutcomes()) {
			System.out.println(o);
		}
		
		for(ArrayList<Hit> h: ts.getHits()) {
			System.out.println(h);
		}
		*/
	}

}
