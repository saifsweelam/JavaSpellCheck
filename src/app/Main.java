package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dictionary.WordLooker;

public class Main {
	public static void main(String[] args) {
		WordLooker wordlooker = new WordLooker();

		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the string to start spell checking");
			String text = sc.nextLine();
			String[] words = text.split(" ");

			List<String> wrongWords = new ArrayList<>();
			int j = 1;

			System.out.println();

			for (int i = 0; i < words.length; i++) {
				System.out.print(words[i]);
				if (!wordlooker.exists(words[i].replaceAll("[^a-zA-Z ]", ""))) {
					wrongWords.add(words[i]);
					System.out.print('(' + Integer.toString(j) + ')');
					j++;
				}
				System.out.print(" ");
			}

			System.out.println();
			System.out.println();
			System.out.println("Suggestions:");
			System.out.println();

			for (int i = 0; i < wrongWords.size(); i++) {
				System.out.printf("(%d) %s: ", i+1, wrongWords.get(i));
				System.out.println(String.join(", ", wordlooker.matchWord(wrongWords.get(i))));
			}
		}
	}
}
