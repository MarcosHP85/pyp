package ar.nasa.pyp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.nasa.pyp.service.OtIfsService;

@Controller
public class IndexController {

	@Autowired
	OtIfsService otIfsService;
	
	@RequestMapping(value="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("IndexView");
		
		otIfsService.list();
		
		return model;
	}
}
