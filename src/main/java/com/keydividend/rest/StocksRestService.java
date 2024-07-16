package com.keydividend.rest;

import com.keydividend.entity.TickerExecutive;
import com.keydividend.entity.TickerFinancials;
import com.keydividend.mapper.SearchTickerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.keydividend.entity.TickerSymbol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * 
 * @author rupau
 *
 */

public interface StocksRestService {

	
	public List<TickerSymbol> getSymbols();

	public TickerSymbol getCompanyProfile(TickerSymbol tickerSymbol);

	public TickerSymbol getExecutives(TickerSymbol tickerSymbol);

	public TickerSymbol getFinancials(TickerSymbol tickerSymbol);

	//public TickerSymbol geFullStockPrice(String tickerSymbol);

	public List<TickerSymbol> getBatchStockPrice(List<String> tickerSymbols);
	
	public TickerSymbol getStockPrice(String tickerSymbol);
	
	public void getDividendPrice(String tickerSymbol);
	
	public TickerSymbol searchTicker(String tickerSymbol);
	
	public void getStockNews(List<String> ticketSymbols);
	
	public void getStockNewsWithSentiments();

}
