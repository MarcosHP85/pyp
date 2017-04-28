package ar.nasa.pyp.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.nasa.ifs.domain.OtIfsHistorica;
import ar.nasa.ifs.service.OtIfsHistoricaService;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

@Controller
@RequestMapping(value = "/historial")
public class HistorialController {

	@Autowired
	private OtIfsHistoricaService otIfsHistoricaService;
	
	@RequestMapping
	public @ResponseBody String historialForm(Model model){
		model.addAttribute("historicaForm", new OtIfsHistorica());
		return "HistorialView";
	}
	
	@RequestMapping(value="/buscar")
	public String historialSubmit(
			@ModelAttribute(value="historicaForm") OtIfsHistorica historicaForm,
			Model model){
		
		model.addAttribute("historicaForm", historicaForm);
		model.addAttribute("historicas", 
				this.otIfsHistoricaService.findByComponenteLikeOrderByComponenteAsc(historicaForm.getComponente().toUpperCase()));
		model.addAttribute("seleccionadas", new HistorialExportForm());
		
		return "HistorialView";
	}
	
	@RequestMapping(value="/exportar", produces = "application/pdf")
	public @ResponseBody byte[] historialExportSubmit(
			@ModelAttribute(value="seleccionadas") HistorialExportForm historialExportForm,
			@ModelAttribute(value="historicaForm") OtIfsHistorica historicaForm,
			Model model){
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		      // 1) Load Docx file by filling Velocity template engine and cache it to the registry
		      InputStream in = ReportController.class.getResourceAsStream("HistorialReport.odt");
		      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Freemarker);

		      // 2) Create context Java model
		      IContext context = report.createContext();
		      List<OtIfsHistorica> ots = historialExportForm.getOts();
		      for (OtIfsHistorica ot : ots){
		    	  if (ot.getTrabajoRealizado() == null)
		    		  ot.setTrabajoRealizado("---");
		      }
		      context.put("ots", ots);

		      // 3) Generate report by merging Java model with the Docx
		      Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
		      report.convert(context, options, out);

		    } catch (IOException e) {
		      e.printStackTrace();
		    } catch (XDocReportException e) {
		      e.printStackTrace();
		    }
		return out.toByteArray();
	}
	
	@RequestMapping(value="{componente}", method = RequestMethod.GET)
	public @ResponseBody List<OtIfsHistorica> getHistorial(@PathVariable String componente){
		return this.otIfsHistoricaService.findByComponenteLikeOrderByComponenteAsc(componente.toUpperCase());
	}
}
