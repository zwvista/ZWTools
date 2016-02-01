package com.zwstudio.tools.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.apache.poi.hssf.util.CellReference;
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
		String path = "C:\\work\\zw.xlsx";
		
		FileInputStream file = new FileInputStream(new File(path));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("SQL");
		daos.clear();
		for(int k = 1; k < sheet.getPhysicalNumberOfRows(); k++) {
			XSSFRow row = sheet.getRow(k);
			String num = String.valueOf((int)row.getCell(0, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
			UnaryOperator<String> op = letter -> row.getCell(CellReference.convertColStringToIndex(letter), Row.CREATE_NULL_AS_BLANK).getStringCellValue();
			String sql = op.apply("B");
			String sqlString = Arrays.stream(sql.split("\n"))
					.filter(s -> !s.trim().isEmpty())
					.map(s -> "sb.append(\" " + s + "\");\n")
					.collect(Collectors.joining());
			String daoClassName = op.apply("C");
			String daoMethodName = op.apply("D");
			daos.add(new HWDao() {{
				setNum(num);
				setSql(sql);
				setSqlString(sqlString);
				setDaoClassName(daoClassName);
				setDaoMethodName(daoMethodName);
			}});
		}
	}
}
