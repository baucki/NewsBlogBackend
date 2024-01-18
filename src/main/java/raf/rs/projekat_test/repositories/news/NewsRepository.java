package raf.rs.projekat_test.repositories.news;

import raf.rs.projekat_test.entities.News;

import java.util.List;

public interface NewsRepository {

    public News addNews(News news);

    public List<News> allNews();

    public News findNews(Integer id);

    public void incViews(Integer id);

    public News updateNews(Integer id, News news);

    public void deleteNews(Integer id);


}
