package com;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.log4j.Logger;

public class HotDealsApp {

	//private static String SITE = "https://www.hotukdeals.com/search/idealo?action=idealo&offset=0&keywords=iphone";
	private static String SITE = "https://www.hotukdeals.com/search?q=gopro";
	final static Logger LOG = Logger.getLogger(HotDealsApp.class);

	public static void main(String[] args) throws Exception {
		AppProperties p = AppProperties.getInstance();
		LOG.info("Welcome to Hot deals");

		List<Product> products = getProducts(SITE);

		StringBuilder sb = new StringBuilder(products.size() + " products matched:\n");
		sb.append(ProductHelper.productsToString(products));

		LOG.debug(sb.toString());

		if (p.getPropertyBoolean("send.email")) {
			//send email
			SendHTMLEmail.sendHTML(sb.toString());
			LOG.info("Email sent");
		} else {
			LOG.info("Email not sent");
		}
	}

	private static void writeToFile(String file, String content) throws Exception {
		try (FileWriter writer = new FileWriter(file, false)) {
			writer.write(content);
		}
	}

	private static List<Product> getProducts(String site) throws Exception {

		List<Product> returnList = new ArrayList<>();
		Document doc = Jsoup.connect(site).ignoreContentType(true).get();

		LOG.info(doc.title());
		final String html = doc.html();
		writeToFile("/media/sf_Downloads/shared/test.html", html);

		Elements items = doc.select("article.thread--deal");

		LOG.info("Found " + items.size() + " raw result(s)");
		ProductFilter filter = ProductFilter.newInstance();

		for (Element item : items) {
			Element hot = item.getElementsByClass("thread--type-list").first();
			if (hot != null) {
				Product product = ProductHelper.parse(item);
				if (filter.apply(product)) {
					returnList.add(product);
				}
			}
		}
		LOG.debug("Applied filter, found " + returnList.size() + " result(s)");
		LOG.info(filter.getSummary());
		return returnList;
	}

}
