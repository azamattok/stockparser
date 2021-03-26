package kz.dilau.htc.filemanager.exception;

import kz.dilau.htc.filemanager.util.BundleMessageUtil;
import kz.dilau.htc.filemanager.web.dto.LocaledValue;
import org.springframework.http.HttpStatus;

public class NotFoundException extends DetailedException {
    public NotFoundException(LocaledValue description) {
        super(HttpStatus.NOT_FOUND, description);
    }

    public static NotFoundException createFileNotFoundByUuid(String uuid) {
        return new NotFoundException(BundleMessageUtil.getLocaledValue("error.file.notFound.uuid", uuid));
    }
}
