package com.phuoclong.api.features.account.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@AllArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class ChangeAccountInfo extends BaseIdentityCommand<ResponseMessageOf<String>> {
    String firstName;
    String lastName;
}
