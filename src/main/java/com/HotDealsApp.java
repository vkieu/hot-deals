package com;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HotDealsApp {

	//private static String SITE = "https://www.hotukdeals.com/search/idealo?action=idealo&offset=0&keywords=iphone";
	private static String SITE = "https://www.hotukdeals.com/search?q=gopro";

	public static void main(String[] args) throws Exception {

		System.out.println("Welcome to Hot deals");

		List<Product> products = getProducts(SITE);

		StringBuilder sb = new StringBuilder(products.size() + " products matched:\n");
		for (Product p : products) {
			sb.append(p).append("\n");
		}
		//send email
		SendHTMLEmail.sendHTML(sb.toString());
	}

	private static void writeToFile(String file, String content) throws Exception {
		try (FileWriter writer = new FileWriter(file, false)) {
			writer.write(content);
		}
	}

	private static List<Product> getProducts(String site) throws Exception {

		List<Product> returnList = new ArrayList<>();
		Document doc = Jsoup.connect(site).ignoreContentType(true).get();

		System.out.println(doc.title());
		final String html = doc.html();
		writeToFile("/media/sf_Downloads/shared/test.html", html);

		Elements items = doc.select("article.thread--deal");

		System.out.println("Found " + items.size() + " raw  result(s)");

		for (Element item : items) {
			Element hot = item.getElementsByClass("thread--type-list").first();
			if (hot != null) {
				Product product = ProductHelper.parse(item);
				if (ProductFilter.apply(product)) {
					returnList.add(product);
				}
			}
		}
		System.out.println("Applied filter, found " + returnList.size() + " result(s)");
		return returnList;
	}

}
