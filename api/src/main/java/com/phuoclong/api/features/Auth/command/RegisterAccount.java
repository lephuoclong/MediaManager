package com.phuoclong.api.features.Auth.command;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.Auth.responses.RegisterResponse;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(callSuper = false)
public class RegisterAccount implements Command<ResponseMessageOf<RegisterResponse>> {
    @NotNull
    @NotBlank
    @NotEmpty
    String email;

    @NotNull
    @NotBlank
    @NotEmpty
    String username;

    @NotNull
    @NotBlank
    @NotEmpty
    String firstName;

    @NotNull
    @NotBlank
    @NotEmpty
    String lastName;

    @NotNull
    @NotBlank
    @NotEmpty
    String password;

    @NotNull
    @NotBlank
    @NotEmpty
    String confirmPassword;
}
