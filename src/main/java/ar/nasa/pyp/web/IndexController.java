package ar.nasa.pyp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcProperties.View;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ar.nasa.pyp.service.OtIfsService;

@RestController
public class IndexController {

	@Autowired
	OtIfsService otIfsService;
	
//	@CrossOrigin
//	@RequestMapping(value="/")
//	public String index() {
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		System.out.println(auth.getName());
////		System.out.println(auth.getPrincipal());
////		if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_NUEVO")))
////			System.out.println("Redireccionando a usuario nuevo...");
////		
////		ModelAndView view = new ModelAndView("index.html");
//		
//		//otIfsService.list();
//		
//		return "index.html";
//	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request) {
		
		Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if(exception instanceof LockedException) {
			System.out.println(exception.getMessage());
		}
		
		ModelAndView model = new ModelAndView("LoginView");
		
		return model;
	}
}
