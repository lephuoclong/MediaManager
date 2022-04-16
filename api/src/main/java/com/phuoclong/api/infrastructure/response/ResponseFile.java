package com.phuoclong.api.infrastructure.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseFile {

     String name;

     String url;

     String type;

     long size;

}
