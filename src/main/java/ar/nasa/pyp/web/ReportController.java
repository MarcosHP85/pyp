package ar.nasa.pyp.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.nasa.pyp.service.OtIfsService;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

	@Autowired
	private OtIfsService otIfsService;
	
	@RequestMapping(value="{ot}", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
	public @ResponseBody byte[] getReport(@PathVariable Integer ot){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
		      // 1) Load Docx file by filling Velocity template engine and cache it to the registry
		      InputStream in = ReportController.class.getResourceAsStream("LVER.docx");
		      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);

		      // 2) Create context Java model
		      IContext context = report.createContext();
		      context.put("ot", this.otIfsService.getByOtId(ot));
		      context.put("check1", true);
		      context.put("check2", false);

		      // 3) Generate report by merging Java model with the Docx
		      report.process(context, out);

		    } catch (IOException e) {
		      e.printStackTrace();
		    } catch (XDocReportException e) {
		      e.printStackTrace();
		    }
		return out.toByteArray();
	}
}
