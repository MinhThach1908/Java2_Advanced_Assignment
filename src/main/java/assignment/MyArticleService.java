package assignment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class MyArticleService implements ArticleService {
    @Override
    public ArrayList<String> getLinks(String url) {
        HashSet<String> links = new HashSet<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByTag("a");
            for (Element element : elements) {
                String href = element.attr("href");
                if(href.contains("https://tienphong") && href.contains(".tpo")){
                    links.add(href);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(links);
    }

    @Override
    public Article getArticle(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            String title = doc.select("h1.article__title ").text();
            String description = doc.select("div.article__sapo").text();
            String content = doc.select("div.article__body p").text();
            String thumb = doc.select("div.article__body  img").attr("data-src");
            Article article = new Article();
            article.setBaseUrl(url);
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setThumbnail(thumb);
            return article;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
