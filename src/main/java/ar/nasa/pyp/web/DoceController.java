package ar.nasa.pyp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.nasa.pyp.domain.OtDoce;
import ar.nasa.pyp.service.OtDoceService;

@Controller
@RequestMapping(value = "/api/doce")
public class DoceController {

	@Autowired
	private OtDoceService otDoceService;
	
	@RequestMapping(value = "search")
	public @ResponseBody List<OtDoce> search(@RequestParam("s") Integer semana) {
		return otDoceService.findBySemana(semana, 2000);
	}
}
