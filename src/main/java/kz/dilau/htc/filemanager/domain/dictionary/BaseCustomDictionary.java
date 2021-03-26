package kz.dilau.htc.filemanager.domain.dictionary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.dilau.htc.filemanager.domain.base.BaseEntity;
import kz.dilau.htc.filemanager.domain.base.MultiLang;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseCustomDictionary extends BaseEntity<Long> {
    @Embedded
    private MultiLang multiLang;
    @JsonIgnore
    @Column(name = "is_removed", nullable = false, columnDefinition = "boolean default false")
    private Boolean isRemoved = false;

}
