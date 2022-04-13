package com.phuoclong.api.features.Auth.command;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.Auth.responses.LoginResponse;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@NoArgsConstructor
@FieldNameConstants
public class LoginAccount implements Command<ResponseMessageOf<LoginResponse>> {

    String username;

    String password;

}
