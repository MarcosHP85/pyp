package ar.nasa.pyp.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public @ResponseBody Set<OtDoce> search(@RequestParam(value = "s", required = false) Integer semana,
			@RequestParam(value = "ots", required = false) String ots) {
		Set<OtDoce> tmp = new HashSet<OtDoce>();
		
		if (semana != null)
			tmp.addAll(otDoceService.findBySemana(semana));
		
		if (ots != null && tmp.size() >= ots.length()) {
			List<Long> numOts = new ArrayList<Long>();
			String[] stringNumOts = ots.split(",");
			
			for (String ot: stringNumOts)
				numOts.add(Long.parseLong(ot));
			
			tmp.addAll(otDoceService.findByOtIn(numOts));
		}
		
		return tmp;
	}
	
	@RequestMapping(value = "{planta}/search")
	public @ResponseBody List<OtDoce> search(@RequestParam("s") Integer semana, @PathVariable Integer planta) {
		return otDoceService.findByPlantaAndSemana(planta, semana);
	}
}
