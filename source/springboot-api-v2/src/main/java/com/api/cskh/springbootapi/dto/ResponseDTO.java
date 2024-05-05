package com.api.cskh.springbootapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private T data;
    private String message;
    private Integer status = 1; // 0: error, 1: success (default 1)
}
