package com;

public class ProductFilter {

	//private static boolean filterInitalised = false;

	//private int filteredTemperature;
	private double filteredPrice;

	private int expiredCount = 0;
	private int underPriceCount = 0;
	private int applyCallCount = 0;

	private ProductFilter() {
		AppProperties p = AppProperties.getInstance();
		//filteredTemperature = p.getPropertyInt("filter.product.temperature.above");
		filteredPrice = p.getPropertyDouble("filter.product.price.above");
		//filterInitalised = true;
	}

	//private void initalised() {
	//	new ProductFilter();
	//}

	public static ProductFilter newInstance() {
		return new ProductFilter();
	}


	public boolean apply(Product product) {
		applyCallCount++;

		if (product.isExpired()) {
			expiredCount++;
			return false;
		}
		//there might not be any voting yet
//		if(product.getVote().getTemperture() < filteredTemperature) {
//			return false;
//		}
		if (product.getPrice().getValue().doubleValue() < filteredPrice) {
			underPriceCount++;
			return false;
		}
		return true;
	}

	public String getSummary() {
		return "Filter(s) summary: \n" +
				"Products count: " + applyCallCount + "\n" +
				"Expired count:" + expiredCount + "\n" +
				underPriceCount + " product(s) under the cost of £" + filteredPrice + "\n"
				;
	}
}
