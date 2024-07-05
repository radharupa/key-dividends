package com.keydividend.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keydividend.constants.Constants;
import com.keydividend.rest.StockServiceFactory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keydividend.dao.DividendDao;
import com.keydividend.entity.Holding;
import com.keydividend.entity.Portfolio;
import com.keydividend.entity.TickerSymbol;
import com.keydividend.rest.StocksRestService;

/**
 * 
 * @author rupau
 *
 */

@Service
public class DividendService {
	
	@Autowired
	DividendDao dividendDao;
	
	
	public TickerSymbol searchTicker(String ticker) {
		
		TickerSymbol tickerSymbol = dividendDao.getTickerSymbol(ticker);
		if(tickerSymbol == null)
		{
			StocksRestService stocksRestService = StockServiceFactory.getStockRestService(Constants.STOCK_SRVICE_FMP);
			tickerSymbol = stocksRestService.searchTicker(ticker);
			saveTickerSymbol(tickerSymbol);
		}
		return tickerSymbol;
	}
	
	public TickerSymbol saveTickerSymbol(TickerSymbol tickerSymbol) {
		return dividendDao.saveTickerSymbol(tickerSymbol);
	}

	public boolean addPortfolio(Portfolio portfolio) {
		return dividendDao.addPortfolio(portfolio);
	}

	public boolean updatePortfolio(Portfolio portfolio) {
		return dividendDao.updatePortfolio(portfolio);
	}

	public boolean removePortfolio(String portfolioId) {
		return dividendDao.removePortfolio(portfolioId);
	}
	
	public boolean addHolding(Holding holding) {
		return dividendDao.addHolding(holding);
	}
	
	public boolean updateHolding(Holding holding) {
		return dividendDao.updateHolding(holding);
	}

	public boolean removeHolding(String holdingId) {
		return dividendDao.removeHolding(holdingId);
	}

	public List<Portfolio> getPortfoliosByUserId(String userId)
	{
		return dividendDao.getPortfoliosByUserId(userId);
	}

	public List<Holding> getHoldingsByPortfoliIds(List<String> portfolioIds)
	{
		return dividendDao.getHoldingsByPortfoliIds(portfolioIds);
	}


	public List<TickerSymbol> getTickerSymbolsWithLatest(List<String> tickerSymbols) {

		List<TickerSymbol> tickerSymbolList = dividendDao.getTickerSymbols(tickerSymbols);
		if(tickerSymbolList != null && !tickerSymbolList.isEmpty())
		{
			StocksRestService stocksRestService = StockServiceFactory.getStockRestService(Constants.STOCK_SRVICE_FMP);
			stocksRestService.getBatchStockPrice(tickerSymbols);
			//TODO: set the data to tickerSymbolList
		}
		return tickerSymbolList;
	}

	public Document loadDashboard(String userId)
	{
		Document responseDoc = new Document();
		try {
			List<Portfolio> portfolios = dividendDao.getPortfoliosByUserId(userId);

			if(portfolios != null && !portfolios.isEmpty())
			{
				List<TickerSymbol> tickerSymbolsList = null;
				List<String> portfolioIds = portfolios.stream().map(p -> p.getPortfolioId()).collect(Collectors.toList());
				List<Holding> holdings = getHoldingsByPortfoliIds(portfolioIds);


				if(holdings != null && !holdings.isEmpty()) {
					Map<String, List<Holding>> holdingsMap = holdings.stream().collect(Collectors.groupingBy(h -> h.getPortfolioId()));
					for(Portfolio portfolio : portfolios)
					{
						if(holdingsMap != null && holdingsMap.containsKey(portfolio.getPortfolioId()))
							portfolio.setHoldings(holdingsMap.get(portfolio.getPortfolioId()));
					}

					List<String> tickerSymbols = holdings.stream().map(h -> h.getTickerSymbol()).collect(Collectors.toList());

					tickerSymbolsList = getTickerSymbolsWithLatest(tickerSymbols);

				}
				Document data = new Document();
				data.put("portfolios", portfolios);
				data.put("tickerSymbols", tickerSymbolsList);
				responseDoc.put("data",data);
			}
		}catch (Exception e)
		{

		}

		return responseDoc;
	}

}
