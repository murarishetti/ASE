package com.assignment.lab;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;

import org.junit.Test;

public class TestCylinderVol {

	VolChecker cv = new VolChecker();
	Double vol = cv.CVol(3.0,3.0);
	Double defVol =  25.12;
	DecimalFormat f = new DecimalFormat("##.00");
	String volume = f.format(vol);
	Double ccm = Double.parseDouble(volume);

	
	@Test
	public void testheight() {
		System.out.println("@Test Volume(): " + defVol + " = " + ccm);
		assertEquals(defVol, ccm);
	}


}
