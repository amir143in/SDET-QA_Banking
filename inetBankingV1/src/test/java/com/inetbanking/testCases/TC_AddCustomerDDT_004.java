package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_AddCustomerDDT_004 extends BaseClass {
	public static LoginPage lp;
	public static AddCustomerPage addCust;

	//Will check the issues
	@Test(dataProvider = "AddCustomerData")
	public void addNewCustomerDDT(String CustLoginUname, String CustLoginPwd, String custName, String custGender,
			String CustDOB, String CustAddress, String CustCity, String CustState, String CustPin, String CustTelephone,
			String CustEmail, String CustPassword) throws InterruptedException, IOException {
		lp = new LoginPage(driver);

		logger.info("Login started");
		lp.setUserName(CustLoginUname);
		lp.setPassword(CustLoginPwd);
		lp.clickSubmit();

		Thread.sleep(3000);
		logger.info("Enter customer details");

		try {
			addCust = new AddCustomerPage(driver);
			addCust.clickAddNewCustomer();
			addCust.custName(custName);
			addCust.gender(custGender);
			addCust.custdob(CustDOB);
			addCust.custaddress(CustAddress);
			addCust.custcity(CustCity);
			addCust.custstate(CustState);
			addCust.custpinno(CustPin);
			addCust.custtelephoneno(CustTelephone);
			addCust.custemailid(CustEmail);
			addCust.custpassword(CustPassword);
			addCust.custsubmit();
			Thread.sleep(5000);

			logger.info("validation started....");

			boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

			if (res) {
				Assert.assertTrue(true);
				logger.info("test case passed....");
			} else {
				logger.info("test case failed....");
				captureScreen(driver, "addNewCustomerDDT");
				Assert.assertTrue(false);
			}
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Something went wrong!!!!");
		}

	}

	@DataProvider(name = "AddCustomerData")
	String[][] getData() throws IOException {
		// String path = System.getProperty("user.dir") +
		// "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		String path = "C:/Users/AAnsari/git/inetBankingV1/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		 // String path = logindataPath;
		int rownum = XLUtils.getRowCount(path, "Sheet2");
		int colcount = XLUtils.getCellCount(path, "Sheet2", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet2", i, j);// 1 0
			}

		}
		return logindata;
	}
}
