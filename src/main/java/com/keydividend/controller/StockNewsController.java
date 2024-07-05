package com.keydividend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keydividend.service.StockNewsService;

/**
 * 
 * @author rupau
 *
 */

@RestController
@RequestMapping("v1/stockNews")
public class StockNewsController {
	
	@Autowired
	private StockNewsService stockNewsService;
	
	

}
