package assignment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
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
                if(href.contains("https://thanhnien.vn/") && href.contains(".html")){
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
            String title = doc.select("h1.title-detail").text();
            String description = doc.select("p.description").text();
            String thumb = doc.select("picture img[itemprop=contentUrl]").attr("data-src");
            String content = doc.select("article.fck_detail p.Normal[style=text-align:right;]").text();
            Article article = new Article();
            article.setTitle(title);
            article.setDescription(description);
            article.setThumbnail(thumb);
            article.setContent(content);
            return article;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
