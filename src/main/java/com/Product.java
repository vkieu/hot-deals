package com;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Product {

	private String link;
	private String description;
	private Price price;
	private Vote vote;
	private String imageLink;



	public Product(){}

	public int getId() {
		return Math.abs(hashCode());
	}

	public Vote getVote() {
		return vote;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public void setVote(String vote) {
		this.vote = new Vote(vote);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = new Price(price);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(getDescription(), product.getDescription())
				.append(getPrice(), product.getPrice())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(getDescription())
				.append(getPrice())
				.toHashCode();
	}

	@Override
	public String toString() {
		return "Product {" +
				" \ninternalId=" + getId() +
				",\ndescription='" + description + '\'' +
				",\nprice='" + price + '\'' +
				",\nvote='" + vote + '\'' +
				",\nlink='" + link + '\'' +
				",\n\n<img src=\"" + imageLink  + "\"/>" +
				"}\n\n";
	}
}