package com.phuoclong.api.features.Auth.command;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassword extends BaseIdentityCommand<ResponseMessageOf<String>> {
    String token;
    String newPassword;
    String confirmPassword;
}
