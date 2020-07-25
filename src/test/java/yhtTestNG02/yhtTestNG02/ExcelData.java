package yhtTestNG02.yhtTestNG02;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ExcelData {
	@Test
	public static  List<Map<String, String>> readExcelData(String exclePath) {
		//源表头
        List<String> title = new ArrayList<String>();
        //源表数据
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        //源文件中缺少字段信息的无效数据信息记录
        List<String> errorList = new ArrayList<String>();
      Map<String, Object> mapR = new HashMap<String, Object>();
      HSSFWorkbook inStream = null;
      HSSFWorkbook workBook = null;
      HSSFSheet sheet = null;
      int rowNum = 0;
      try {
    	  //获取表格
    	  HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(exclePath));
    	  //2.获取要解析的表格（第一个表格）
//        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
//        //获得最后一行的行号
          int lastRowNum = sheet.getLastRowNum();
          for (int i = 0; i < rowNum; i++) {
//                  if(!lastRowNum && controlParseNum != -1){
        	  //3.获得要解析的行
            HSSFRow row = sheet.getRow(i);
//            HSSFCell cell = row.getCell();
                      if (i == 0) {
                          for (int j = 0; j < row.getLastCellNum(); j++) {
                              // 读取第一行 存入标题
                        	  HSSFCell cell = row.getCell(j);
                              // 获取单元格的值
                              String str = getCellValue(cell);
                              title.add(str);
                          }
                      } else {
                          Map<String, String> beanRow=new HashMap<String, String>();
                          for(int k = 0; k <title.size();k++){
                              // 读取数据行
                        	  HSSFCell cell = row.getCell(k);
                              // 获取单元格的值
                              String str = getCellValue(cell);
                              beanRow.put(title.get(k), "".equals(str) ? " " : str);
                          }
                          result.add(beanRow);
                      }
//                      if(i  == controlParseNum){
//                          terminateParseFlag = true;
//                          /* throw new IOException();*/
//                          break;
//                      }

//                  }
              }
      } catch (Exception e) {
//          LOGGER.error("readExcelWithTitle() error",e);
      } finally {
          if (inStream != null) {
              try {
                  inStream.close();
              } catch (IOException e) {
//                  LOGGER.error("FileInputStream close() error",e);
              }
          }
//      }
//      mapR.put("title", title);
//      mapR.put("result", result);
//      mapR.put("total", rowNum);
//      mapR.put("errorList", errorList);
      System.out.println("hahahahahh"+result);
      return result;
     }
  
//		//获取文件名后缀判断文件类型
//        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1,
//                fileName.length());
//        //根据文件类型及文件输入流新建工作簿对象
//        HSSFWorkbook wb = null;
////        List<T> resultlist = new ArrayList<T>();
////        if (fileType.equals("xls")) {
////            wb = new HSSFWorkbook(inputStream);
////        } else if (fileType.equals("xlsx")) {
////            wb = new XSSFWorkbook(inputStream);
////        } else {
////            logger.error("您上传的excel格式不正确");
////            throw new Exception("您上传的excel格式不正确");
////        }
//		//1.读取Excel文档对象
//        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream("/Users/juandu/Documents/code/excelData.xls"));
//        //2.获取要解析的表格（第一个表格）
//        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
//        //获得最后一行的行号
//        int lastRowNum = sheet.getLastRowNum();
//        for (int i = 0; i <= lastRowNum; i++) {//遍历每一行
//            //3.获得要解析的行
//            HSSFRow row = sheet.getRow(i);
////            HSSFCell cell = row.getCell;
////            cell.setCellType(CellType.STRING);
//            //4.获得每个单元格中的内容（String）
//            String stringCellValue0 = row.getCell(0).getStringCellValue();
//            String stringCellValue1 = row.getCell(1).getStringCellValue();
////            String stringCellValue1 = cell.getStringCellValue();
////            HSSFCell cell = row.getCell(2);
////            cell.setCellType(CellType.STRING);
////            String stringCellValue2 = cell.getStringCellValue(); 
//            System.out.println(stringCellValue0+"--"+stringCellValue1+"--"+stringCellValue1+"--");
//           
//
//        }
//		return null;
	}

	private static String getCellValue(HSSFCell cell) {
		// TODO Auto-generated method stub
		return null;
	}
  
}


