package com.phuoclong.api.infrastructure.models;

import java.util.UUID;

public interface MyFileShare {
    UUID getId();
    String getDisplayName();
    String getName();
    Long getSize();
    String getType();
    UUID getDirectoryId();
    String getEmail();
    String getReceiver();
}
