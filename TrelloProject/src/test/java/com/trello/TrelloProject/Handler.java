package com.trello.TrelloProject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Handler {

	public static void main(String [] args) throws BiffException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		File f = new File("TrelloLogin.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet sh1 = wb.getSheet(0);
		int sh1nor = sh1.getRows();
		
		Sheet sh2 = wb.getSheet(1);
		int sh2nor = sh2.getRows();
		
		TrelloLoginTest tr = new TrelloLoginTest();
		Method m[] = tr.getClass().getMethods();
		for(int i = 0; i<sh1nor; i++){
			String tname = sh1.getCell(0, i).getContents();
			String mode = sh1.getCell(2, i).getContents();
			if(mode.equalsIgnoreCase("yes")){
				for(int j=1;j<sh2nor;j++){
					String tid = sh2.getCell(0, j).getContents();
					if(tname.equalsIgnoreCase(tid)){
						String taction = sh2.getCell(3, j).getContents();
						String objdesc = sh2.getCell(2, j).getContents();
						String testdata = sh2.getCell(4, j).getContents();
						for(int k=0;k<m.length;k++){
							if(m[k].getName().equals(taction)){
								m[k].invoke(tr, objdesc,testdata);
								break;
							}
						}
					}
				}
			}
		}
		wb.close();		
	}	
}