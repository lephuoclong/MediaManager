package com.phuoclong.api.features.account.queries;

import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.models.AccountBase;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import org.springframework.http.ResponseEntity;

public class GetListAccount extends PaginationCommand<ResponseEntity<PageResultOf<AccountBase>>> {
}
