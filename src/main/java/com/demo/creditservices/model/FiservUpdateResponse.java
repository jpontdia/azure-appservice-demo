package com.demo.creditservices.model;

import lombok.*;

import java.util.Map;

@SuppressWarnings("unused")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiservUpdateResponse {
    Map<String, String> messageMap;
}
