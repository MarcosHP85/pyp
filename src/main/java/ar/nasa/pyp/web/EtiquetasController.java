package ar.nasa.pyp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/etiquetas")
public class EtiquetasController {

	@RequestMapping
	public ModelAndView main(){
		
		ModelAndView model = new ModelAndView("EtiquetasView");
		
		return model;
	}
}
