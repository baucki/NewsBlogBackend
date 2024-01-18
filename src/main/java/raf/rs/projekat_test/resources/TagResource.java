package raf.rs.projekat_test.resources;

import raf.rs.projekat_test.entities.Tag;
import raf.rs.projekat_test.services.TagService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tags")
public class TagResource {

    @Inject
    private TagService tagService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag create(Tag tag) {
        return this.tagService.addTag(tag);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTags() {
        return Response.ok(this.tagService.allTags()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag find(@PathParam("id") Integer id) {
        return this.tagService.findTag(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id) {
        this.tagService.deleteTag(id);
    }


}
