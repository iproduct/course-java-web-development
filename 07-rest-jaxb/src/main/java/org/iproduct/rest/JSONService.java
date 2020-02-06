package org.iproduct.rest;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.iproduct.rest.exception.NonExistngEntityException;

@Path("/products")
public class JSONService {

	@GET
	@Produces("application/json")
	public Collection<Product> getProductInJSON() {
		return ProductController.getInstance().findAll();
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Product getProductById(@PathParam("id") long id) {
		try {
			return ProductController.getInstance().findById(id);
		} catch(NonExistngEntityException ex) {
			throw new WebApplicationException(Response.status(Status.NOT_FOUND).entity("{\"error\": \"" + ex.getMessage() + "\"}").build());		
		}
		
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createProductInJSON(Product product) {

		product = ProductController.getInstance().addProduct(product);
		String result = "Product created : " + product;
		System.out.println(result);
		return Response.status(201).header("Location", "/api/products/" + product.getId()).entity(product).build();
		
	}
	
	
}