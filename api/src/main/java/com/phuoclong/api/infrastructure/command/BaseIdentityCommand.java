package com.phuoclong.api.infrastructure.command;

import an.awesome.pipelinr.Command;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class BaseIdentityCommand<T> implements Command<T> {
    @Getter
    @Setter
    public String accountId;
}
