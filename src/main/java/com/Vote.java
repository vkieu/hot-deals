package com;

public class Vote {

	private String rawString;
	private int temperture;

	public Vote(String rawString) {
		this.rawString =  rawString;
		temperture = Integer.parseInt(this.rawString.substring(0, this.rawString.indexOf("°")));
	}

	public String getRawString() {
		return rawString;
	}

	public int getTemperture() {
		return temperture;
	}

	@Override
	public String toString() {
		return "Vote{" +
				"temperture=" + rawString +
				'}';
	}
}
