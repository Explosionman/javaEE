package ru.rybinskov.rest;

import ru.rybinskov.dto.ProductDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void save(ProductDto product);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDto product);

    @DELETE
    @Path("/{id}")
    void deleteById(@PathParam("id") Long id);

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findByName(@QueryParam("name") String name);

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAllByCategoryId(@QueryParam("categoryId") Long categoryId);
}
