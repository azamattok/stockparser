package kz.dilau.htc.filemanager.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class FileInfoDto {
    private String uuid;
    private String name;
    private String type;
    private long size;
    @JsonIgnore
    private byte[] data;
}
