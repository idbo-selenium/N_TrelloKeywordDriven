package com.trello.TrelloProject;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.io.File;

public class JXLUpdateExcelSheet {
	
	public void writeXLSFile(){
		System.out.println("Write XLSFile");
		try{
			WritableWorkbook workbook = Workbook.createWorkbook(new File("Data.xls"));
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, true, UnderlineStyle.DOUBLE);
			WritableCellFormat headerCells = new WritableCellFormat(headerFont);
			headerCells.setBackground(Colour.ROSE);
			int column = 0;
			for (int row=0;row <5;row++){
				Label label = new Label(row,column,"Headers",headerCells);
				label.setCellFormat(headerCells);
				sheet.addCell(label);
			}
			column ++;
			WritableFont normalFont = new WritableFont(WritableFont.TIMES, 10);
			WritableCellFormat normalCell = new WritableCellFormat(normalFont);
			for (int col=column;col <5;col++){
				for (int row=0;row <5;row++){
					Label label = new Label(row,col,"cells "+row+" "+col,normalCell);
					label.setCellFormat(normalCell);
					sheet.addCell(label);
				}
			}
			workbook.write();
			workbook.close();
			System.out.println("Data.xls created sucessfully");
		}
		catch(Exception ee)
		{
			System.out.println("Exception :: "+ee);
		}
	}
	
	public void readXLSFile(){
		System.out.println("Read XLSFile");
		try{
			Workbook workbook = Workbook.getWorkbook(new File("Data.xls"));
			Sheet sheet = workbook.getSheet(0);
			for(int column = 0; column < sheet.getColumns(); column++){
				for(int row = 0; row < sheet.getRows(); row++){
					Cell cell = sheet.getCell(row,column);
					System.out.print(cell.getContents());
				}
				System.out.println();
			}
		}
		catch(Exception ee)
		{
			System.out.println("Exception :: "+ee);
		}
	}
	
	public static void main(String [] args){
		JXLUpdateExcelSheet obj = new JXLUpdateExcelSheet();
		obj.writeXLSFile();
		obj.readXLSFile();
	}
}