package raf.rs.projekat_test.resources;

import raf.rs.projekat_test.entities.Category;
import raf.rs.projekat_test.services.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(Category category) {
        return this.categoryService.addCategory(category);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.categoryService.allCategories()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category find(@PathParam("id") Integer id) {
        return this.categoryService.findCategory(id);
    }

    @GET
    @Path("/name/{categoryName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category findByName(@PathParam("categoryName") String name) {
        return this.categoryService.findCategoryByName(name);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category update(@PathParam("id") Integer id, Category category) {
        return this.categoryService.updateCategory(id, category);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id) {
        this.categoryService.deleteCategory(id);
    }
}
