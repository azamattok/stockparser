package kz.dilau.htc.filemanager.web.rest;

import com.binance.api.client.domain.general.ExchangeInfo;
import kz.dilau.htc.filemanager.service.StockQuotaService;
import kz.dilau.htc.filemanager.util.Constants;
import kz.dilau.htc.filemanager.web.dto.FileInfoDto;
import kz.dilau.htc.filemanager.web.rest.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.OPEN_API_PATH)
@RequiredArgsConstructor
@Slf4j
public class OpenStockQuotaResource {
    private final StockQuotaService stockQuotaService;
/*
    @GetMapping("/info/{uuid}")
    public ResponseEntity<FileInfoDto> getFileInfo(@PathVariable String uuid) {
        return ResponseEntity.ok(stockQuotaService.getFileInfo(uuid));
    }*/

    @GetMapping("/allapi")
    public void getAllApi() {
        stockQuotaService.saveQuotesFromBinanceMannually();
    }

    @GetMapping("/exchangeinfo")
    public ResponseEntity<ExchangeInfo> getExchangeInfo() {
        return ResponseEntity.ok(stockQuotaService.getExchangeInfo());
    }


}
