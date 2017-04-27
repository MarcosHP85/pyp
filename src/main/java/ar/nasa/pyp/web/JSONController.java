package ar.nasa.pyp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
//	@Autowired
//	private SimpMessagingTemplate template;
	
	@CrossOrigin
	@RequestMapping(value="{ot}", method = RequestMethod.GET)
	public @ResponseBody OtIfs getJSON(@PathVariable Integer ot){
		OtIfs o = this.otIfsService.getByOtId(ot);
		
//		this.template.convertAndSend("/topic/greetings", o.toString());
		
		return o;
	}
	
	@RequestMapping("all")
	public @ResponseBody List<OtIfs> getAllJSON(){
		List<OtIfs> o = this.otIfsService.list();
		
//		this.template.convertAndSend("/topic/greetings", o.toString());
		
		return o;
	}
}
