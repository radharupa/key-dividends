package com.keydividend.entity;


import java.util.List;

/**
 *
 * @author rupau
 */
public class Watchlist {

    private String watchlistId;
    private String userId;
    private String tickerSymbol;

    private List<HistoricalData> historicalDataList;
    private Double priceCap; //to alert if the stock price reach this value
    private String notificationEmail;
    private String notificationPhoneNumber;



    public String getWatchlistId() {
        return watchlistId;
    }

    public void setWatchlistId(String watchlistId) {
        this.watchlistId = watchlistId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public List<HistoricalData> getHistoricalDataList() {
        return historicalDataList;
    }

    public void setHistoricalDataList(List<HistoricalData> historicalDataList) {
        this.historicalDataList = historicalDataList;
    }

    public Double getPriceCap() {
        return priceCap;
    }

    public void setPriceCap(Double priceCap) {
        this.priceCap = priceCap;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public String getNotificationPhoneNumber() {
        return notificationPhoneNumber;
    }

    public void setNotificationPhoneNumber(String notificationPhoneNumber) {
        this.notificationPhoneNumber = notificationPhoneNumber;
    }
}
