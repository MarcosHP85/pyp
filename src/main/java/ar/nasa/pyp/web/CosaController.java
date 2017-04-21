package ar.nasa.pyp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.nasa.pyp.domain.Cosa;
import ar.nasa.pyp.service.CosaService;

@Controller
public class CosaController {

	@Autowired
	CosaService cosaService;
	
	@RequestMapping(value="/cosa")
	public String nuevaCosa() {
		
		Cosa c = new Cosa();
		c.setName("Nombre de la cosa");
		
		System.out.println(cosaService.save(c));
		
		return "OK";
	}
}
