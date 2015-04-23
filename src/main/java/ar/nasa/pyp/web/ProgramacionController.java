package ar.nasa.pyp.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.nasa.ifs.domain.OtIfs;
import ar.nasa.pyp.domain.Ot;
import ar.nasa.pyp.domain.Paquete;
import ar.nasa.pyp.domain.Programacion;
import ar.nasa.pyp.service.OtIfsService;
import ar.nasa.pyp.service.OtService;
import ar.nasa.pyp.service.ProgramacionService;

@Controller
public class ProgramacionController {

	@Autowired
	private ProgramacionService programacionService;
	@Autowired
	private OtIfsService otIfsService;
	@Autowired
	private OtService otService;
	
	@RequestMapping(value = "/programacion")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("IndexView");
		
//		Programacion programacion = new Programacion();
//		programacion.setTitulo("S1520");
//		programacion.setPlanta(2000);
//		
//		OtIfs otIfs = otIfsService.getByOtId(35837);
//		Ot ot = new Ot(otIfs);
//		
//		Paquete paquete = new Paquete();
//		ot.setPaquete(paquete);
//		
//		Programacion programacion = programacionService.findByTitulo("S1520");
//		Ot ot = new Ot(otIfsService.getByOtId(36201));
//		Set<Ot> ots = new HashSet<Ot>();
//		ots.add(ot);
//		
//		programacion.setOts(ots);
//		
//		programacion.setPlanta(2000);
//		programacion.setOts(ots);
//		
//		programacionService.save(programacion);
//		
//		for (Ot o : programacionService.findByTitulo("S1520").getOts()) {
//			System.out.println("===> " + o.getId() + " | " + o.getComponente() + " | " + o.getTarea());
//		}
		return model;
	}
	
	@PreAuthorize("hasRole('SUP_PLAN')")
	@RequestMapping(value = "/programacion/nueva")
	public ModelAndView programacion() {
		List<Integer> allPlantas = new ArrayList<Integer>();
		allPlantas.add(2000);
		allPlantas.add(4000);
		
		ModelAndView model = new ModelAndView("ProgramacionNuevaView");
		model.addObject("allPlantas", allPlantas);
		
		Programacion programacion = new Programacion();
		model.addObject("programacion", programacion);
		
		return model;
	}
	
	@RequestMapping(value = "/programacion/guardar", method = RequestMethod.POST)
	public ModelAndView programacionGuardar(@ModelAttribute Programacion programacion) {
		programacionService.save(programacion);
		
		return new ModelAndView("redirect:/programacion");
	}
}
