package yhtTestNG02.yhtTestNG02;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取excel，返回map对象list集合
 *
 * @author longrong.lang
 */
public class ReadExcelUtil {


	/**
	 * 读取excel操作
	 *
	 * @param filePath
	 * @return:读取excel，返回map对象集合
	 */
	public static List<Map<String, String>> getExcuteList(String filePath) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		//源表头
		List<String> title = new ArrayList<String>();
		List<Map<String, String>> list = null;
		String cellData = null;
		//        String columns[] = {"name", "method", "value","备注"};
		String columns[] = null;
		wb = readExcel(filePath);
		if (wb != null) {
			//用来存放表中数据
			list = new ArrayList<Map<String, String>>();
			//获取第一个sheet
			sheet = wb.getSheetAt(0);
			//获取最大行数
			int rownum = sheet.getPhysicalNumberOfRows();
			//获取第一行
			row = sheet.getRow(0);
			int colnum = row.getPhysicalNumberOfCells();
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i < lastRowNum; i++) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				row = sheet.getRow(i);
				if (i == 0) {
					for (int j = 0; j < row.getLastCellNum(); j++) {
						// 读取第一行 存入标题
						Cell cell = row.getCell(j);
						// 获取单元格的值
						String str = getCellValue(cell);
						title.add(str);
					}
				}else {
						for(int k = 0; k <title.size();k++){
//							// 读取数据行
//							Cell cell = row.getCell(k);
//							// 获取单元格的值
//							String str = getCellValue(cell);
							 cellData = (String) getCellFormatValue(row.getCell(k));
							map.put(title.get(k),cellData);
						}
					}
              
					list.add(map);
				}
			}
			return list;
		}


		private static String getCellValue(Cell cell) {
			// TODO Auto-generated method stub
			return null;
		}


		/**
		 * 判断excel文件的类型
		 *
		 * @param filePath
		 * @return
		 */
		public static Workbook readExcel(String filePath) {
			Workbook wb = null;
			if (filePath == null) {
				return null;
			}
			String extString = filePath.substring(filePath.lastIndexOf("."));
			InputStream is = null;
			try {
				is = new FileInputStream(filePath);
				if (".xls".equals(extString)) {
					return wb = new HSSFWorkbook(is);
				} else if (".xlsx".equals(extString)) {
					return wb = new XSSFWorkbook(is);
				} else {
					return wb = null;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return wb;
		}

		public static Object getCellFormatValue(Cell cell) {
			Object cellValue = null;
			if (cell != null) {
				//判断cell类型
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC: {
					cellValue = String.valueOf(cell.getNumericCellValue());
					break;
				}
				case Cell.CELL_TYPE_FORMULA: {
					//判断cell是否为日期格式
					if (DateUtil.isCellDateFormatted(cell)) {
						//转换为日期格式YYYY-mm-dd
						cellValue = cell.getDateCellValue();
					} else {
						//数字
						cellValue = String.valueOf(cell.getNumericCellValue());
					}
					break;
				}
				case Cell.CELL_TYPE_STRING: {
					cellValue = cell.getRichStringCellValue().getString();
					break;
				}
				default:
					cellValue = "";
				}
			} else {
				cellValue = "";
			}
			return cellValue;
		}

	}