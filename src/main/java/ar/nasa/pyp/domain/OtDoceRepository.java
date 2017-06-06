package ar.nasa.pyp.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		List<OtDoce> ots = new ArrayList<OtDoce>();
		Row row;
		Cell cellSemana;
		Sheet sheet = sheetPlanta(planta);
		for (int r = settings.getFirstRow(); r < sheet.getPhysicalNumberOfRows(); r++) {
			row = sheet.getRow(r);
			if (row == null)
				continue;
			
			cellSemana = row.getCell(settings.getColSemana());
			if (cellSemana == null)
				continue;
			
			if ((cellSemana.getCellType() == Cell.CELL_TYPE_NUMERIC && 
					cellSemana.getNumericCellValue() == semana) ||
					(cellSemana.getCellType() == Cell.CELL_TYPE_STRING &&
					cellSemana.getStringCellValue().startsWith(semana.toString()))) {
				ots.add(rowToOtDoce(row));
			}
		}
		return ots;
	}
	
	public List<OtDoce> findBySemana(Integer semana) {
		List<OtDoce> tmp = findByPlantaAndSemana(2000, semana);
		tmp.addAll(findByPlantaAndSemana(4000, semana));
		return tmp;
	}
	
	public OtDoce findByPlantaAndOt(Integer planta, Integer ot) {
		Row row;
		Cell cellOt;
		Sheet sheet = sheetPlanta(planta);
		for (int r = settings.getFirstRow(); r < sheet.getPhysicalNumberOfRows(); r++) {
			row = sheet.getRow(r);
			if (row == null)
				continue;
			
			cellOt = row.getCell(settings.getColNumOt());
			if (cellOt == null)
				continue;
			
			if (cellOt.getCellType() == Cell.CELL_TYPE_NUMERIC && 
					cellOt.getNumericCellValue() == ot) {
				return rowToOtDoce(row);
			}
		}
		return null;
	}
	
	public OtDoce findByOt(Integer ot) {
		OtDoce tmp = findByPlantaAndOt(2000, ot);
		return (tmp == null) ? findByPlantaAndOt(4000, ot) : tmp;
	}
	
	public List<OtDoce> findByPlantaAndComponente(Integer planta, String componente) {
		List<OtDoce> ots = new ArrayList<OtDoce>();
		Row row;
		Cell cellComponente;
		Sheet sheet = sheetPlanta(planta);
		for (int r = settings.getFirstRow(); r < sheet.getPhysicalNumberOfRows(); r++) {
			row = sheet.getRow(r);
			if (row == null)
				continue;
			
			cellComponente = row.getCell(settings.getColComponente());
			if (cellComponente == null)
				continue;
			
			if (cellComponente.getCellType() == Cell.CELL_TYPE_STRING && 
					cellComponente.getStringCellValue().equals(componente)) {
				ots.add(rowToOtDoce(row));
			}
		}
		return ots;
	}
	
	public List<OtDoce> findByComponente(String componente) {
		List<OtDoce> tmp = findByPlantaAndComponente(2000, componente);
		tmp.addAll(findByPlantaAndComponente(4000, componente));
		return tmp;
	}
	
	private Sheet sheetPlanta(Integer planta) {
		Sheet sheet = null;
		try {
			String path = planta == 2000
					? settings.getFileCna1() 
					: settings.getFileCna2();
			
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			sheet = wb.getSheetAt(0);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return sheet;
	}
	
	private OtDoce rowToOtDoce(Row row) {
		OtDoce ot = new OtDoce();
    	
    	if(row.getCell(settings.getColNumOt()).getCellType() == Cell.CELL_TYPE_NUMERIC) {

    		ot.setId((int)row.getCell(settings.getColNumOt()).getNumericCellValue());

	    	if(row.getCell(settings.getColSemana()).getCellType() == Cell.CELL_TYPE_NUMERIC)
	    		ot.setSemana((int)row.getCell(settings.getColSemana()).getNumericCellValue());
	    	if(row.getCell(settings.getColSemana()).getCellType() == Cell.CELL_TYPE_STRING)
	    		ot.setSemana(Integer.valueOf(row.getCell(settings.getColSemana()).getStringCellValue().substring(0, 4)));
	    	if(row.getCell(settings.getColComponente()).getCellType() == Cell.CELL_TYPE_STRING)
	    		ot.setComponente(row.getCell(settings.getColComponente()).getStringCellValue());
	    	if(row.getCell(settings.getColOrgMant()).getCellType() == Cell.CELL_TYPE_STRING)
	    		ot.setOrgMant(row.getCell(settings.getColOrgMant()).getStringCellValue());
	    	if(row.getCell(settings.getColObservaciones()).getCellType() == Cell.CELL_TYPE_STRING)
	    		ot.setObservaciones(row.getCell(settings.getColObservaciones()).getStringCellValue());
	    	if(row.getCell(settings.getColFInicio()).getCellType() == Cell.CELL_TYPE_NUMERIC)
	    		ot.setFechaInicio(row.getCell(settings.getColFInicio()).getDateCellValue());
	    	if(row.getCell(settings.getColFFin()).getCellType() == Cell.CELL_TYPE_NUMERIC)
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
