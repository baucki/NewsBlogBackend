package raf.rs.projekat_test.resources;

import raf.rs.projekat_test.entities.News;
import raf.rs.projekat_test.entities.User;
import raf.rs.projekat_test.services.NewsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News create(News news) {
        return  this.newsService.addNews(news);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.newsService.allNews()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News find(@PathParam("id") Integer id) {
        return newsService.findNews(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public News update(@PathParam("id") Integer id, News news) {
        return newsService.updateNews(id, news);
    }

    @PUT
    @Path("/views/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateViews(@PathParam("id") Integer id) {
        this.newsService.incViews(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id) {
        newsService.deleteNews(id);
    }

}
