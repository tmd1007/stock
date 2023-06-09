package zerobase.stock;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zerobase.stock.model.Company;
import zerobase.stock.scraper.NaverFinanceScraper;
import zerobase.stock.scraper.Scraper;
import zerobase.stock.scraper.YahooFinanceScraper;

import java.io.IOException;

@SpringBootApplication
public class StockApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);

	}

}
