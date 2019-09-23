package com.zzrenfeng.zhsx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 解析Excel
 * 
 * @author 田杰熠
 * @version 1.0
 */
public class ExcelUtil {

	public static String formatCell(Cell cell) {
		if (cell == null) {
			return "";
		} else {
			if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				DecimalFormat df = new DecimalFormat("#");
				return String.valueOf(df.format(cell.getNumericCellValue()));
			} else {
				return String.valueOf(cell.getStringCellValue());
			}
		}
	}

	public static Map<String, Object> getDataFromExcel(String filePath) {

		Map<String, Object> hm = new HashMap<String, Object>();
		File file = new File(filePath);
		InputStream is = null;
		Workbook workbook = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 得到工作簿
		try {
			// .xlsx格式
			workbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			try {
				// .xls格式
				workbook = new HSSFWorkbook(is);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		// 得到一个工作表
		if (hm != null) {
			Sheet sheet = workbook.getSheetAt(0);
			// 获得表头
			Row rowHead = sheet.getRow(0);
			// 获得数据的总行数
			int totalRowNum = sheet.getLastRowNum();
			hm.put("sheet", sheet);
			hm.put("rowHead", rowHead);
			hm.put("totalRowNum", totalRowNum);
		}
		return hm;
	}

}
