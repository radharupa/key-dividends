package com.keydividend.controller;

import com.keydividend.constants.Constants;
import com.keydividend.entity.TickerSymbol;
import com.keydividend.rest.StockServiceFactory;
import com.keydividend.rest.StocksRestService;
import com.keydividend.service.DividendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * @author rupau
 *
 */

@RestController
@RequestMapping("v1/stock")
public class StockController {

	@Autowired
	DividendService dividendService;


	@GetMapping(path = "/saveTickers")
	public Boolean saveTickers()
	{
		try {
			TickerSymbol tickerSymbol = new TickerSymbol();
			tickerSymbol.setTickerSymbol("AAPL");
			StocksRestService stockRestService = StockServiceFactory.getStockRestService(Constants.STOCK_SRVICE_FMP);
			System.out.println(stockRestService);
			stockRestService.getCompanyProfile(tickerSymbol);
			stockRestService.getExecutives(tickerSymbol);

			/*List<TickerSymbol> tickerSymbols = stockRestService.getSymbols();
			for (TickerSymbol tickerSymbol : tickerSymbols) {
				dividendService.saveTickerSymbol(tickerSymbol);
			}*/
		}catch (Exception ex)
		{
			return false;
		}
		return true;
	}

	@GetMapping(path = "/searchTicker")
	public TickerSymbol searchTicker(String ticker)
	{
		TickerSymbol tickerSymbol = null;
		try {
			tickerSymbol = dividendService.searchTicker(ticker);
		}catch (Exception ex)
		{

		}
		return tickerSymbol;
	}
	

}
