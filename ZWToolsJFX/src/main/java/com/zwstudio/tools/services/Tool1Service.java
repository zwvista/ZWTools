package com.zwstudio.tools.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.UnaryOperator;

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
		String path = "C:\\work\\svn\\doc\\branches\\PPD_2.1\\4000物理設計\\共通\\手動生成DAO一覧.xlsx";
		
		FileInputStream file = new FileInputStream(new File(path));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("手動生成DAO一覧");
		daos.clear();
		for(int k = 9; k < sheet.getPhysicalNumberOfRows(); k++) {
			XSSFRow row = sheet.getRow(k);
			String num = String.valueOf((int)row.getCell(0, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
			UnaryOperator<String> op = letter -> row.getCell(CellReference.convertColStringToIndex(letter), Row.CREATE_NULL_AS_BLANK).getStringCellValue();
			String module = op.apply("H");
			String classLName = op.apply("I");
			String classPName = op.apply("J");
			String methodLName = op.apply("K");
			String methodPName = op.apply("L");
			String daoClass = op.apply("O");
			String sqlR = op.apply("R");
			String sql = sqlR.equals("") ? op.apply("Q") : sqlR;
			String sqlString = Arrays.stream(sql.split("\n"))
					.filter(s -> true)
					.map(s -> "sql.append(\"" + s + "\");")
					.reduce((acc, s) -> acc + "\n" + s).get();
			daos.add(new HWDao() {{
				setNum(num);
				setModule(module);
				setClassLName(classLName);
				setClassPName(classPName);
				setMethodLName(methodLName);
				setMethodPName(methodPName);
				setDaoClass(daoClass);
				setSql(sql);
				setSqlString(sqlString);
			}});
		}
	}
}
