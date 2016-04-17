package com.assignment.lab;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
@Path("/volume")
public class Volume {
	@GET
	@Produces("application/xml")
	public String Cvolume(){
		 
		Double radius = 0.0;
		Double height = 0.0;
		Double vol;
		
		vol = (3.14*((radius*radius)*height));
		
		String result="Output: Volume of Cylinder in Cubic Cm \n\n" + vol;
		return "<cylinder>" + "<radius>" + radius + "</radius>" + "<height>" + height + "</height>" + "<volumeoutput>" + result + "</volumeoutput>" + "</cylinder>";
		
	}
		@Path("{r}/{h}")
		@GET
		@Produces("application/xml")
		
		public String HeightConvert(@PathParam("r") Double r,@PathParam("h") Double h) {
			Double rad = r;
			Double height = h;
			Double vol;
			
			vol = (3.14*((rad*rad)*height));
	 			
			String result="Output: VOlume of Cylinder in Cubic Cm \n\n" + vol;
			return "<cylinder>" + "<radius>" + rad + "</radius>" + "<height>" + height + "</height>" + "<volumeoutput>" + result + "</volumeoutput>" + "</cylinder>";
				
		}

}

