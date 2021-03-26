package kz.dilau.htc.filemanager.web.rest;

import kz.dilau.htc.filemanager.service.StockQuotaService;
import kz.dilau.htc.filemanager.util.Constants;
import kz.dilau.htc.filemanager.web.dto.FileInfoDto;
import kz.dilau.htc.filemanager.web.rest.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Constants.API_PATH)
@RequiredArgsConstructor
@Slf4j
public class StockQuotaResource {
    private final StockQuotaService stockQuotaService;
/*
    @PostMapping("/upload")
    public ResponseEntity<FileInfoDto> uploadFile(@RequestParam MultipartFile file) {
        return ResponseEntity.ok(stockQuotaService.uploadFile(file));
    }

    @GetMapping("/info/{uuid}")
    public ResponseEntity<FileInfoDto> getFileInfo(@PathVariable String uuid) {
        return ResponseEntity.ok(stockQuotaService.getFileInfo(uuid));
    }*/


}
