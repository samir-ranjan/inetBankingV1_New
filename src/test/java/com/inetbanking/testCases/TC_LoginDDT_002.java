package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{

	@Test(dataProvider = "LoginData")
	public void LoginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickSubmit();
		Thread.sleep(3000);

		if(isAlertPresent())
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			System.out.println("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			System.out.println("Login Passed");
			lp.clickLogout();
			Thread.sleep(3000);

			driver.switchTo().alert().accept();//logout alert close
			driver.switchTo().defaultContent();
		}


	}

	public boolean isAlertPresent()
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}




	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"./Configuration/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][colCount];
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1",i,j);
			}
		}


		return logindata;
	}


}
