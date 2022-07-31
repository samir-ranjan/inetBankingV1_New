package com.inetbanking.testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	@Test
	public void addNewCustomer() throws InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();

		Thread.sleep(3000);

		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.ClickAddNewCustomer();
		addcust.customerName("samir");
		addcust.custgender("male");
		addcust.custdob("12", "01", "2020");

		Thread.sleep(3000);

		addcust.custaddress("bhubaneswar");
		addcust.custcity("pipili");
		addcust.custstate("odisha");
		addcust.custpinno("752101");
		addcust.custtelephoneno("13245626");


		String email = randomestring()+"@gmail.com";
		addcust.custemailid(email);

		addcust.custpassword("java@selenium");
		addcust.custsubmit();

		Thread.sleep(3000);

		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if(res)
		{
			Assert.assertTrue(true);
			System.out.println("passed");

		}
		else
		{
			System.out.println("failed");
			Assert.assertTrue(false);
		}




	}


	public String randomestring()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);

	}

	public static String randomeNum()
	{
		String generatedstring2 = RandomStringUtils.randomNumeric(4);
		return(generatedstring2);
	}



}
