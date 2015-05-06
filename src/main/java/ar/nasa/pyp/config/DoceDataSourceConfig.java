package ar.nasa.pyp.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="doce")
public class DoceDataSourceConfig {

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

	@Bean
	public DoceSemanasSettings doceCna1Workbook() throws FileNotFoundException, IOException {
		return new DoceSemanasSettings(fileCna1, fileCna2, firstRow, colNumOt, colSemana, colObservaciones, colLu, colMa, colMi, colJu, colVi, colSa, colDo);
	}

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
	

}
