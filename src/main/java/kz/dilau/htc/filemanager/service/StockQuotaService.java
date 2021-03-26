package kz.dilau.htc.filemanager.service;

import com.binance.api.client.domain.general.ExchangeInfo;
import kz.dilau.htc.filemanager.web.dto.FileInfoDto;
import org.springframework.web.multipart.MultipartFile;

public interface StockQuotaService {
    public void saveQuotesFromBinanceMannually();
    public void saveQuotesFromBinancBySchedule();

    ExchangeInfo getExchangeInfo();
}
