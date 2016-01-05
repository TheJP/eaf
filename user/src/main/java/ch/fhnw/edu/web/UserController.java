package ch.fhnw.edu.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.edu.model.User;
import ch.fhnw.edu.persistence.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	private Log log = LogFactory.getLog(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userRepository.findAll();
		log.debug("Found " + users.size() + " users");
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}