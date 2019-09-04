package com.DriverScript;

import java.io.IOException;

import org.testng.annotations.Test;

public class AppTest
{
	@Test
	public void startTest1()
	{
		TestScript ts = new TestScript();
		try {
			ts.startTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
