package com.trello.TrelloProject;

import java.io.File;
import java.lang.reflect.Method;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelHandle {
	
	public static void main(String[] args) throws Exception{
		
		File file = new File("TrelloLogin.xls");
		Workbook workbook=Workbook.getWorkbook(file);
		//Testcases sheet getting
		//Sheet testCasesSheet = workbook.getSheet(0);	
		Sheet testCasesSheet = workbook.getSheet("Test_cases");
		
		//Getting Testcases sheet number of rows
		int testCasesSheet_gettingRows = testCasesSheet.getRows();
		System.out.println("row "+testCasesSheet_gettingRows);
		
		//Login_Test Sheet getting
		Sheet login_testSheet = workbook.getSheet("Trello_login");
		
		//Getting Login_Test sheet number of rows
		int login_testSheet_gettingRows = login_testSheet.getRows();
		System.out.println("rows "+login_testSheet_gettingRows);
		
		//instanstaite TrellologinTest class
		TrelloLoginTest trelloTest = new TrelloLoginTest();
		
		//getting TrelloLoginTest class methods
		Method trelloTest_methods[] = trelloTest.getClass().getMethods();		
		
		for(int i =1; i<testCasesSheet_gettingRows;i++){
			//TestID column from TestCases Sheet
			String test_name = testCasesSheet.getCell(0, i).getContents();
			//Type column from TestCases sheet 
			String test_type = testCasesSheet.getCell(2, i).getContents();
			//checking condition for test_type yes or no
			if(test_type.equalsIgnoreCase("yes")){
				for(int j=1;j<login_testSheet_gettingRows;j++){
					String test_id = login_testSheet.getCell(0, j).getContents();
					//checking for testcases sheet TestID column and login_test sheet testID column
					if(test_name.equalsIgnoreCase(test_id)){
						//getting test action from login_test sheet
						String test_action = login_testSheet.getCell(3, j).getContents();
						//getting object value from login_test sheet
						String objectValue = login_testSheet.getCell(2, j).getContents();
						//getting object data from login_test sheet
						String objectData = login_testSheet.getCell(4, j).getContents();
						for(int k=0;k<trelloTest_methods.length;k++){
							//condition for test action in login_test sheet and in trellologintest class 
							if(trelloTest_methods[k].getName().equals(test_action)){
								//invoke object value and object data to trellologintest class
								trelloTest_methods[k].invoke(trelloTest, objectValue,objectData);
								break;
							}
						}
					}
				}  
			}	        		
		}          
		workbook.close();
	}	
}