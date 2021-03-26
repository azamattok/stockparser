package kz.dilau.htc.filemanager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@RefreshScope
@Component
@ConfigurationProperties(prefix = "data")
@Data
public class DataProperties {
    private String basePath;
    private List<String> imageContentTypes;
    private ImageSize maxImageSize;
    private ImageSize previewImageSize;

    @Data
    public static class ImageSize {
        private int width;
        private int height;
    }
}
