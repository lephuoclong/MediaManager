package com.phuoclong.api.infrastructure.models;

import java.util.UUID;

public interface FileSearch {
    UUID getId();
    String getName();
    String getDisplayName();
    String getType();
    Long getSize();
    UUID getAccountId();
    UUID getDirectoryId();
}
