package com.phuoclong.api.features.Auth.command;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(callSuper = true)
public class RegisterAccount extends BaseIdentityCommand<ResponseMessageOf<String>> {
    @NotNull
    @NotBlank
    @NotEmpty
    String email;

    @NotNull
    @NotBlank
    @NotEmpty
    String fistName;

    @NotNull
    @NotBlank
    @NotEmpty
    String lastName;

    @NotNull
    @NotBlank
    @NotEmpty
    String password;
}
