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

import ar.nasa.pyp.ei1.domain.ReciUnidad1;
import ar.nasa.pyp.ei1.service.ReciUnidad1Service;
import ar.nasa.pyp.ei2.domain.ReciUnidad2;
import ar.nasa.pyp.ei2.service.ReciUnidad2Service;
import ar.nasa.pyp.ifs.domain.OtIfs;
import ar.nasa.pyp.ifs.service.OtIfsService;
import ar.nasa.pyp.rdn.domain.ReunionDiaria;
import ar.nasa.pyp.rdn.service.ReunionDiariaService;

@Controller
@RequestMapping(value = "/json")
public class JSONController {

	@Autowired
	private OtIfsService otIfsService;
	@Autowired
	private ReunionDiariaService reunionDiariaService;
	@Autowired
	private ReciUnidad1Service reciUnidad1Service;
	@Autowired
	private ReciUnidad2Service reciUnidad2Service;
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
	
	@RequestMapping("reunion")
	public @ResponseBody List<ReunionDiaria> getReunion(){
		
		return reunionDiariaService.ultimasReuniones();
	}
	
	@RequestMapping("indisponibles1")
	public @ResponseBody List<ReciUnidad1> getIndisponibles1(){
		
		return reciUnidad1Service.equiposIndisponibles();
	}
	
	@RequestMapping("indisponibles2")
	public @ResponseBody List<ReciUnidad2> getIndisponibles2(){
		
		return reciUnidad2Service.equiposIndisponibles();
	}
}
