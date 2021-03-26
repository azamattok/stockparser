package kz.dilau.htc.filemanager.domain.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Builder
@Embeddable
public class MultiLang implements Serializable {
    @Column(name = "name_kz", nullable = false)
    private String nameKz;
    @Column(name = "name_ru", nullable = false)
    private String nameRu;
    @Column(name = "name_en", nullable = false)
    private String nameEn;

    public MultiLang() {
    }

    public MultiLang(String nameKz, String nameRu, String nameEn) {
        this.nameKz = nameKz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }


}
