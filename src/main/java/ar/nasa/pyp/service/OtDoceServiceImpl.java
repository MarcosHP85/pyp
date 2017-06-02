package ar.nasa.pyp.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.nasa.pyp.config.DoceSemanasSettings;
import ar.nasa.pyp.domain.OtDoce;

@Service
public class OtDoceServiceImpl implements OtDoceService {

	@Autowired
	private DoceSemanasSettings settings;
		
	@Override
	public List<OtDoce> findBySemana(Integer semana, Integer planta) {
		List<OtDoce> ots = new ArrayList<OtDoce>();
		
		try {
			String path = planta == 4000 ? settings.getFileCna2() : settings.getFileCna1();
			
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			
			Sheet sheet = wb.getSheetAt(0);
			Row row;
		    Cell cell;
			
		    int rowHead = settings.getFirstRow(); // Numero de fila con los titulos
		    int rows = sheet.getPhysicalNumberOfRows(); // Total de filas

		    int col = settings.getColNumOt(); // Columna con los numeros de ot

		    for(int r = rowHead; r < rows; r++) {
		        row = sheet.getRow(r);
		        if(row != null) {
	                cell = row.getCell(col);
	                if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK &&
	                		laColumnaEsIgual(row, settings.getColSemana(), semana) &&
	                		laColumnaEmpiezaCon(row, settings.getColOrgMant(), "C")) {
	                	
	                	ots.add(rowToOtDoce(row));
	                }
		        }
		    }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return ots;
	}

	private boolean laColumnaEs(Row row, Integer columna) {
		return row.getCell(columna) != null &&
				row.getCell(columna).getCellType() == Cell.CELL_TYPE_NUMERIC;
	}
	
	private boolean laColumnaEsIgual(Row row, Integer columna, Integer valor) {
		return laColumnaEs(row, columna) && 
				(int)row.getCell(columna).getNumericCellValue() == valor;
	}
	
	private boolean laColumnaEs(Row row, Integer columna, String valor) {
		return row.getCell(columna) != null &&
				row.getCell(columna).getCellType() == Cell.CELL_TYPE_STRING;
	}
	
	private boolean laColumnaEsIgual(Row row, Integer columna, String valor) {
		return laColumnaEs(row, columna, valor) &&
				row.getCell(columna).getStringCellValue().equals(valor);
	}
	
	private boolean laColumnaEmpiezaCon(Row row, Integer columna, String valor) {
		return laColumnaEs(row, columna, valor) &&
				row.getCell(columna).getStringCellValue().startsWith(valor);
	}
	
	private OtDoce rowToOtDoce(Row row) {
		OtDoce ot = new OtDoce();
    	
    	if(row.getCell(settings.getColNumOt()).getCellType() == Cell.CELL_TYPE_NUMERIC) {

    		ot.setId((int)row.getCell(settings.getColNumOt()).getNumericCellValue());

	    	if(row.getCell(settings.getColSemana()).getCellType() == Cell.CELL_TYPE_NUMERIC)
	    		ot.setSemana((int)row.getCell(settings.getColSemana()).getNumericCellValue());
	    	if(row.getCell(settings.getColComponente()).getCellType() == Cell.CELL_TYPE_STRING)
	    		ot.setComponente(row.getCell(settings.getColComponente()).getStringCellValue());
	    	if(row.getCell(settings.getColOrgMant()).getCellType() == Cell.CELL_TYPE_STRING)
	    		ot.setOrgMant(row.getCell(settings.getColOrgMant()).getStringCellValue());
	    	if(row.getCell(settings.getColObservaciones()).getCellType() == Cell.CELL_TYPE_STRING)
	    		ot.setObservaciones(row.getCell(settings.getColObservaciones()).getStringCellValue());
	    	if(HSSFDateUtil.isCellDateFormatted(row.getCell(settings.getColFInicio())))
	    		ot.setFechaInicio(row.getCell(settings.getColFInicio()).getDateCellValue());
	    	if(HSSFDateUtil.isCellDateFormatted(row.getCell(settings.getColFFin())))
	    		ot.setFechaFin(row.getCell(settings.getColFFin()).getDateCellValue());
	    	if(row.getCell(settings.getColLu()).getCellType() == Cell.CELL_TYPE_STRING 
	    			&& row.getCell(settings.getColLu()).getStringCellValue().equals("X"))
	    		ot.setLunes(true);
	    	if(row.getCell(settings.getColLu()).getCellType() == Cell.CELL_TYPE_STRING 
	    			&& row.getCell(settings.getColMa()).getStringCellValue().equals("X"))
	    		ot.setMartes(true);
	    	if(row.getCell(settings.getColLu()).getCellType() == Cell.CELL_TYPE_STRING 
	    			&& row.getCell(settings.getColMi()).getStringCellValue().equals("X"))
	    		ot.setMiercoles(true);
	    	if(row.getCell(settings.getColLu()).getCellType() == Cell.CELL_TYPE_STRING 
	    			&& row.getCell(settings.getColJu()).getStringCellValue().equals("X"))
	    		ot.setJueves(true);
	    	if(row.getCell(settings.getColLu()).getCellType() == Cell.CELL_TYPE_STRING 
	    			&& row.getCell(settings.getColVi()).getStringCellValue().equals("X"))
	    		ot.setViernes(true);
	    	if(row.getCell(settings.getColLu()).getCellType() == Cell.CELL_TYPE_STRING 
	    			&& row.getCell(settings.getColSa()).getStringCellValue().equals("X"))
	    		ot.setSabado(true);
	    	if(row.getCell(settings.getColLu()).getCellType() == Cell.CELL_TYPE_STRING 
	    			&& row.getCell(settings.getColDo()).getStringCellValue().equals("X"))
	    		ot.setDomingo(true);
    	}
    	
    	return ot;
	}
}
