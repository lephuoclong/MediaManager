package com.phuoclong.api.infrastructure.models;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface DirectoryShare {
    UUID getId();
    UUID getCreatedBy();
    OffsetDateTime getCreatedDate();
    UUID getModifiedBy();
    OffsetDateTime getModifiedDate();
    UUID getAccountId();
    Integer getDeleted();
    Integer getLevel();
    String getName();
    UUID getParentId();
}
