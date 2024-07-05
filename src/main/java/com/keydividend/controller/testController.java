package com.keydividend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keydividend.rest.StocksRestService;


@RestController
public class testController {
	
	@GetMapping("/test")
	public String test()
	{
		System.out.println("test");
		//StocksRestService stocksRest = new StocksRestService();
		//stocksRest.getSymbols();
		//stocksRest.getStockPrice("MOND");
		//stocksRest.getDividendPrice("AAPL");
		//stocksRest.searchTicker("MOND");
		//stocksRest.getStockNewsWithSentiments();
		return "test msg";
	}

}
