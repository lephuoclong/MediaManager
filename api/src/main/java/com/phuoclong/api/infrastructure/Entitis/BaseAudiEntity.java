package com.phuoclong.api.infrastructure.Entitis;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@Data
public abstract class BaseAudiEntity {
    @Column(nullable = false)
    protected OffsetDateTime createdDate = OffsetDateTime.now(ZoneOffset.UTC);

    protected OffsetDateTime modifiedDate;

    @Column(length = 36,nullable = false)
    protected String createdBy;

    @Column(length = 36)
    protected String modifiedBy;
}
