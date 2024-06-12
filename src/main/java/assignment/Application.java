package assignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        generateMenu();
    }

    public static void generateMenu() {
        Scanner scanner = new Scanner(System.in);
        ArticleController articleController = new ArticleController();
        while(true) {
            System.out.println("Information Crawler Program");
            System.out.println("================================");
            System.out.println("1. Vnexpress Article Crawler");
            System.out.println("2. My Article Crawler");
            System.out.println("3. Show information list");
            System.out.println("4. Exit");
            System.out.println("================================");
            System.out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    articleController.vnexpressCrawler();
                    break;
                case 2:
                    articleController.myCrawler();
                    break;
                case 3:
                    System.out.println("In development");
                    break;
                case 4:
                    System.out.println("Exit Application");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            if (choice == 4) {
                break;
            }
        }
    }
}
