/**
 * 
 */
package ak.task.sample.textprocessing;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amarjeet Kumar
 *
 */
@RestController
public class GreetUser {

	@RequestMapping("/webapi/test")
	public String sayHi() {
		return "Hello There! This application requires basic authentication in order to use the services.";
	}
	
	@RequestMapping("/home")
	public String sayHiOnHomePage() {
		return "Hello There! You have reached home page. "
				+ "\nThis application requires basic authentication in order to use the services.";
	}
}
