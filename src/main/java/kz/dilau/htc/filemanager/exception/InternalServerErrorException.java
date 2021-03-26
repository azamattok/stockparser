package kz.dilau.htc.filemanager.exception;

import kz.dilau.htc.filemanager.util.BundleMessageUtil;
import kz.dilau.htc.filemanager.web.dto.LocaledValue;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends DetailedException {
    public InternalServerErrorException(LocaledValue description) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, description);
    }

    public static InternalServerErrorException createCanNotSaveFile() {
        return new InternalServerErrorException(BundleMessageUtil.getLocaledValue("error.file.saving"));
    }

    public static InternalServerErrorException createCanNotReadFileContent() {
        return new InternalServerErrorException(BundleMessageUtil.getLocaledValue("error.file.reading"));
    }


    public static InternalServerErrorException createCanNotDeleteFile() {
        return new InternalServerErrorException(BundleMessageUtil.getLocaledValue("error.file.deleting"));
    }
}
