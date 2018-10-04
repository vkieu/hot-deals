package com;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class Price {
	private static String GB_FORMAT = "£#.##";

	private String rawString;
	private BigDecimal value;

	public Price(String rawString) {
		this.rawString =  rawString;
		if(!StringUtils.isEmpty(rawString)) {
			String newStr = rawString.replaceAll("[^\\d.]+", "");
			//System.out.println(this.rawString);
			this.value = new BigDecimal(newStr);
		} else {
			this.value = BigDecimal.ZERO;
		}
	}

	public BigDecimal getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "Price{" +
				"value=" + rawString +
				'}';
	}
}
