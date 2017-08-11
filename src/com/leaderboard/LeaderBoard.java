package com.leaderboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

/**
 * LeaderBoard, maintains ranks based on spends
 * 
 * @author kaushik
 */
public class LeaderBoard {

	private Map<String, Spends> spendsMap;
	private TreeSet<Spends> ranks;

	/**
	 * Creates a map for spends information, also creates a {@link TreeSet} for
	 * calculating ranks. The ordering of {@link TreeSet} is in descending order
	 * also to keep it consistent with equals, comparator returns 0 if objects
	 * are equal.
	 */
	public LeaderBoard() {
		spendsMap = new HashMap<>();
		ranks = new TreeSet<>((o1, o2) -> {
			if (o1.getSpends() == o2.getSpends()) {
				return o1.equals(o2) ? 0 : 1;
			}
			return o2.getSpends() - o1.getSpends();
		});
	}

	/**
	 * Add spends and update Leader board. Reorders rank tree; Complexity of O(ln n)
	 * @param name
	 * @param spend
	 */
	public void addSpend(String name, int spend) {
		Spends spends;
		if (spendsMap.containsKey(name)) {
			spends = spendsMap.get(name);
			ranks.remove(spends);			// Remove old spends O(ln n)
			spends.addSpends(spend);
		} else {
			spends = new Spends(name, spend);
		}

		ranks.add(spends);					// Add updated spends O(ln n)
		spendsMap.put(name, spends);
	}

	/**
	 * Get Rank of particular element; uses a headset view, complexity: O(1)
	 * @param name
	 * @return rank of required element
	 */
	public int getRank(String name) {
		Spends spends = spendsMap.get(name);
		if (spends == null)
			throw new IllegalArgumentException("No Spends");

		return ranks.headSet(spends, true).size();
	}

	/**
	 * Gets top 10; uses iterator
	 * @return top 10 in LeaderBoard
	 */
	public List<Spends> topTen() {
		List<Spends> topTen = new ArrayList<>();
		Iterator<Spends> it = ranks.iterator();

		for (int i = 0; i < 10; i++) {
			if (it.hasNext()) {
				topTen.add(it.next());
			}
		}

		return topTen;
	}

	public static void main(String[] args) {
		LeaderBoard leaderBoard = new LeaderBoard();
		
		// Add spends
		leaderBoard.addSpend("ABC", 40);
		leaderBoard.addSpend("DEF", 60);
		leaderBoard.addSpend("XYZ", 20);
		leaderBoard.addSpend("JKL", 40);
		leaderBoard.addSpend("ABC", 40);

		// Add millions of random spends
		System.out.println(new Date());
		Random random = new Random();
		for (int i = 0; i < 1000000; i++) {
			leaderBoard.addSpend(name(random), (random.nextInt(10) * 10));
		}
		System.out.println("Add End: " + new Date());
		
		// Get rank
		System.out.println("Total Spends of XYZ: " + leaderBoard.spendsMap.get("XYZ").getSpends());
		System.out.println("XYZ's Rank: " + leaderBoard.getRank("XYZ"));
		System.out.println(new Date());			// Very efficient
		
		// Top Ten
		System.out.println(leaderBoard.topTen());
	}

	/**
	 * Generates random 3 letter names
	 * Only for test.
	 */
	static String name(Random random) {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		return sb.toString();
	}	
}