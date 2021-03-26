package kz.dilau.htc.filemanager.web.rest;

import kz.dilau.htc.filemanager.service.CandlestickDataService;
import kz.dilau.htc.filemanager.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.OPEN_API_PATH)
@RequiredArgsConstructor
@Slf4j
public class CandlestickDataResource {
    // private final StockQuotaService stockQuotaService;
    private final CandlestickDataService candlestickDataService;

    @GetMapping("/saveCandlestickFromBinanceMannually")
    public void saveCandlestick() {
        candlestickDataService.saveCandlestickFromBinanceMannually();
    }

    //public void saveCandlestickFromBinanceBySymbol(String symbol, Long startTime)
    @GetMapping("/saveCandlestickBySymbolMannually")
    public void saveCandlestickBySymbo(@RequestParam String symbol) {
        candlestickDataService.saveCandlestickFromBinanceBySymbol(symbol, Constants.STARTTIME);
    }

}
