package com.keydividend.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author rupau
 *
 */
public class TickerSymbol {
	
	private String tickerSymbol;
	private String exchange;
	private String exchangeShortName;
	private String cik;

	private String stockType;
	private String stockGrade;
	private String stockPrevGrade;
	private String stockGradingCompany;

	private String countryCode;
	private String currencyCode;

	private String websiteUrl;
	private String companyName;
	private String companyAddress;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String companyIpoDate;
	private String companyIndustry;
	private String companySector;
	private String companyLogoUrl;
	private String description;
	private int fullTimeEmployees;
	private List<TickerExecutive> tickerExecutives;
	private List<TickerFinancials> tickerFinancials;


	private boolean dividendExists;
	private int dividendDuration;
	private Date dividendDate;

	private StockPrice stockPrice;
	private List<StockPrice> historicalData;

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
	public boolean isDividendExists() {
		return dividendExists;
	}
	public void setDividendExists(boolean dividendExists) {
		this.dividendExists = dividendExists;
	}
	public int getDividendDuration() {
		return dividendDuration;
	}
	public void setDividendDuration(int dividendDuration) {
		this.dividendDuration = dividendDuration;
	}
	public Date getDividendDate() {
		return dividendDate;
	}
	public void setDividendDate(Date dividendDate) {
		this.dividendDate = dividendDate;
	}

	public String getCik() {
		return cik;
	}

	public void setCik(String cik) {
		this.cik = cik;
	}

	public List<TickerExecutive> getTickerExecutives() {
		return tickerExecutives;
	}

	public void setTickerExecutives(List<TickerExecutive> tickerExecutives) {
		this.tickerExecutives = tickerExecutives;
	}

	public void addTickerExecutive(TickerExecutive tickerExecutive)
	{
		if(tickerExecutives == null)
			tickerExecutives = new ArrayList<TickerExecutive>();
		tickerExecutives.add(tickerExecutive);
	}

	public StockPrice getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(StockPrice stockPrice) {
		this.stockPrice = stockPrice;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getExchangeShortName() {
		return exchangeShortName;
	}

	public void setExchangeShortName(String exchangeShortName) {
		this.exchangeShortName = exchangeShortName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompanyIpoDate() {
		return companyIpoDate;
	}

	public void setCompanyIpoDate(String companyIpoDate) {
		this.companyIpoDate = companyIpoDate;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getCompanySector() {
		return companySector;
	}

	public void setCompanySector(String companySector) {
		this.companySector = companySector;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStockGrade() {
		return stockGrade;
	}

	public void setStockGrade(String stockGrade) {
		this.stockGrade = stockGrade;
	}

	public List<TickerFinancials> getTickerFinancials() {
		return tickerFinancials;
	}

	public void setTickerFinancials(List<TickerFinancials> tickerFinancials) {
		this.tickerFinancials = tickerFinancials;
	}

	public void addTickerFinancials(TickerFinancials tickerFinancial)
	{
		if(tickerFinancials == null)
			tickerFinancials = new ArrayList<TickerFinancials>();
		tickerFinancials.add(tickerFinancial);
	}

	public List<StockPrice> getHistoricalData() {
		return historicalData;
	}

	public void setHistoricalData(List<StockPrice> historicalData) {
		this.historicalData = historicalData;
	}

	public String getCompanyLogoUrl() {
		return companyLogoUrl;
	}

	public void setCompanyLogoUrl(String companyLogoUrl) {
		this.companyLogoUrl = companyLogoUrl;
	}

	public int getFullTimeEmployees() {
		return fullTimeEmployees;
	}

	public void setFullTimeEmployees(int fullTimeEmployees) {
		this.fullTimeEmployees = fullTimeEmployees;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStockPrevGrade() {
		return stockPrevGrade;
	}

	public void setStockPrevGrade(String stockPrevGrade) {
		this.stockPrevGrade = stockPrevGrade;
	}

	public String getStockGradingCompany() {
		return stockGradingCompany;
	}

	public void setStockGradingCompany(String stockGradingCompany) {
		this.stockGradingCompany = stockGradingCompany;
	}
}
