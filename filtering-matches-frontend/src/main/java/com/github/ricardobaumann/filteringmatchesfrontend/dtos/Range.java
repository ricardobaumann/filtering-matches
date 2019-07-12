package com.github.ricardobaumann.filteringmatchesfrontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Range {

    @NotNull
    private Integer from;

    @NotNull
    private Integer to;
}
