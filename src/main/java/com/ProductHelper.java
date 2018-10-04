package com;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

public class ProductHelper {

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

		String imageLink = e.select("img.thread-image").first().attr("src");

		//System.out.println(e.select("img.thread-image"));
		//System.out.println(e.select("img.thread-image").attr("src"));
		if (StringUtils.isEmpty(imageLink)) {
			//System.out.println(e.select("img.thread-image").attr("data-lazy-img"));
			String imgLinkJson = e.select("img.thread-image").attr("data-lazy-img");
			//System.out.println("imgLinkJson? " + imgLinkJson);
			try (JsonReader reader = Json.createReader(new StringReader(imgLinkJson))) {
				JsonObject jsonObject = reader.readObject();
				imageLink = jsonObject.getString("src");
			}
		}
		product.setImageLink(imageLink);

		return product;
	}
}
