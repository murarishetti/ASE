package com.assignment.lab;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/perimeter")
public class Perimeter {
	@GET
	@Produces("application/xml")
	public String Cperimeter(){
		 
		Double rad = 0.0;
		Double p;
		
		p = 2*(3.14*rad);
		
		String result="Output: Perimeter of Cylinder in Cms\n\n" + p;
		return "<cylinder>" + "<rad>" + rad + "</rad>" + "<perimeter>" + result + "</perimeter>" + "</cylinder>";
		
	}
		@Path("{r}")
		@GET
		@Produces("application/xml")
		
		public String WeightConvert(@PathParam("r") Double r) {
			Double rad = r;
			Double p;
			
			p = 2*(3.14*rad);
			
			String result="Output: Perimeter of Cylinder in Cms\n\n" + p;
			return "<cylinder>" + "<rad>" + rad + "</rad>" + "<perimeter>" + result + "</perimeter>" + "</cylinder>";
		}
}

