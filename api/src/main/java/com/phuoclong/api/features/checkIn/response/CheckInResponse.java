package com.phuoclong.api.features.checkIn.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class CheckInResponse {
    UUID accountId;

    String username;

    String fullName;

    String email;
}
