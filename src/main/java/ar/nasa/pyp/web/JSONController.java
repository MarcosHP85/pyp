package ar.nasa.pyp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.nasa.ifs.domain.OtIfs;
import ar.nasa.pyp.service.OtIfsService;

@Controller
@RequestMapping(value = "/json")
public class JSONController {

	@Autowired
	private OtIfsService otIfsService;
	
	@RequestMapping(value="{ot}", method = RequestMethod.GET)
	public @ResponseBody OtIfs getJSON(@PathVariable Integer ot){
		return this.otIfsService.getByOtId(ot);
	}
}
