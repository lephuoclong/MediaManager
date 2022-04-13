package com.phuoclong.api.features.Auth.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class RegisterResponse {
    String username;

    String message;
}
