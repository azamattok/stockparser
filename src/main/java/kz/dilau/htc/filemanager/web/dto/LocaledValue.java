package kz.dilau.htc.filemanager.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LocaledValue implements Serializable {
    private String ru;
    private String kk;
    private String en;
}
