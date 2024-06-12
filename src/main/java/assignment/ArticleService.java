package assignment;

import java.util.ArrayList;

public interface ArticleService {
    ArrayList<String> getLinks(String url); // Get link list from a source
    Article getArticle(String url); // Get detailed information from post link
}
