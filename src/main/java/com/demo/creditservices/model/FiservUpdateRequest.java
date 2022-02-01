package com.demo.creditservices.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("unused")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiservUpdateRequest {
    Common common;
    String amount;
    String code;
    String limitOverrideFlag;
}
