package raf.rs.projekat_test.services;

import raf.rs.projekat_test.entities.News;
import raf.rs.projekat_test.repositories.news.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class NewsService {

    @Inject
    private NewsRepository newsRepository;

    public News addNews(News news) {
        return newsRepository.addNews(news);
    }

    public List<News> allNews() {
        return newsRepository.allNews();
    }

    public News findNews(Integer id) {
        return newsRepository.findNews(id);
    }

    public void incViews(Integer id) {
        this.newsRepository.incViews(id);
    }

    public News updateNews(Integer id, News news) {
        return this.newsRepository.updateNews(id, news);
    }

    public void deleteNews(Integer id) {
        newsRepository.deleteNews(id);
    }

}
