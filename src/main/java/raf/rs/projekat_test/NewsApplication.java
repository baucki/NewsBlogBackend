package raf.rs.projekat_test;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import raf.rs.projekat_test.repositories.category.CategoryRepository;
import raf.rs.projekat_test.repositories.category.CategoryRepositoryImpl;
import raf.rs.projekat_test.repositories.comment.CommentRepository;
import raf.rs.projekat_test.repositories.comment.CommentRepositoryImpl;
import raf.rs.projekat_test.repositories.news.NewsRepository;
import raf.rs.projekat_test.repositories.news.NewsRepositoryImpl;
import raf.rs.projekat_test.repositories.tag.TagRepository;
import raf.rs.projekat_test.repositories.tag.TagRepositoryImpl;
import raf.rs.projekat_test.repositories.user.UserRepository;
import raf.rs.projekat_test.repositories.user.UserRepositoryImpl;
import raf.rs.projekat_test.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class NewsApplication extends ResourceConfig {

    public NewsApplication() {
        // turn on validation
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Defining implementation in dependency containers
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(UserRepositoryImpl.class).to(UserRepository.class).in(Singleton.class);
                this.bind(CategoryRepositoryImpl.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(NewsRepositoryImpl.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(TagRepositoryImpl.class).to(TagRepository.class).in(Singleton.class);
                this.bind(CommentRepositoryImpl.class).to(CommentRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(NewsService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(CommentService.class);

            }
        };
        register(binder);

        // Ucitavamo resurse
        packages("raf.rs.projekat_test");
    }

}