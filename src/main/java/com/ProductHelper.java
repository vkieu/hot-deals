package com;

import java.io.StringReader;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.apache.log4j.Logger;

public class ProductHelper {

	final static Logger LOG = Logger.getLogger(ProductHelper.class);

	public static Product parse(Element e) {

		String vote = e.getElementsByClass("vote-box").first().text();
		String desc = e.getElementsByClass("cept-description-container").first().text();
		String price = e.getElementsByClass("thread-price").text();
		String link = e.select("a.thread-link").first().attr("href");

		Product product = new Product();
		product.setLink(link);
		product.setPrice(price);
		product.setDescription(desc);
		product.setVote(vote);
		product.setExpired(vote);

		String imageLink = e.select("img.thread-image").first().attr("src");

		LOG.debug(e.select("img.thread-image"));
		LOG.debug(e.select("img.thread-image").attr("src"));
		if (StringUtils.isEmpty(imageLink)) {
			LOG.debug(e.select("img.thread-image").attr("data-lazy-img"));
			String imgLinkJson = e.select("img.thread-image").attr("data-lazy-img");
			LOG.debug("imgLinkJson? " + imgLinkJson);
			try (JsonReader reader = Json.createReader(new StringReader(imgLinkJson))) {
				JsonObject jsonObject = reader.readObject();
				imageLink = jsonObject.getString("src");
			}
		}
		product.setImageLink(imageLink);

		return product;
	}

	public static String productsToString(List<Product> products) {
		final StringBuilder sb = new StringBuilder();
		for (Product p : products) {
			sb.append(p).append("\n");
		}
		return sb.toString();
	}
}
