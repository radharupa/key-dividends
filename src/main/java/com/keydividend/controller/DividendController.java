package com.keydividend.controller;

import com.keydividend.entity.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keydividend.rest.StocksRestService;
import com.keydividend.service.DividendService;
import com.keydividend.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rupau
 *
 */

@RestController
@RequestMapping("v1/dividend")
public class DividendController {
	
	@Autowired
	private DividendService dividendService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/searchTicker")
	public TickerSymbol searchTicker(String ticker)
	{
		return dividendService.searchTicker(ticker);
	}

	@PostMapping(path = "/addPortfolio")
	public boolean addPortfolio(@RequestBody Portfolio portfolio) throws Exception{
		return dividendService.addPortfolio(portfolio);
	}
	
	@PostMapping(path = "/updatePortfolio")
	public boolean updatePortfolio(@RequestBody Portfolio portfolio) throws Exception{
		return dividendService.updatePortfolio(portfolio);
	}

	@PostMapping(path = "/removePortfolio")
	public boolean removePortfolio(@RequestBody String portfolioId) throws Exception{
		return dividendService.removePortfolio(portfolioId);
	}

	@PostMapping(path = "/getPortfolios")
	public List<Portfolio> getPortfolios(@RequestBody String userId) throws Exception{
		return dividendService.getPortfoliosByUserId(userId);
	}

	@PostMapping(path = "/addHolding")
	public boolean addHolding(@RequestBody Holding holding) throws Exception{
		return dividendService.addHolding(holding);
	}
	
	@PostMapping(path = "/updateHolding")
	public boolean updateHolding(@RequestBody Holding holding) throws Exception{
		return dividendService.updateHolding(holding);
	}

	@PostMapping(path = "/removeHolding")
	public boolean removeHolding(@RequestBody String holdingId) throws Exception{
		return dividendService.removeHolding(holdingId);
	}

	@PostMapping(path = "/getHoldings")
	public List<Holding> getHoldings(@RequestBody String portfolioId) throws Exception{
		List<String> portfolioIds = new ArrayList<String>();
		portfolioIds.add(portfolioId);
		return dividendService.getHoldingsByPortfoliIds(portfolioIds);
	}

	@PostMapping(path = "/loadDashboard")
	public Document loadDashboard(@RequestBody String userId) throws Exception{
		return dividendService.loadDashboard(userId);
	}


}
