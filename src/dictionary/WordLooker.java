package dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordLooker {
	private String[] words;
	private final String FILE_NAME = "words.txt";

	public WordLooker() {
		InputStream inputStream = this.getClass().getResourceAsStream(FILE_NAME);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		List<String> wordlist = new ArrayList<>();
		String line;

		try {
			while ((line = reader.readLine()) != null) {
			    wordlist.add(line.toLowerCase());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		wordlist.sort(null);
		this.words = wordlist.toArray(new String[0]);
	}

	public boolean exists(String word) {
		return Arrays.binarySearch(this.words, word.toLowerCase()) >= 0;
	}

	public List<String> matchWord(String word) {
		List<String> matchingWords = new ArrayList<>();
		String currentWord;
		for (int i = 0; i < word.length(); i++) {
			currentWord = word.substring(0, i) + word.substring(i+1);
			if (this.exists(currentWord)) matchingWords.add(currentWord);

			for (int j = 0; j < 26; j++) {
				currentWord = word.substring(0, i) + (char) (j + 97) + word.substring(i+1);
				if (this.exists(currentWord)) matchingWords.add(currentWord);
			}
		}
		return matchingWords;
	}
}
