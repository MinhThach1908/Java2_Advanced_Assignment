package assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController {

    Scanner scanner = new Scanner(System.in);

    public void vnexpressCrawler(){
        ArticleService vnexpressArticleService = new VnexpressArticleService();
        ArrayList<String> vnexpressLinks = vnexpressArticleService.getLinks("https://vnexpress.net/the-thao");
        for (int i = 0; i < vnexpressLinks.size(); i++) {
            Article article = vnexpressArticleService.getArticle(vnexpressLinks.get(i));
            System.out.printf("%d - %s\n", i + 1, article.getTitle());
        }
        System.out.println("---------------------------");
        System.out.println("Enter to continue");
        scanner.nextLine();
    }

    public void myCrawler(){
        ArticleService myArticleService = new MyArticleService();
        ArrayList<String> myLinks = myArticleService.getLinks("https://thanhnien.vn/du-lich.htm");
        for (int i = 0; i < myLinks.size(); i++) {
            Article article = myArticleService.getArticle(myLinks.get(i));
            System.out.printf("%d - %s\n", i + 1, article.getTitle());
        }
        System.out.println("---------------------------");
        System.out.println("Enter to continue");
        scanner.nextLine();
    }
}
