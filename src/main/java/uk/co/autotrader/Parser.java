package uk.co.autotrader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

	public FootballResults parse(String filename) throws IOException {
		FootballResults footballResults = new FootballResults();
		BufferedReader input = new BufferedReader(new FileReader(filename));


		return footballResults;

	}

}
