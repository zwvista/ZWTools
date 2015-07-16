package com.zwstudio.tools.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.zwstudio.tools.models.HWDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

@Service
public class Tool1Service {
	@Getter
	private ObservableList<HWDao> daos = FXCollections.observableArrayList();
	
	public void load() throws IOException {
		String path = "C:\\work\\svn\\doc\\branches\\PPD_2.1\\4000物理設計\\共通\\手動生成DAO一覧.xlsx";
		
		FileInputStream file = new FileInputStream(new File(path));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("手動生成DAO一覧");
		for(int k = 9; k < sheet.getPhysicalNumberOfRows(); k++) {
			XSSFRow row = sheet.getRow(k);
			String num = String.valueOf(row.getCell(0, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
			String module = row.getCell(10, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
			daos.add(new HWDao() {{
				setNum(num);
				setModule(module);
//				setMessage(message);
			}});
		}
	}
}
