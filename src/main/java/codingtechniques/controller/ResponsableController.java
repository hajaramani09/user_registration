package codingtechniques.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import codingtechniques.model.Responsable;
import codingtechniques.service.ResponsableServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

public class ResponsableController {

	@Autowired
	public ResponsableServiceImpl userService;

	@RequestMapping(method = RequestMethod.POST, value = "/addUser")
	public ResponseEntity<Responsable> addUser(@Validated @RequestBody Responsable user) {
		return new ResponseEntity<Responsable>(userService.addUser(user), null, HttpStatus.CREATED);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/getUser")
	public List<Responsable> getUser() {
		return userService.getUsers();
	}

	@GetMapping("/getMe")
	public Responsable getMe() {
		return userService.getUser(userService.getAuthUser());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/responsable{IdR}")
	public ResponseEntity<Responsable> updateUser(@PathVariable Integer userId, @RequestBody Responsable user) {
		return new ResponseEntity<Responsable>(userService.updateUser(userId, user), null, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/responsable/{IdR}")
	public ResponseEntity<Responsable> deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<Responsable>(null, null, HttpStatus.OK);
	}
}
