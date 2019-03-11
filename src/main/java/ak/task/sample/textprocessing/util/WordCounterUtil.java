/**
 * 
 */
package ak.task.sample.textprocessing.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Amarjeet Kumar
 *
 */
public class WordCounterUtil {
	
	public static Map<String, Integer> getWordCountMap(String filePath) throws IOException {
		
		System.out.println("Reading words from file : " + filePath);
		
		return Files.lines(Paths.get(filePath))
			    .flatMap(Pattern.compile("\\W+")::splitAsStream)
			    .filter(s -> s.length() >= 2)
			    .map(String::toLowerCase)
			    .collect(Collectors.groupingBy(w->w, Collectors.summingInt(w->1)));
	}

}
