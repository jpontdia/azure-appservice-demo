package com.demo.creditservices.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateRequest {

  @Pattern(regexp = "^([0-9]{15,16})$", message = "accountId should contain only numbers.")
  @Schema(description="Account Id for credit update")
  private String accountId;

  @Size(min=1, max=10)
  @Pattern(regexp = "\\d+", message = "amount should contain only numbers.")
  @Schema(description="Amount limit for credit.")
  private String amount;

  @Size(min=2, max=30, message="sourceSystem must have in size 2 to 30 characters.")
  @Schema(description="System that request the update of credit limit.")
  private String sourceSystem;

  @Size(min=1, max=30, message="messageIdentifier must have in size 1 to 30 characters.")
  @Schema(description="Unique message identifier to differentiate among several request")
  private String messageIdentifier;
}