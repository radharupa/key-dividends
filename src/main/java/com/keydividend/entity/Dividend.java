package com.keydividend.entity;

import java.util.Date;


/**
 * 
 * @author rupau
 *
 */
public class Dividend {

	private String tickerSymbol;
	private Double dividend;
	private Date dividendDate;
	private String paymentSchedule;
	
	public String getTickerSymbol() {
		return tickerSymbol;
	}
	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}
	public Double getDividend() {
		return dividend;
	}
	public void setDividend(Double dividend) {
		this.dividend = dividend;
	}

	public Date getDividendDate() {
		return dividendDate;
	}

	public void setDividendDate(Date dividendDate) {
		this.dividendDate = dividendDate;
	}

	public String getPaymentSchedule() {
		return paymentSchedule;
	}

	public void setPaymentSchedule(String paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}
}
