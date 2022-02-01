package com.demo.creditservices.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateResponse {
    UpdateRequest updateRequest;
    OffsetDateTime changeDate;
}