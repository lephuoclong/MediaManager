package com.phuoclong.api.infrastructure.models;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface MyFolderShare {
    UUID getId();
    String getName();
    UUID getCreatedBy();
    OffsetDateTime getCreatedDate();
    UUID getAccountId();
    Integer getLevel();
    UUID getParentId();
    Integer getDeleted();
    String getEmail();
    String getReceiver();
}
