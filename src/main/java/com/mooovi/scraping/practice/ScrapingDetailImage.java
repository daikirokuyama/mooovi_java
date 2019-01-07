package com.mooovi.scraping.practice;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapingDetailImage {

    public static void main(String[] args){
        try {
            scraping();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scraping() throws IOException {
    		Document document = Jsoup.connect("http://review-movie.herokuapp.com/products/342").get();
    		Elements elements = document.select(".entry-content img");
    		
    		for (Element element : elements) {
    			System.out.println(element.attr("src"));
    		}
    }

}
