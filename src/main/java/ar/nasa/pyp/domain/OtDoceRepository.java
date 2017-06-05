package ar.nasa.pyp.domain;

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
import org.springframework.stereotype.Component;

import ar.nasa.pyp.config.DoceSemanasSettings;

@Component
public class OtDoceRepository {

	@Autowired
	private DoceSemanasSettings settings;
	
	public List<OtDoce> findByPlantaAndSemana(Integer planta, Integer semana) {
		return null;
	}
	
	private List<OtDoce> findBy(Integer planta, Criterio criterio) {
		List<OtDoce> ots = new ArrayList<OtDoce>();
		try {
			String path = planta == 2000
					? settings.getFileCna1() 
					: settings.getFileCna2();
			
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			Sheet sheet = wb.getSheetAt(0);

		    int rowHead = settings.getFirstRow(); // Numero de fila con los titulos
		    int rows = sheet.getPhysicalNumberOfRows(); // Total de filas

		    Row row;
		    
		    for (int r = rowHead; r < rows; r++) {
		        row = sheet.getRow(r);
		        if (row != null) {
	                Cell cellNumOt = row.getCell(settings.getColNumOt());
	                if (cellNumOt != null && cellNumOt.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	                	if (criterio.run(row))
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
	
	private class Criterio {
		public boolean run(Row row) {
			return true;
		}
	}
}
