package com.keydividend.entity;

import java.util.Date;

/**
 * 
 * @author rupau
 *
 */
public class Holding {
	
	private String userId;
	private String holdingId;
	private String portfolioId;
	private String portfolioName;
	private String tickerSymbol;
	private String countryCode;
	private Double stockVolume;
	private Date purchaseDate;
	private Double purchasedStockPrice;
	
	private StockPrice stockPrice;
	private Dividend dividend;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHoldingId() {
		return holdingId;
	}
	public void setHoldingId(String holdingId) {
		this.holdingId = holdingId;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	public String getTickerSymbol() {
		return tickerSymbol;
	}
	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Double getStockVolume() {
		return stockVolume;
	}
	public void setStockVolume(Double stockVolume) {
		this.stockVolume = stockVolume;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Double getPurchasedStockPrice() {
		return purchasedStockPrice;
	}
	public void setPurchasedStockPrice(Double purchasedStockPrice) {
		this.purchasedStockPrice = purchasedStockPrice;
	}
	public StockPrice getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(StockPrice stockPrice) {
		this.stockPrice = stockPrice;
	}
	public Dividend getDividend() {
		return dividend;
	}
	public void setDividend(Dividend dividend) {
		this.dividend = dividend;
	}

}
