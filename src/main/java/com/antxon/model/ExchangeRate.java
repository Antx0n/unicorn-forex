package com.antxon.model;

public class ExchangeRate {

	public ExchangeRate(String currency, Float rate) {
		this.currency = currency;
		this.rate = rate;
	}
	//Base currency is always KES
	String currency;
	Float rate;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
}
