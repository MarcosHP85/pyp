package ar.nasa.pyp.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.CellTextFormatter;
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
	public List<OtDoce> findBySemana(Integer semana) {
		List<OtDoce> ots = new ArrayList<OtDoce>();
		
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(settings.getFileCna1()));
			
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
	                if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK 
	                		&& row.getCell(settings.getColSemana()).getCellType() == Cell.CELL_TYPE_NUMERIC
	                		&& (int)row.getCell(settings.getColSemana()).getNumericCellValue() == semana) {
	                	
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

	private OtDoce rowToOtDoce(Row row) {
		OtDoce ot = new OtDoce();
    	
    	if(row.getCell(settings.getColNumOt()).getCellType() == Cell.CELL_TYPE_NUMERIC) {

    		ot.setId((int)row.getCell(settings.getColNumOt()).getNumericCellValue());

	    	if(row.getCell(settings.getColSemana()).getCellType() == Cell.CELL_TYPE_NUMERIC)
	    		ot.setSemana((int)row.getCell(settings.getColSemana()).getNumericCellValue());
	    	if(row.getCell(settings.getColObservaciones()).getCellType() == Cell.CELL_TYPE_STRING)
	    		ot.setObservaciones(row.getCell(settings.getColObservaciones()).getStringCellValue());
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
