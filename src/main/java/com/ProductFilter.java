package com;

public class ProductFilter {

	private static boolean filterInitalised = false;
	private static int filteredTemperature;
	private static double filteredPrice;

	private ProductFilter() {
		AppProperties p = AppProperties.getInstance();
		filteredTemperature = p.getPropertyInt("filter.product.temperature.above");
		filteredPrice = p.getPropertyDouble("filter.product.price.above");
		filterInitalised = true;
	}

	private static void initalised() {
		new ProductFilter();
	}


	public static boolean apply(Product product) {
		if(!filterInitalised) {
			initalised();
		}

		if(product.getVote().getTemperture() < filteredTemperature) {
			return false;
		}
		if(product.getPrice().getValue().doubleValue() < filteredPrice) {
			return false;
		}
		return true;
	}
}
