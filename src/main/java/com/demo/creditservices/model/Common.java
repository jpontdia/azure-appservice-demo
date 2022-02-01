package com.demo.creditservices.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CustomerInquiryRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-17T09:01:06.170Z[GMT]")

public class Common {
  @JsonProperty("accountId")
  private String accountId;

  @JsonProperty("user")
  private String User = null;

  @JsonProperty("debug")
  private String debug;

  @JsonProperty("presentationId")
  private String presentationId;

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getUser() {
    return User;
  }

  public void setUser(String user) {
    User = user;
  }

  public String getDebug() {
    return debug;
  }

  public void setDebug(String debug) {
    this.debug = debug;
  }

  public String getPresentationId() {
    return presentationId;
  }

  public void setPresentationId(String presentationId) {
    this.presentationId = presentationId;
  }
}
