package ar.nasa.pyp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.nasa.pyp.domain.User;
import ar.nasa.pyp.ifs.domain.OtActivaIfs;
import ar.nasa.pyp.ifs.service.OtActivaIfsService;

@Controller
@RequestMapping(value = "/api/ot_activa")
public class OtActivaIfsController {

	@Autowired
	private OtActivaIfsService otActivaIfsService;
	
	@RequestMapping(value = "search")
	public @ResponseBody List<OtActivaIfs> searchOts(@RequestParam("ots") String ots) {
		List<Long> numOts = new ArrayList<Long>();
		String[] stringNumOts = ots.split(",");
		
		for (String ot: stringNumOts)
			numOts.add(Long.parseLong(ot));
		
		return otActivaIfsService.searchOts(numOts);
	}
	
	@RequestMapping(value = "save/planifica", method = RequestMethod.POST)
	public @ResponseBody OtActivaIfs setPlanifica(@RequestBody OtActivaIfs ot, @AuthenticationPrincipal User activeUser) {
		OtActivaIfs tmp = otActivaIfsService.encontrarOtNumero(ot.getNumOt());
		tmp.setPlanifica(activeUser.getIfsUser());
		
		return otActivaIfsService.actualizarOt(tmp);
	}
}
