/**
 * 
 */
package org.ak.task.textprocessing.wordcounter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Amarjeet Kumar
 *
 */
@RestController
@RequestMapping("/counter-api")
public class WordCounterController {
	
	@Autowired
	private WordCounterService wordCounterService;

	@RequestMapping(method = RequestMethod.POST, value = "/search")
	public SearchResponse findWordCount(@RequestBody SearchRequest searchText) {
		try {
			return wordCounterService.searchWords(searchText.getSearchText());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File could not be read");
		}
	}
	
	@RequestMapping(value = "/top/{number}", produces = "text/csv")
	public String findTopWords(@PathVariable int number) {
		try {
			return wordCounterService.findTopWords(number);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File could not be read");
		}
	}
}
