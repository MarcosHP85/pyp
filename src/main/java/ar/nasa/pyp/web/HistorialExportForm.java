package ar.nasa.pyp.web;

import java.util.List;

import ar.nasa.pyp.ifs.domain.OtIfsHistorica;


public class HistorialExportForm {

	private List<OtIfsHistorica> ots;

	public HistorialExportForm() {
	}

	public HistorialExportForm(List<OtIfsHistorica> ots) {
		this.ots = ots;
	}
	
	public List<OtIfsHistorica> getOts() {
		return ots;
	}

	public void setOts(List<OtIfsHistorica> ots) {
		this.ots = ots;
	}
	
}
