package com.github.ricardobaumann.filteringmatches.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Range {
    private Integer from;
    private Integer to;
}
