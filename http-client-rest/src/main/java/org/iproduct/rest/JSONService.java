package org.iproduct.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/products")
public class JSONService {

	
	

	@GET
	@Produces("application/json")
	public List<Product> getProductInJSON() {
		return ProductController.getInstance().findAll();
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createProductInJSON(Product product) {

		ProductController.getInstance().addProduct(product);
		String result = "Product created : " + product;
		System.out.println(result);
		return Response.status(201).entity(product).build();
		
	}
	
	
}