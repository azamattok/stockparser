package kz.dilau.htc.filemanager.service;

public interface CandlestickDataService {
    void saveCandlestickFromBinanceMannually();

    void saveCandlestickFromBinanceBySchedulle();

}
