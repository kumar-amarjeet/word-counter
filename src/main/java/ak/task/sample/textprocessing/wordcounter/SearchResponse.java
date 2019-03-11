/**
 * 
 */
package ak.task.sample.textprocessing.wordcounter;

import java.util.Map;

/**
 * @author Amarjeet Kumar
 *
 */
public class SearchResponse {
	
	private Map<String, Integer> counts;

	public Map<String, Integer> getCounts() {
		return counts;
	}

	public void setCounts(Map<String, Integer> counts) {
		this.counts = counts;
	}
}
