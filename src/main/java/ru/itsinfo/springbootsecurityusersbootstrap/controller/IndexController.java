package ru.itsinfo.springbootsecurityusersbootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import ru.itsinfo.springbootsecurityusersbootstrap.config.exception.LoginException;
import ru.itsinfo.springbootsecurityusersbootstrap.service.AppService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class IndexController {
	private final AppService appService;

	@Autowired
	public IndexController(AppService appService) {
		this.appService = appService;
	}

	// todo
	// https://www.baeldung.com/spring-security-csrf
	// https://docs.spring.io/spring-security/site/docs/3.2.0.CI-SNAPSHOT/reference/html/csrf.html
	@GetMapping("/")
	public String welcomePage(Model model, HttpSession session,
							  @SessionAttribute(required = false, name = "Authentication-Exception") LoginException authenticationException,
							  @SessionAttribute(required = false, name = "Authentication-Name") String authenticationName) {
		appService.authenticateOrLogout(model, session, authenticationException, authenticationName);
		return "index";
	}

	@GetMapping("/403") // todo
	public String accessDeniedPage() {
		return "access-denied";
	}
}