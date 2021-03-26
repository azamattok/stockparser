package kz.dilau.htc.filemanager.web.rest.util;

import kz.dilau.htc.filemanager.web.dto.FileInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ResponseBuilder {
    private ResponseBuilder() {}

    public static ResponseEntity<Resource> prepareDownloadResponse(FileInfoDto fileInfo, boolean isPreview) {
        String encodedFileName = fileInfo.getName();
        try {
            encodedFileName = URLEncoder.encode(fileInfo.getName(), StandardCharsets.UTF_8.displayName()).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            log.error("Unsupported encoding {}", StandardCharsets.UTF_8.displayName());
        }

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, (isPreview ? "inline" : "attachment")
                + "; filename=" + encodedFileName
                + "; filename*=UTF-8''" + encodedFileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(fileInfo.getData());
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(fileInfo.getSize())
                .contentType(MediaType.parseMediaType(fileInfo.getType()))
                .body(resource);
    }
}
