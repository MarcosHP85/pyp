package ar.nasa.pyp.config;

public class DoceSemanasSettings {

	private String fileCna1;
	private String fileCna2;
	private Integer firstRow;
	private Integer colNumOt;
	private Integer colSemana;
	private Integer colObservaciones;
	private Integer colLu;
	private Integer colMa;
	private Integer colMi;
	private Integer colJu;
	private Integer colVi;
	private Integer colSa;
	private Integer colDo;
	
	public String getFileCna1() {
		return fileCna1;
	}
	public void setFileCna1(String fileCna1) {
		this.fileCna1 = fileCna1;
	}
	public String getFileCna2() {
		return fileCna2;
	}
	public void setFileCna2(String fileCna2) {
		this.fileCna2 = fileCna2;
	}
	public Integer getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}
	public Integer getColNumOt() {
		return colNumOt;
	}
	public void setColNumOt(Integer colNumOt) {
		this.colNumOt = colNumOt;
	}
	public Integer getColSemana() {
		return colSemana;
	}
	public void setColSemana(Integer colSemana) {
		this.colSemana = colSemana;
	}
	public Integer getColObservaciones() {
		return colObservaciones;
	}
	public void setColObservaciones(Integer colObservaciones) {
		this.colObservaciones = colObservaciones;
	}
	public Integer getColLu() {
		return colLu;
	}
	public void setColLu(Integer colLu) {
		this.colLu = colLu;
	}
	public Integer getColMa() {
		return colMa;
	}
	public void setColMa(Integer colMa) {
		this.colMa = colMa;
	}
	public Integer getColMi() {
		return colMi;
	}
	public void setColMi(Integer colMi) {
		this.colMi = colMi;
	}
	public Integer getColJu() {
		return colJu;
	}
	public void setColJu(Integer colJu) {
		this.colJu = colJu;
	}
	public Integer getColVi() {
		return colVi;
	}
	public void setColVi(Integer colVi) {
		this.colVi = colVi;
	}
	public Integer getColSa() {
		return colSa;
	}
	public void setColSa(Integer colSa) {
		this.colSa = colSa;
	}
	public Integer getColDo() {
		return colDo;
	}
	public void setColDo(Integer colDo) {
		this.colDo = colDo;
	}
	
	public DoceSemanasSettings(String fileCna1, String fileCna2,
			Integer firstRow, Integer colNumOt, Integer colSemana,
			Integer colObservaciones, Integer colLu, Integer colMa,
			Integer colMi, Integer colJu, Integer colVi, Integer colSa,
			Integer colDo) {
		super();
		this.fileCna1 = fileCna1;
		this.fileCna2 = fileCna2;
		this.firstRow = firstRow;
		this.colNumOt = colNumOt;
		this.colSemana = colSemana;
		this.colObservaciones = colObservaciones;
		this.colLu = colLu;
		this.colMa = colMa;
		this.colMi = colMi;
		this.colJu = colJu;
		this.colVi = colVi;
		this.colSa = colSa;
		this.colDo = colDo;
	}
	@Override
	public String toString() {
		return "DoceSemanasSettings [fileCna1=" + fileCna1 + ", fileCna2="
				+ fileCna2 + ", firstRow=" + firstRow + ", colNumOt="
				+ colNumOt + ", colSemana=" + colSemana + ", colObservaciones="
				+ colObservaciones + ", colLu=" + colLu + ", colMa=" + colMa
				+ ", colMi=" + colMi + ", colJu=" + colJu + ", colVi=" + colVi
				+ ", colSa=" + colSa + ", colDo=" + colDo + "]";
	}
	
}
