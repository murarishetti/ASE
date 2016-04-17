package com.assignment.lab;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;

import org.junit.Test;

public class TestCylinderPer {

	
	PerimeterChecker pc = new PerimeterChecker();
	Double ps = pc.CPer(3.0);
	Double defVol= 12.56;
	DecimalFormat f = new DecimalFormat("##.00");
	String tper = f.format(ps);
	Double cps = Double.parseDouble(tper);

	
	@Test
	public void testweight() {
		System.out.println("@perimter(): " + tper + " = " + cps);
		assertEquals(defVol,cps);
	}


	
}
