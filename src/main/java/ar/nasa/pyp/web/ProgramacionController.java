package ar.nasa.pyp.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.nasa.ifs.domain.OtIfs;
import ar.nasa.pyp.domain.Programacion;
import ar.nasa.pyp.service.OtDoceService;
import ar.nasa.pyp.service.OtIfsService;
import ar.nasa.pyp.service.OtService;
import ar.nasa.pyp.service.ProgramacionService;

@Controller
@RequestMapping(value = "/{planta}/programacion")
public class ProgramacionController {

	@Autowired
	private ProgramacionService programacionService;
	@Autowired
	private OtIfsService otIfsService;
	@Autowired
	private OtDoceService otDoceService;
	@Autowired
	private OtService otService;
	
	@ModelAttribute("allPlantas")
	public List<Integer> allPlantas() {
		List<Integer> allPlantas = new ArrayList<Integer>();
		allPlantas.add(2000);
		allPlantas.add(4000);
		return allPlantas;
	}
	
	@RequestMapping
	public ModelAndView index(
			@PathVariable("planta") Integer planta) {
		ModelAndView model = new ModelAndView("ProgramacionView");
		
		model.addObject("planta",planta);
		
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
	
	@RequestMapping(value = "/editar")
	public ModelAndView programacionEditar(
			@PathVariable("planta") Integer planta,
			@RequestParam("semana") Integer semana,
			@RequestParam("estado") String estado) {
		
		ModelAndView model = new ModelAndView("ProgramacionEditarView");
		
//		List<OtIfs> ots;
//		
//		if (estado.equals("parada"))
//			ots = otIfsService.getParadaByPlantaYSemana(planta, semana);
//		else
//			ots = otIfsService.getByPlantaYSemana(planta,semana);

		model.addObject("planta", planta);
		model.addObject("semana", semana);
		model.addObject("estado", estado);
		model.addObject("ots", otDoceService.findBySemana(semana));
		
		return model;
	}
	
	@PreAuthorize("hasRole('SUP_PLAN')")
	@RequestMapping(value = "/nueva")
	public ModelAndView programacion() {
		
		ModelAndView model = new ModelAndView("ProgramacionNuevaView");
		
		Programacion programacion = new Programacion();
		model.addObject("programacion", programacion);
		System.out.println(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));
		System.out.println(Calendar.getInstance(Locale.FRANCE).getFirstDayOfWeek());
		return model;
	}
	
	@RequestMapping(value = "/nueva", params = {"addOt"}, method = RequestMethod.POST)
	public ModelAndView addOt(@ModelAttribute Programacion programacion, BindingResult bindingResult, Model model) {
//		programacion.getOts().add(new Ot());
		System.out.println("holaaa  " + programacion.getTitulo());
		return new ModelAndView("ProgramacionNuevaView");
	}
	
	@RequestMapping(value = "/nueva", params = {"guardar"}, method = RequestMethod.POST)
	public ModelAndView programacionGuardar(@ModelAttribute Programacion programacion) {
		programacionService.save(programacion);
		
		return new ModelAndView("redirect:/programacion");
	}
	
	@RequestMapping(value = "/doce")
	public ModelAndView poiBean() throws FileNotFoundException, IOException {
		ModelAndView model = new ModelAndView("ProgramacionView");
		otDoceService.findBySemana(1520);
		return model;
	}
}
