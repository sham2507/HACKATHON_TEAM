package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProvider {
	ExcelUtility exe;
	
	@DataProvider(name="bookingDetails")
	public String[][] bookingData() throws IOException{		
		exe = new ExcelUtility();
		String[][] data = exe.fetchData(".//testData/MMT_Test_Data.xlsx","bookingData");
		return data;
	}
	
	@DataProvider(name="giftDetails")
	public String[][] giftData() throws IOException{
		exe = new ExcelUtility();
		String[][] data = exe.fetchData(".//testData/MMT_Test_Data.xlsx","giftData");
		return data;
	}

}
