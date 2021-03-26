package kz.dilau.htc.filemanager.service;

public interface CandlestickDataService {
    void saveCandlestickFromBinanceMannually();

    void saveCandlestickFromBinanceBySchedulle();

    void saveCandlestickFromBinanceBySymbol(String symbol, Long startTime);

    void saveCandlestickFromBinanceBySymbol(Long starttime);
}
