/**
 * 
 */
package org.ak.task.textprocessing.wordcounter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.ak.task.textprocessing.util.WordCounterUtil;
import org.springframework.stereotype.Service;

/**
 * @author Amarjeet Kumar
 *
 */
@Service
public class WordCounterService {

	private static final String SAMPLE_TEXT_FILE = "SampleTextFile.txt";
	private static final String CSV_SEPARATOR = "|";

	// The following two maps will be used to cache the word counts
	private static Map<String, Integer> wordCountMap = null;
	private static Map<String, Integer> sortedWordCounts = null;


	public SearchResponse searchWords(List<String> searchWords) throws IOException {

		if (wordCountMap == null) {
			// Map will be populated only once
			readWordsIntoMap();
		}

		SearchResponse response = new SearchResponse();

		Map<String, Integer> wordCount = new HashMap<>();

		for (String word : searchWords) {
			System.out.println("Finding the count of : " + word);
			wordCount.put(word, wordCountMap.getOrDefault(word.toLowerCase(), 0));
		}

		response.setCounts(wordCount);

		return response;
	}

	public String findTopWords(int topCounter) throws IOException {
		if (topCounter < 1) {
			return "";
		}
		if (sortedWordCounts == null) {
			System.out.println("sortedWordCounts is null. Will sort the word counts first");
			// Word Count map will be sorted based on the value, only once
			prepareSortedWordCountMap();

		}
		String result = "";
		int counter = 0;
		for (Entry<String, Integer> entry : sortedWordCounts.entrySet()) {
			if (counter < topCounter) {
				result = result + entry.getKey() + CSV_SEPARATOR + entry.getValue() + "\n";
				counter++;
			}
		}
		return result.trim();
	}

	private void prepareSortedWordCountMap() throws IOException {

		if (wordCountMap == null) {
			// Map will be populated only once
			readWordsIntoMap();
		}
		System.out.println("Sorting the wordCountMap in based on values i.e. word count");
		System.out.println("Original Map:" + wordCountMap);

		sortedWordCounts = wordCountMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		System.out.println("Sorted   Map:" + sortedWordCounts);
	}

	private void readWordsIntoMap() throws IOException {
		System.out.println("wordCountMap is null. Reading words from file.");
		wordCountMap = WordCounterUtil.getWordCountMap(SAMPLE_TEXT_FILE);
	}
}
