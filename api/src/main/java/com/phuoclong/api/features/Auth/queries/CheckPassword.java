package com.phuoclong.api.features.Auth.queries;

import com.phuoclong.api.features.Auth.responses.LoginResponse;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CheckPassword extends BaseIdentityCommand<ResponseMessageOf<LoginResponse>> {
    @Length(min = 6)
    String password;
}
