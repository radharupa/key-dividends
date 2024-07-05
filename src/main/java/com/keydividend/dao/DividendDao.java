package com.keydividend.dao;


import java.util.Date;
import java.util.List;

import com.keydividend.entity.*;

/**
 * 
 * @author rupau
 *
 */
public interface DividendDao {
	
	public TickerSymbol getTickerSymbol(String ticker);
	public TickerSymbol saveTickerSymbol(TickerSymbol tickerSymbol);
	public List<TickerSymbol> getTickerSymbols(List<String> tickerSymbols);


	public boolean addPortfolio(Portfolio portfolio);
	public boolean updatePortfolio(Portfolio portfolio);
	public boolean removePortfolio(String portfolioId);
	
	public boolean addHolding(Holding holding);
	public boolean updateHolding(Holding holding);
	public boolean removeHolding(String holdingId);

	public List<Portfolio> getPortfoliosByUserId(String userId);
	public List<Holding> getHoldingsByPortfoliIds(List<String> portfolioIds);
	public List<HistoricalData> getHistoricalData(List<String> tickerSymbols, Date fromDate, Date toDate);

	
}
