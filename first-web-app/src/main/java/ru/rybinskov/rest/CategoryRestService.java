package ru.rybinskov.rest;

import ru.rybinskov.dto.CategoryDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/categories")
public interface CategoryRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto findById(@PathParam("id") Long id);


    @GET
    @Path("/count")
    Long countAll();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryDto categoryDto);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void save(CategoryDto categoryDto);

    @DELETE
    void deleteById(Long id);
}
