package com.ef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
public class Arguments {

    private String name;

    private String value;
}