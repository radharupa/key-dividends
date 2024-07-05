package com.keydividend.rest;

import com.keydividend.constants.Constants;
import org.springframework.stereotype.Component;

@Component
public class StockServiceFactory {

    public static StocksRestService getStockRestService(String service) {
        StocksRestService stocksRestService = null;

        if (service.equals(Constants.STOCK_SRVICE_FMP)) {
            stocksRestService = new FinancialModelingPrepRestService();
        }

        return stocksRestService;
    }

}
