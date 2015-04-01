package ar.nasa.pyp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("IndexView");
		
		return model;
	}
}
