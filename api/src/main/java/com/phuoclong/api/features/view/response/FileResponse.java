package com.phuoclong.api.features.view.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class FileResponse {
    String content;

    String name;

    String type;
}
